/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.io.IOException;
import java.net.ServerSocket;
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

    private ServerSocket serverSocket;
    private JScrollPane scrollPane;
    private JFrame serverLog;
    private String logTime;

    private JTextArea textArea;

    public Server() {
        init();
    }

    public void init() {
        serverLog = new JFrame();
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);

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
            textArea.append(logTime + ": Server iniciado na porta 8000\n");

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
