package GUI;

import javax.swing.*;
import java.awt.*;


import GameCtl.CodeUploadHandler;
import GameCtl.gameStart;
import GameCtl.resetGame;
import GameEngine.GpColor;
import GameEngine.OccJudgement;

public class GomokuGameGUI extends JFrame {
    private JPanel boardPanel; // 棋盘面板

    public GomokuGameGUI() {
        initializeUI();
        createMenuBar();
        createBoardPanel();
    }

    private void initializeUI() {
        setTitle("五子棋游戏");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 加载图标并设置为窗口图标
        ImageIcon icon = new ImageIcon("src/asset/favicon/favicon.png");
        setIconImage(icon.getImage());
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("文件");
        menuBar.add(fileMenu);

        JMenuItem uploadCodeSubMenu = new JMenuItem("上传用户代码");
        fileMenu.add(uploadCodeSubMenu);
        uploadCodeSubMenu.addActionListener(e -> CodeUploadHandler.handleUploadCode());

        JMenu StartMenu = new JMenu("游戏控制");
        menuBar.add(StartMenu);

        JMenuItem startMenuItem = new JMenuItem("开始游戏");
        StartMenu.add(startMenuItem);
        startMenuItem.addActionListener(e -> new gameStart());
        JMenuItem resetGameMenuItem = new JMenuItem("重置游戏");
        StartMenu.add(resetGameMenuItem);
        resetGameMenuItem.addActionListener(e -> new resetGame());


        JMenu exitMenu = new JMenu("退出");
        menuBar.add(exitMenu);

        JMenuItem exitMenuItem = new JMenuItem("退出");
        exitMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
    }


    private void createBoardPanel() {
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int boardSize = 15; // 棋盘大小，这里假设是15x15
                int cellSize = 22; // 每个格子的大小

                // 绘制棋盘
                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {
                        int x = col * cellSize;
                        int y = row * cellSize;

                        // 绘制格子边框
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);

                        // 绘制棋子（示例中用红色圆代表玩家1的棋子，蓝色圆代表玩家2的棋子）
                        if (OccJudgement.isOccupied(row, col)) {
                            g.setColor(GpColor.getPlayerColor(row, col));
                            g.fillOval(x, y, cellSize, cellSize);
                        }

                    }
                }
            }
        };
        boardPanel.setPreferredSize(new Dimension(300, 300));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GomokuGameGUI gameGUI = new GomokuGameGUI();
            gameGUI.setVisible(true);
        });
    }
}
