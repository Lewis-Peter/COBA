package GameCtl;

import AI.RandomAI;
import AI.MinimaxAI;

public class GameController {
    private int[][] boardState = new int[15][15]; // 棋盘状态
    private RandomAI randomAI = new RandomAI(); // 随机AI
    private MinimaxAI minimaxAI = new MinimaxAI(); // MiniMax AI
    private boolean currentPlayerIsRandomAI = true; // 当前下棋的是随机AI

    public void startGame() {
        while (true) {
            if (currentPlayerIsRandomAI) {
                // 随机AI下棋
                int[] move = randomAI.makeMove(boardState);
                makeMove(move[0], move[1], 1); // 假设1表示随机AI
            } else {
                // MiniMax AI下棋
                int[] move = minimaxAI.makeMove(boardState);
                makeMove(move[0], move[1], 2); // 假设2表示MiniMax AI
            }

            // 检查游戏是否结束
            if (isGameOver()) {
                break;
            }

            // 切换玩家
            currentPlayerIsRandomAI = !currentPlayerIsRandomAI;
        }
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
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
