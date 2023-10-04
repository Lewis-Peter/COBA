package AI;

// RandomAI.java
import java.util.Random;

public class RandomAI {
    public int[] makeMove(int[][] board) {
        int boardSize = board.length;
        Random random = new Random();

        while (true) {
            int row = random.nextInt(boardSize);
            int col = random.nextInt(boardSize);

            if (board[row][col] == 0) {
                return new int[]{row, col};
            }
        }
    }
}
