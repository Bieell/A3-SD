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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    private String[] buttons;
    private String[][] board;

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    public Server() {
        init();
    }

    public void init() {
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
            textArea.append(logTime + ":       Aguardando conexão dos Jogadores.\n");
            socketPlayerOne = serverSocket.accept();
            textArea.append(logTime + ":       Jogador 1 conectado!\n");
            socketPlayerTwo = serverSocket.accept();
            textArea.append(logTime + ":       Jogador 2 conectado!\n");

            int sessionNum = 1;
            
            textArea.append(logTime + ":       Iniciando thread para sessão:  " + sessionNum++ + "...\n");
            NewGameSession session = new NewGameSession(socketPlayerOne, socketPlayerTwo);
            Thread t1 = new Thread(session);
            t1.start();

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

//    private boolean isWon() {
//        board = ;
//        int k = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                board[i][j] = buttons[k];
//                k++;
//            }
//        }
//
//        // Verificar linhas
//        for (int linha = 0; linha < 3; linha++) {
//            if (!board[linha][0].getText().equals("")
//                    && board[linha][0].getText().equals(board[linha][1].getText())
//                    && board[linha][0].getText().equals(board[linha][2].getText())) {
//                winButtons = new JButton[3];
//                winButtons[0] = board[linha][0];
//                winButtons[1] = board[linha][1];
//                winButtons[2] = board[linha][2];
//                return true;
//            }
//        }
//
//        // Verificar colunas
//        for (int coluna = 0; coluna < 3; coluna++) {
//            if (!board[0][coluna].getText().equals("")
//                    && board[0][coluna].getText().equals(board[1][coluna].getText())
//                    && board[0][coluna].getText().equals(board[2][coluna].getText())) {
//                winButtons = new JButton[3];
//                winButtons[0] = board[0][coluna];
//                winButtons[1] = board[1][coluna];
//                winButtons[2] = board[2][coluna];
//                return true;
//            }
//        }
//
//        // Verificar diagonais
//        if (!board[0][0].getText().equals("")
//                && board[0][0].getText().equals(board[1][1].getText())
//                && board[0][0].getText().equals(board[2][2].getText())) {
//            winButtons = new JButton[3];
//            winButtons[0] = board[0][0];
//            winButtons[1] = board[1][1];
//            winButtons[2] = board[2][2];
//            return true;
//        }
//
//        if (!board[0][2].getText().equals("")
//                && board[0][2].getText().equals(board[1][1].getText())
//                && board[0][2].getText().equals(board[2][0].getText())) {
//            winButtons = new JButton[3];
//            winButtons[0] = board[0][2];
//            winButtons[1] = board[1][1];
//            winButtons[2] = board[2][0];
//            return true;
//        }
//
//        return false; // Não há vitória
//
//    }
    public static void main(String[] args) {
        new Server();
    }

}
