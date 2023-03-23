package TicTacToe;

import java.util.Random;

public class Computer extends Player {
    public Computer(String marker) {
        super(marker);
    }

    public int getMove(String[][] board) {
        int move = -1;
        int size = board.length;

        while (move == -1) {
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);

            if (board[x][y] == null) {
                move = x * size + y;
            }
        }

        return move;
    }
}