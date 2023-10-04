package org.example;

import AI.MinimaxAI;
import AI.RandomAI;

import javax.swing.*;

public class GomokuGameGUITest extends JFrame {
    private int[][] boardState = new int[15][15];
    private RandomAI randomAI;
    private MinimaxAI minimaxAI;
    private boolean currentPlayerIsRandomAI = true; // 当前下棋的是随机AI

    public void GomokuGameGUI() {
        // 初始化棋盘状态
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                boardState[row][col] = 0; // 0表示空
            }
        }

        // 初始化AI实例
        randomAI = new RandomAI();
        minimaxAI = new MinimaxAI();

        // 开始游戏
        startGame();
    }

    private void startGame() {
        while (true) {
            // 根据当前玩家调用相应的AI来生成落子坐标，并更新棋盘状态
            int[] move;
            if (currentPlayerIsRandomAI) {
                move = randomAI.makeMove(boardState);
            } else {
                move = minimaxAI.makeMove(boardState);
            }

            makeMove(move[0], move[1], currentPlayerIsRandomAI ? 1 : 2);

            // 切换玩家
            currentPlayerIsRandomAI = !currentPlayerIsRandomAI;

            // 检查游戏是否结束
            if (isGameOver()) {
                break;
            }
        }

        // 游戏结束后的处理逻辑
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
        return false; // 示例，需要根据实际情况实现
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GomokuGameGUI gameGUI = new GomokuGameGUI();
            gameGUI.setVisible(true);
        });
    }
}
