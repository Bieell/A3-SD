/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class NewGameSession implements Runnable {

    private String[] marks = {"X", "O"};
    private final static int PLAYER_X = 0;
    private final static int PLAYER_O = 1;
    private int currentPlayer;
    private int playerXWins = 0;
    private int playerOWins = 0;

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    private DataInputStream inputPlayerOne;
    private DataOutputStream outputPlayerOne;

    private DataInputStream inputPlayerTwo;
    private DataOutputStream outputPlayerTwo;

    public NewGameSession(Socket socketPlayerOne, Socket socketPlayerTwo) {
        this.socketPlayerOne = socketPlayerOne;
        this.socketPlayerTwo = socketPlayerTwo;
    }

    @Override
    public void run() {

        try {
            inputPlayerOne = new DataInputStream(socketPlayerOne.getInputStream());
            outputPlayerOne = new DataOutputStream(socketPlayerOne.getOutputStream());

            inputPlayerTwo = new DataInputStream(socketPlayerTwo.getInputStream());
            outputPlayerTwo = new DataOutputStream(socketPlayerTwo.getOutputStream());

            outputPlayerOne.writeInt(0);

            while (true) {
                int row = inputPlayerOne.readInt();
                int column = inputPlayerOne.readInt();
                
                outputPlayerTwo.writeInt(row);
                outputPlayerTwo.writeInt(column);
                
                row = inputPlayerTwo.readInt();
                column = inputPlayerTwo.readInt();
                
                outputPlayerOne.writeInt(row);
                outputPlayerOne.writeInt(column);
                
            }

        } catch (IOException ex) {
            Logger.getLogger(NewGameSession.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}
