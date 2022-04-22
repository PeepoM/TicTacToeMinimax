import java.util.Random;

public class Model {
    private final int nrRows = 3;
    private final int nrCols = 3;
    private final int nrToWin = 3;

    public final char COMPUTER = 'C';
    public final char PLAYER = 'X';
    public final char BLANK = ' ';

    private final char[][] board;

    private char turn;

    public Model() {
        board = initializeBoard();
        turn = determineFirstMove();
    }

    public boolean isPlayerMoveValid(int move) {
        int row = (move - 1) / nrCols;
        int col = (move - 1) % nrCols;

        return move >= 1 && move <= (nrRows * nrCols) && board[row][col] == BLANK;
    }

    public void playerMove(int move) {
        int row = (move - 1) / nrCols;
        int col = (move - 1) % nrCols;

        board[row][col] = turn;
    }

    public void computerMove() {
        int bestMove = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        for (int row = 0; row < nrRows; row++) {
            for (int col = 0; col < nrCols; col++) {
                if (board[row][col] == BLANK) {
                    board[row][col] = COMPUTER;
                    int score = minimax(board, false);
                    board[row][col] = BLANK;

                    if (score > bestMove) {
                        bestMove = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        board[bestRow][bestCol] = COMPUTER;
    }

    private int minimax(char[][] board, boolean maximizingPlayer) {
        if (isAWin(COMPUTER, board))
            return 1;
        else if (isAWin(PLAYER, board))
            return -1;
        else if (isBoardFull(board))
            return 0;

        int value;

        if (maximizingPlayer) {
            value = Integer.MIN_VALUE;

            for (int row = 0; row < nrRows; row++) {
                for (int col = 0; col < nrCols; col++) {
                    if (board[row][col] == BLANK) {
                        board[row][col] = COMPUTER;
                        value = Math.max(value, minimax(board, false));
                        board[row][col] = BLANK;
                    }
                }
            }
        } else {
            value = Integer.MAX_VALUE;

            for (int row = 0; row < nrRows; row++) {
                for (int col = 0; col < nrCols; col++) {
                    if (board[row][col] == BLANK) {
                        board[row][col] = PLAYER;
                        value = Math.min(value, minimax(board, true));
                        board[row][col] = BLANK;
                    }
                }
            }
        }
        return value;
    }

    public boolean isBoardFull(char[][] board) {
        // Check if the board is full
        for (char[] row : board) {
            for (char piece : row) {
                if (piece == BLANK) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isGameOver() {
        return isAWin(turn, board) || isADraw(turn, board);
    }

    public boolean isAWin(char turn, char[][] board) {
        String strTurn = String.valueOf(turn);
        String winPattern = strTurn.repeat(nrToWin);

        // Check rows for win
        for (char[] row : board) {
            String strRow = String.valueOf(row);

            if (strRow.contains(winPattern)) {
                return true;
            }
        }

        // Check columns for win
        String strCol = "";
        for (int col = 0; col < nrCols; col++) {
            for (char[] row : board) {
                char piece = row[col];
                String strPiece = String.valueOf(piece);
                strCol = strCol.concat(strPiece);
            }
            if (strCol.contains(winPattern)) {
                return true;
            }
            strCol = "";
        }

        // Check diagonals for win
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn)
            return true;

        if (board[0][2] == turn && board[1][1] == turn && board[2][0] == turn)
            return true;

        return false;
    }

    public boolean isADraw(char turn, char[][] board) {
        return isBoardFull(board) && !isAWin(turn, board);
    }

    public void changeTurn() {
        if (turn == PLAYER) {
            turn = COMPUTER;
        } else {
            turn = PLAYER;
        }
    }

    private char[][] initializeBoard() {
        char[][] board = new char[nrRows][nrCols];

        resetBoard(board);

        return board;
    }

    public void resetBoard(char[][] board) {
        for (int row = 0; row < nrRows; row++) {
            for (int col = 0; col < nrCols; col++) {
                board[row][col] = BLANK;
            }
        }
    }

    private char determineFirstMove() {
        Random rnd = new Random();

        int randMove = rnd.nextInt(2);

        if (randMove == 1) {
            return PLAYER;
        } else {
            return COMPUTER;
        }
    }

    /* ------------- GETTERS AND SETTERS ------------- */

    public char[][] getBoard() {
        return board;
    }

    public char getTurn() {
        return turn;
    }
}
