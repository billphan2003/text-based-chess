public class King {
    // Instance variables
    private final int row;
    private final int col;
    private final boolean isBlack;

    /**
     * Constructor.
     *
     * @param row     The current row of the king.
     * @param col     The current column of the king.
     * @param isBlack The color of the king.
     */
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     *
     * @param board  The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Case 1: Adjacent movement
        return board.verifyAdjacent(this.row, this.col, endRow, endCol)

                // Case 2: Either it was not adjacent, or it was but that failed due to a friendly piece
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack);
    }
}

