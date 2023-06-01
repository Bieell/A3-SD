/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
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
    private final static int PLAYER_X_WON = 0;
    private final static int PLAYER_O_WON = 1;
    private final static int DRAW = 2;
    private final static int CONTINUE = 3;
    private final static int REMATCH = 4;

    private int currentPlayer;
    private int playerXWins = 0;
    private int playerOWins = 0;
    private String[][] board = new String[3][3];
    private int[][] winButtons = new int[3][2];

    private Socket socketPlayerOne;
    private Socket socketPlayerTwo;

    private DataInputStream inputPlayerOne;
    private DataOutputStream outputPlayerOne;

    private DataInputStream inputPlayerTwo;
    private DataOutputStream outputPlayerTwo;

    public NewGameSession(Socket socketPlayerOne, Socket socketPlayerTwo) {
        this.socketPlayerOne = socketPlayerOne;
        this.socketPlayerTwo = socketPlayerTwo;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
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
                board[row][column] = "X";

                int result = checkWinner("X", row, column, PLAYER_X, outputPlayerTwo);
                if (result == DRAW) {
                    continue;
                }
                if (result == REMATCH) {
                    continue;
                }

                row = inputPlayerTwo.readInt();
                column = inputPlayerTwo.readInt();
                board[row][column] = "O";

                result = checkWinner("O", row, column, PLAYER_O, outputPlayerOne);
                if (result == DRAW) {
                    continue;
                }
                if (result == REMATCH) {
                    continue;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(NewGameSession.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int checkWinner(String mark, int row, int column, int playerWon, DataOutputStream outputPlayer) throws IOException {

        if (isWon(mark)) {
            outputPlayerOne.writeInt(playerWon);
            outputPlayerTwo.writeInt(playerWon);
            sendMove(outputPlayer, row, column);
            sendWinButtons(outputPlayerOne);
            sendWinButtons(outputPlayerTwo);
            if (rematch(mark) == true) {
                for (int i = 0; i < board.length; i++) {
                    Arrays.fill(board[i], "");
                }
                return REMATCH;
            }
            return playerWon;

        } else if (isFull()) {
            outputPlayerOne.writeInt(DRAW);
            outputPlayerTwo.writeInt(DRAW);
            sendMove(outputPlayer, row, column);
            for (int i = 0; i < board.length; i++) {
                Arrays.fill(board[i], "");
            }
            return DRAW;
        } else {
            outputPlayer.writeInt(CONTINUE);
            sendMove(outputPlayer, row, column);
            return CONTINUE;
        }

    }

    private boolean isWon(String mark) {
        //chegando primeira diagonal
        if ((board[0][0] == mark) && (board[1][1] == mark) && (board[2][2] == mark)) {
            int[] rows = {0, 1, 2};
            int[] columns = {0, 1, 2};
            setWinButtons(rows, columns);
            return true;
        }

        //chegando segunda diagonal
        if ((board[0][2] == mark) && (board[1][1] == mark) && (board[2][0] == mark)) {
            int[] rows = {0, 1, 2};
            int[] columns = {2, 1, 0};
            setWinButtons(rows, columns);
            return true;
        }

        //checando as linhas
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == mark) && (board[i][1] == mark) && (board[i][2] == mark)) {
                int[] rows = {i, i, i};
                int[] columns = {0, 1, 2};
                setWinButtons(rows, columns);
                return true;
            }
        }

        //checando as colunas
        for (int j = 0; j < 3; j++) {
            if ((board[0][j] == mark) && (board[1][j] == mark) && (board[2][j] == mark)) {
                int[] rows = {0, 1, 2};
                int[] columns = {j, j, j};
                setWinButtons(rows, columns);
                return true;
            }
        }

        return false;
    }

    private void sendMove(DataOutputStream out, int row, int column) throws IOException {
        out.writeInt(row);
        out.writeInt(column);
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private void setWinButtons(int[] rows, int[] columns) {
        winButtons[0][0] = rows[0];
        winButtons[0][1] = columns[0];
        winButtons[1][0] = rows[1];
        winButtons[1][1] = columns[1];
        winButtons[2][0] = rows[2];
        winButtons[2][1] = columns[2];

    }

    private void sendWinButtons(DataOutputStream outputPlayer) {
        try {
            int linhas = winButtons.length;
            int colunas = winButtons[0].length;
            outputPlayer.writeInt(linhas);
            outputPlayer.writeInt(colunas);

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    outputPlayer.writeInt(winButtons[i][j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean rematch(String markWinner) throws IOException {
        if (markWinner == marks[PLAYER_X]) {
            inputPlayerTwo.readBoolean();
            outputPlayerOne.writeBoolean(true);
            return true;
        } else {
            inputPlayerOne.readBoolean();
            outputPlayerTwo.writeBoolean(true);
            return true;
        }
    }
}
