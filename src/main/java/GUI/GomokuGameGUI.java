package GUI;

import javax.swing.*;
import java.awt.*;


import GameCtl.CodeUploadHandler;
import GameCtl.resetGame;

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

        JMenuItem resetGameMenuItem = new JMenuItem("重置游戏");
        fileMenu.add(resetGameMenuItem);
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
                // 在这里绘制棋盘和棋子
                // 根据游戏状态动态绘制棋盘
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
