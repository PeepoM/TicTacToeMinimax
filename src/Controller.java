public class Controller {
    private final Model model;
    private final TextView view;

    public Controller(Model model, TextView view) {
        this.model = model;
        this.view = view;
    }

    public void startSession() {
        char turn = model.getTurn();
        char[][] board = model.getBoard();

        do {
            view.displayWelcomeScreen();
            model.resetBoard(board);
            view.displayBoard(board);

            while (!model.isGameOver()) {
                board = model.getBoard();
                turn = model.getTurn();

                if (turn == model.PLAYER) {
                    int move = view.processMove(model);
                    model.playerMove(move);
                } else if (turn == model.COMPUTER) {
                    model.computerMove();
                }

                view.displayBoard(board);

                if (!model.isGameOver()) {
                    model.changeTurn();
                }
            }

            boolean isWin = model.isAWin(turn, board);
            boolean isDraw = model.isADraw(turn, board);

            view.displayGameResult(isWin, isDraw, turn);
        } while (view.restartGame());
    }
}
