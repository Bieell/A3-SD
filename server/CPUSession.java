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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GabrielAlves
 */
public class CPUSession implements Runnable {

    private final static int PLAYER_MARK = 0;
    private final static int CPU_MARK = 1;
    private final static int PLAYER_WON = 0;
    private final static int CPU_WON = 1;
    private final static int DRAW = 2;
    private final static int CONTINUE = 3;
    private final static int REMATCH = 4;

    private String[][] board = new String[3][3];
    private int[][] winButtons = new int[3][2];

    private Socket playerSocket;

    private DataInputStream inputPlayerSocket;
    private DataOutputStream outputPlayerSocket;

    public CPUSession(Socket playerSocket) {
        this.playerSocket = playerSocket;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    @Override
    public void run() {

        try {
            inputPlayerSocket = new DataInputStream(playerSocket.getInputStream());
            outputPlayerSocket = new DataOutputStream(playerSocket.getOutputStream());

            outputPlayerSocket.writeInt(0);

            while (true) {
                int row = inputPlayerSocket.readInt();
                int column = inputPlayerSocket.readInt();
                board[row][column] = "X";

                int result = checkWinner("X", row, column, PLAYER_MARK);
                if (result == DRAW) {
                    continue;
                }
                if (result == REMATCH) {
                    continue;
                }

                int cpuMoves[] = getCPUMove();
                System.out.println(cpuMoves[0] + " " + cpuMoves[1]);
                result = checkWinner("O", cpuMoves[0], cpuMoves[1], CPU_MARK);

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

    private int checkWinner(String mark, int row, int column, int playerWon) throws IOException {

        if (isWon(mark)) {
            outputPlayerSocket.writeInt(playerWon);
            if ("O".equals(mark)) {
                sendMove(outputPlayerSocket, row, column);
            }
            sendWinButtons(outputPlayerSocket);
            if (rematch(mark) == true) {
                for (int i = 0; i < board.length; i++) {
                    Arrays.fill(board[i], "");
                }
                return REMATCH;
            }
            return playerWon;

        } else if (isFull()) {
            outputPlayerSocket.writeInt(DRAW);
            for (int i = 0; i < board.length; i++) {
                Arrays.fill(board[i], "");
            }
            return DRAW;
        } else {
            if (mark == "O") {
                outputPlayerSocket.writeInt(CONTINUE);
                sendMove(outputPlayerSocket, row, column);
            }
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
        if (markWinner.equals("X")) {
            outputPlayerSocket.writeBoolean(true);
            return true;
        } else if (markWinner.equals("O")) {
            inputPlayerSocket.readBoolean();
            return true;
        } else {
            return false;
        }
    }

    private int[] getCPUMove() {
        int[] cpuMove = new int[2];
        if (!isFull()) {
            int row;
            int column;
            boolean moveNotFound = true;
            while (moveNotFound) {
                row = new Random().nextInt(2 - 0 + 1) + 0;
                column = new Random().nextInt(2 - 0 + 1) + 0;
                moveNotFound = !checkFreeSpace(row, column);
                if (!moveNotFound) {
                    cpuMove[0] = row;
                    cpuMove[1] = column;
                }
            }
            return cpuMove;
        }
        cpuMove[0] = -1;
        cpuMove[1] = -2;
        return cpuMove;
    }

    private boolean checkFreeSpace(int row, int column) {
        if (board[row][column].equals("")) {
            board[row][column] = "O";
            return true;
        }
        return false;
    }

}
