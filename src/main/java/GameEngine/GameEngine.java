package GameEngine;

import AI.MinimaxAI;
import AI.RandomAI;

public class GameEngine {
    private int[][] boardState;
    private boolean currentPlayerIsRandomAI;
    private RandomAI randomAI;
    private MinimaxAI minimaxAI;

    public GameEngine(int[][] boardState, boolean currentPlayerIsRandomAI) {
        this.boardState = boardState;
        this.currentPlayerIsRandomAI = currentPlayerIsRandomAI;
        randomAI = new RandomAI();
        minimaxAI = new MinimaxAI();
    }

    public void makeMove() {
        int[] move;

        if (currentPlayerIsRandomAI) {
            move = randomAI.makeMove(boardState);
        } else {
            move = minimaxAI.makeMove(boardState);
        }

        int row = move[0];
        int col = move[1];

        // 根据落子位置更新棋盘状态
        boardState[row][col] = currentPlayerIsRandomAI ? 1 : 2;

        // 切换下一个玩家
        currentPlayerIsRandomAI = !currentPlayerIsRandomAI;

        // 更新界面（这部分需要你实现）
        updateUI();

        // 检查游戏是否结束
        if (isGameOver()) {
            // 游戏结束，显示胜利者或平局信息（这部分需要你实现）
            showGameResult();
        }
    }

    // 添加一个方法，用于检查游戏是否结束
    private boolean isGameOver() {
        // 实现游戏结束条件的判断逻辑
        // 如果游戏结束，返回true；否则，返回false
        return false; // 你需要根据五子棋的规则来实现这部分逻辑
    }

    // 添加一个方法，用于更新界面
    private void updateUI() {
        // 在棋盘上更新棋子状态（这部分需要你实现）
    }

    // 添加一个方法，用于显示游戏结果
    private void showGameResult() {
        // 在界面上显示胜利者或平局信息（这部分需要你实现）
    }
}
