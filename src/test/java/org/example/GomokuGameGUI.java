package org.example;

/**
 * Unit test for simple App.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GomokuGameGUI extends JFrame {
    private JPanel boardPanel;
    private int[][] board; // 棋盘状态，0表示空，1表示玩家1，2表示玩家2
    private int currentPlayer; // 当前玩家，初始为1

    public GomokuGameGUI() {
        initializeUI();
        createMenuBar();
        createBoardPanel();
        initializeBoard();
    }

    private void initializeUI() {
        setTitle("五子棋游戏");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 创建菜单项...
    }

    private void createBoardPanel() {
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int boardSize = 15; // 棋盘大小，这里假设是15x15
                int cellSize = 25; // 每个格子的大小

                // 绘制棋盘
                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {
                        int x = col * cellSize;
                        int y = row * cellSize;

                        // 绘制格子边框
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);

                        // 绘制棋子
                        if (board[row][col] == 1) {
                            g.setColor(Color.RED);
                            g.fillOval(x, y, cellSize, cellSize);
                        } else if (board[row][col] == 2) {
                            g.setColor(Color.BLACK);
                            g.fillOval(x, y, cellSize, cellSize);
                        }
                    }
                }
            }
        };

        // 添加鼠标点击事件监听器
        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = e.getY() / 20; // 根据鼠标点击位置计算行
                int col = e.getX() / 20; // 根据鼠标点击位置计算列

                // 检查是否可以下子，并更新棋盘状态
                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    repaint(); // 重新绘制棋盘

                    // 检查游戏是否结束
                    if (checkWin(currentPlayer)) {
                        // 当前玩家获胜
                        JOptionPane.showMessageDialog(null, "玩家 " + currentPlayer + " 获胜！");
//                resetGame(); // 重置游戏
                    } else {
                        // 切换玩家
                        currentPlayer = 3 - currentPlayer;
                    }
                }
            }
        });


        boardPanel.setPreferredSize(new Dimension(300, 300));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }

    private void initializeBoard() {
        int boardSize = 15; // 棋盘大小，这里假设是15x15
        board = new int[boardSize][boardSize];
        currentPlayer = 1; // 初始化为玩家1
    }

    private boolean isValidMove(int row, int col) {
        // 检查是否合法的下子位置
        return board[row][col] == 0; // 0表示空位置
    }

    private boolean checkWin(int player) {
        int boardSize = 15; // 棋盘大小，这里假设是15x15

        // 检查水平方向
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col <= boardSize - 5; col++) {
                boolean win = true;
                for (int i = 0; i < 5; i++) {
                    if (board[row][col + i] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // 检查垂直方向
        for (int row = 0; row <= boardSize - 5; row++) {
            for (int col = 0; col < boardSize; col++) {
                boolean win = true;
                for (int i = 0; i < 5; i++) {
                    if (board[row + i][col] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // 检查正对角线方向
        for (int row = 0; row <= boardSize - 5; row++) {
            for (int col = 0; col <= boardSize - 5; col++) {
                boolean win = true;
                for (int i = 0; i < 5; i++) {
                    if (board[row + i][col + i] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        // 检查反对角线方向
        for (int row = 0; row <= boardSize - 5; row++) {
            for (int col = 4; col < boardSize; col++) {
                boolean win = true;
                for (int i = 0; i < 5; i++) {
                    if (board[row + i][col - i] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GomokuGameGUITEST gameGUI = new GomokuGameGUITEST();
            gameGUI.setVisible(true);
        });
    }
}

