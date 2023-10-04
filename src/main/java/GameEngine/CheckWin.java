package GameEngine;

public class CheckWin {

    private int[][] board; // 棋盘状态，0表示空，1表示玩家1，2表示玩家2


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
}
