package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import AI.MinimaxAI;
import AI.RandomAI;
import GameCtl.CodeUploadHandler;
import GameCtl.resetGame;
import GameEngine.GameEngine;

public class GomokuGameGUI extends JFrame {

    private Timer gameTimer; // 游戏循环的计时器
    private JPanel boardPanel; // 棋盘面板
    private int[][] boardState = new int[20][20];
    private RandomAI randomAI;
    private MinimaxAI minimaxAI;
    private boolean currentPlayerIsRandomAI = true; // 当前下棋的是随机AI

    public GomokuGameGUI() {
        // 初始化棋盘状态
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                boardState[row][col] = 0; // 0表示空
            }
        }
        // 初始化主界面
        initializeUI();
        // 创建并设置菜单
        createMenuBar();
        // 为主界面添加一个棋盘面板
        createBoardPanel();

        randomAI = new RandomAI();
        minimaxAI = new MinimaxAI();

        // 创建游戏循环计时器，每隔一段时间触发一次游戏逻辑
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 游戏逻辑
                startGame();
            }
        });
        gameTimer.setRepeats(true); // 设置为重复触发
        gameTimer.setCoalesce(true); // 如果计时器的触发事件被阻塞，确保只触发一个事件
    }

    private void initializeUI() {
        setTitle("五子棋游戏");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 加载图标并设置为窗口图标
        ImageIcon icon = new ImageIcon("src/asset/favicon/favicon.png");
        setIconImage(icon.getImage());
    } //标题以及图标

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("文件");
        menuBar.add(fileMenu);

        JMenuItem uploadCodeSubMenu = new JMenuItem("上传用户代码");
        fileMenu.add(uploadCodeSubMenu);
        uploadCodeSubMenu.addActionListener(e -> CodeUploadHandler.handleUploadCode());

        JMenu startMenu = new JMenu("游戏控制"); // 修正此处为小写startMenu
        menuBar.add(startMenu);

        JMenuItem startMenuItem = new JMenuItem("开始游戏");
        startMenu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 启动游戏
                startGame();
            }
        });

        JMenuItem resetGameMenuItem = new JMenuItem("重置游戏");
        startMenu.add(resetGameMenuItem);
        resetGameMenuItem.addActionListener(e -> new resetGame());

        JMenu exitMenu = new JMenu("退出");
        menuBar.add(exitMenu);

        JMenuItem exitMenuItem = new JMenuItem("退出");
        exitMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));
    }

    private void startGame() {
        // 创建游戏引擎并开始游戏
        GameEngine gameEngine = new GameEngine(boardState, currentPlayerIsRandomAI);
        gameEngine.makeMove();

        // 更新界面
        repaint(); // 重绘棋盘

        // 检查游戏是否结束
        if (isGameOver()) {
            // 游戏结束，处理游戏结果（你需要实现这部分逻辑）
//            handleGameResult();
            gameTimer.stop(); // 停止游戏循环
        }
    }


    private void createBoardPanel() {
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int boardSize = 20; // 棋盘大小，这里假设是20*20
                int cellSize = 21; // 每个格子的大小

                // 绘制棋盘
                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {
                        int x = col * cellSize;
                        int y = row * cellSize;

                        // 绘制格子边框
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);

                        // 绘制棋子
                        if (boardState[row][col] == 1) {
                            g.setColor(Color.RED); // 玩家1的棋子颜色
                            g.fillOval(x, y, cellSize, cellSize);
                        } else if (boardState[row][col] == 2) {
                            g.setColor(Color.BLUE); // 玩家2的棋子颜色
                            g.fillOval(x, y, cellSize, cellSize);
                        }
                    }
                }
            }
        };
        boardPanel.setPreferredSize(new Dimension(500, 500));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(boardPanel, BorderLayout.CENTER);
    }

    // 根据落子位置更新棋盘状态
    private void makeMove(int row, int col, int player) {
        boardState[row][col] = player;
        // 进行其他游戏状态更新，如判断胜负
    }

    // 检查游戏是否结束
    private boolean isGameOver() {
        // 实现游戏结束条件的判断逻辑
        // 如果游戏结束，返回true；否则，返回false
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GomokuGameGUI game = new GomokuGameGUI();
                game.setVisible(true);
            }
        });
    }
}
