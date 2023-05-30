/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Client extends javax.swing.JFrame implements Runnable {

    private String[] marks = {"X", "O"};
    private int myMark, otherMark;

    private final static int PLAYER_X = 0;
    private final static int PLAYER_O = 1;

    private final static int PLAYER_X_WON = 0;
    private final static int PLAYER_O_WON = 1;
    private final static int DRAW = 2;
    private final static int CONTINUE = 3;

    private int currentPlayer;
    private boolean myTurn = false;
    private boolean continuePlay = true;
    private boolean waiting = true;

    private int playerXWins = 0;
    private int playerOWins = 0;

    private JButton buttons[];
    private JButton[] winButtons;
    private JButton[][] board;

    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private JButton clickedButton;

    public Client() {
        initComponents();
        buttons = getButtons();
        board = getBoard();
        getClickedButton();
        execute();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelPxWins = new javax.swing.JLabel();
        labelPoWins = new javax.swing.JLabel();
        btnRst = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Velha");
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(153, 0, 153));
        jLabel1.setFont(new java.awt.Font("Jokerman", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jogo da Velha");
        jLabel1.setOpaque(true);

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn1.setFocusable(false);

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn2.setFocusable(false);

        btn3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn3.setFocusable(false);

        btn4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn4.setFocusable(false);

        btn5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn5.setFocusable(false);

        btn6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn6.setFocusable(false);

        btn7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn7.setFocusable(false);

        btn8.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn8.setFocusable(false);

        btn9.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn9.setFocusable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Jogador 'X':");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Jogador 'O':");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("VITÓRIAS");

        labelPxWins.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPxWins.setText("0");

        labelPoWins.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPoWins.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPxWins))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(labelPoWins))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelPxWins))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelPoWins))
                .addGap(21, 21, 21))
        );

        btnRst.setBackground(new java.awt.Color(255, 255, 0));
        btnRst.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRst.setText("RESETAR");
        btnRst.setFocusable(false);

        btnExit.setBackground(new java.awt.Color(255, 51, 51));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setText("SAIR");
        btnExit.setToolTipText("");
        btnExit.setFocusable(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setToolTipText("");

        btnNewGame.setBackground(new java.awt.Color(51, 204, 255));
        btnNewGame.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNewGame.setText("NOVO JOGO");

        labelStatus.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        labelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelStatus.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRst, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRst, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        Object[] options = {"Sim", "Não"};
        int option = JOptionPane.showOptionDialog(null, "Realmente deseja sair da partida?", "Alerta", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (option == 0) {
            System.exit(0);
        }


    }//GEN-LAST:event_btnExitActionPerformed

    private JButton[] getButtons() {
        buttons = new JButton[9];
        buttons[0] = btn1;
        buttons[1] = btn2;
        buttons[2] = btn3;
        buttons[3] = btn4;
        buttons[4] = btn5;
        buttons[5] = btn6;
        buttons[6] = btn7;
        buttons[7] = btn8;
        buttons[8] = btn9;
        return buttons;
    }

    private JButton[][] getBoard() {
        board = new JButton[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[k];
                k++;
            }
        }
        return board;
    }

    private void execute() {

        try {
            socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            disableButtons();
            currentPlayer = fromServer.readInt();

            if (currentPlayer == PLAYER_X) {
                myMark = PLAYER_X;
                otherMark = PLAYER_O;
                labelStatus.setText("Esperando outro jogador...");
                labelTitle.setText("VOCE É O JOGADOR " + "'" + marks[myMark] + "'");
                if (fromServer.readInt() == 0) {
                    labelStatus.setText("Sua vez! Faça a jogada...");
                    myTurn = true;
                    enableButtons();
                }

            } else if (currentPlayer == PLAYER_O) {
                myMark = PLAYER_O;
                otherMark = PLAYER_X;
                labelStatus.setText("Esperando jogada...");
                labelTitle.setText("VOCE É O JOGADOR " + "'" + marks[myMark] + "'");
            }

            while (continuePlay) {
                if (myTurn) {
                    try {
                        waitForPlayerAction();
                        sendMove();
                        receiveInfo();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        receiveInfo();
                        waitForPlayerAction();
                        sendMove();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }

        waiting = true;
    }

    private void sendMove() {
        drawMyMark(clickedButton);
        int k = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (buttons[k] == clickedButton) {
                    try {
                        toServer.writeInt(row);
                        toServer.writeInt(column);
                        toServer.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                k++;
            }
        }
        disableButtons();

    }

    private void receiveInfo() {
        try {
            int status = fromServer.readInt();

            switch (status) {
                case PLAYER_X_WON:
                    continuePlay = false;
                    if (myMark == PLAYER_X) {
                        labelStatus.setText("Você venceu!");
                        getWinButtons(status);

                    } else if (myMark == PLAYER_O) {
                        labelStatus.setText("O jogador: '" + marks[otherMark] + "' venceu...");
                        receiveMove();
                        getWinButtons(status);
                    }
                    
                    break;
                case PLAYER_O_WON:
                    continuePlay = false;
                    if (myMark == PLAYER_O) {
                        labelStatus.setText("Você venceu!");
                        getWinButtons(status);
                    } else if (myMark == PLAYER_X) {
                        labelStatus.setText("O jogador: '" + marks[otherMark] + "' venceu...");
                        receiveMove();
                        getWinButtons(status);
                    }
                    
                    break;
                case DRAW:
                    continuePlay = false;
                    labelStatus.setText("Deu velha!");

                    if (myMark == 'O') {
                        receiveMove();
                    }
                    break;
                case CONTINUE:
                    receiveMove();
                    enableButtons();
                    labelStatus.setText("Sua vez! Faça a jogada...");
                    myTurn = true;
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void receiveMove() throws IOException {
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        drawOtherMark(row, column);
    }

    private void drawMyMark(JButton button) {
        button.setText(marks[myMark]);
        button.setForeground(Color.BLUE);
        currentPlayer = otherMark;
    }

    private void drawOtherMark(int row, int column) {
        board[row][column].setText(marks[otherMark]);
        board[row][column].setForeground(Color.ORANGE);
        currentPlayer = myMark;

    }

    private void getClickedButton() {
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getText().equals("")) {
                        clickedButton = button;
                        myTurn = false;
                        labelStatus.setText("Esperando Jogada...");
                        waiting = false;
                    }

                }
            });
        }
    }

    public JButton[] getWinButtons(int status) {
        try {
        int linhas = fromServer.readInt();
        int colunas = fromServer.readInt();
        
        int[][] matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = fromServer.readInt();
                System.out.println(matriz[i][j]);
            }
        }
        
        winButtons = new JButton[3];
        winButtons[0] = board[matriz[0][0]][matriz[0][1]];
        winButtons[1] = board[matriz[1][0]][matriz[1][1]];
        winButtons[2] = board[matriz[2][0]][matriz[2][1]];
        
        enableWinButtons();
        
        } catch(IOException e) {
            e.printStackTrace();
        }
        return winButtons;
    }
    
    private void enableWinButtons() {
        for(int i = 0; i<3; i++) {
            winButtons[i].setEnabled(true);
            if(marks[myMark].equals(winButtons[i].getText())){
                winButtons[i].setBackground(Color.GREEN);
            } else {
                winButtons[i].setBackground(Color.RED);
            }
        }
    }
    

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    private void restoreButtons() {
        enableButtons();
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(new JButton().getBackground());
        }
    }

    private void setWins() {
        labelPxWins.setText(Integer.toString(playerXWins));
        labelPoWins.setText(Integer.toString(playerOWins));

    }

    private boolean boardFilledUp() {
        for (int i = 0; i < buttons.length; ++i) {
            if (buttons[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new Client().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnRst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelPoWins;
    private javax.swing.JLabel labelPxWins;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables

}
