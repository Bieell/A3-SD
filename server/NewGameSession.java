/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author gabri
 */
public class NewGameSession implements Runnable{
    
    private DataInputStream fromPlayerOne;
    private DataOutputStream toPlayerOne;

    private DataInputStream fromPlayerTwo;
    private DataOutputStream toPlayerTwo;

    public NewGameSession (Socket socketPlayerOne, Socket socketPlayerTwo) {
        
    }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
