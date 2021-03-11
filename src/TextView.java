public class TextView {
    public void displayWelcomeScreen() {
        System.out.println("\nWelcome to my Tic Tac Toe implementation using the Minimax algorithm!\n");

        System.out.println("        ^ ^           ");
        System.out.println("       (O,O)          ");
        System.out.println("       (   )          ");
        System.out.println("--------\"-\"--------\n");

        System.out.println("To play the game enter numbers from 1 - 9");
        System.out.println("The board looks like this\n");
    }

    public void displayGameResult(boolean win, boolean draw, char turn) {
        if (draw) {
            System.out.println("Game ended in a draw!\n");
        } else if (win) {
            System.out.println("Player " + turn + " is the winner!\n");
        }

    }

    public boolean restartGame() {
        String answer;

        do {
            System.out.print("Do you wish to play again (yes/no)? ");
            answer = InputUtil.readStringFromUser().toLowerCase();

            if (!(answer.equals("yes") || answer.equals("no"))) {
                System.out.println("Please enter either yes or no!");
            }
        } while (!(answer.equals("yes") || answer.equals("no")));

        String faceToPrint = answer.equals("yes") ? "Let's go (◕‿◕✿) !" : "ಠ_ಠ O'RLY?";
        System.out.println(faceToPrint);

        return answer.equals("yes");
    }

    public int processMove(Model model) {
        int move;

        do {
            System.out.print("Please enter a move: ");

            move = InputUtil.readIntFromUser();

            if (!model.isPlayerMoveValid(move)) {
                System.out.println("Not a valid input!");
            }

        } while (!model.isPlayerMoveValid(move));

        return move;
    }

    public final void displayBoard(char[][] board) {
        int nrRows = board.length;
        int nrCols = board[0].length;

        for (int row = 0; row < nrRows; row++) {
            for (int col = 0; col < nrCols; col++) {
                char piece = board[row][col];

                if (col != nrCols - 1) {
                    System.out.print(" " + piece + " |");
                } else {
                    System.out.print(" " + piece + " ");
                }
            }

            System.out.println();

            if (row != nrRows - 1) {
                // Insert three dashes for every piece and one dash for every vertica bar
                String rowDivider = "---".repeat(nrCols) + "-".repeat(nrCols - 1);
                System.out.println(rowDivider);
            }
        }

        System.out.println();
    }
}
