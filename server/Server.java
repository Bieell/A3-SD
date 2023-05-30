/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author GabrielAlves
 */
public class Server {

    private JFrame serverLog;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private String logTime;

    private ServerSocket serverSocket;
    private Socket playerOne;
    private Socket playerTwo;

    private final static int PLAYER_X = 0;
    private final static int PLAYER_O = 1;

    private String[] buttons;
    private String[][] board;

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    public Server() {
        init();
    }

    private void init() {
        serverLog = new JFrame();
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        serverLog.add(scrollPane, BorderLayout.CENTER);
        serverLog.setSize(400, 300);
        serverLog.setTitle("Server Jogo da Velha");

        serverLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverLog.setVisible(true);

        playerConnection();

    }

    public void playerConnection() {
        try {

            logTime = formatCurrentTime();
            serverSocket = new ServerSocket(8000);
            textArea.append(logTime + ":       Server iniciado na porta 8000.\n");
            int sessionNum = 1;
            while (true) {
                textArea.append(logTime + ":       Aguardando conexão dos Jogadores.\n");
                socketPlayerOne = serverSocket.accept();
                textArea.append(logTime + ":       Jogador 1 conectado!\n");
                new DataOutputStream(socketPlayerOne.getOutputStream()).writeInt(PLAYER_X);
                
                DataInputStream inputCPU = new DataInputStream(socketPlayerOne.getInputStream());
                boolean vsCPU = inputCPU.readBoolean();
                if(vsCPU == true) {
                    System.out.println("Contra CPU");
                    continue;
                }
                
                socketPlayerTwo = serverSocket.accept();
                textArea.append(logTime + ":       Jogador 2 conectado!\n");
                inputCPU = new DataInputStream(socketPlayerTwo.getInputStream());
                vsCPU = inputCPU.readBoolean();
                
                if(vsCPU == true) {
                    System.out.println("Contra CPU");
                    new DataOutputStream(socketPlayerTwo.getOutputStream()).writeInt(PLAYER_X);
                    new DataOutputStream(socketPlayerOne.getOutputStream()).writeInt(-1);
                    continue;
                }

                new DataOutputStream(socketPlayerTwo.getOutputStream()).writeInt(PLAYER_O);
                textArea.append(logTime + ":       Iniciando thread para sessão:  " + sessionNum++ + "...\n");
                NewGameSession session = new NewGameSession(socketPlayerOne, socketPlayerTwo);
                Thread t = new Thread(session);
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String formatCurrentTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static void main(String[] args) {
        new Server();
    }

}
