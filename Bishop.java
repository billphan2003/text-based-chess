public class Bishop {
    // Instance variables
    private final int row;
    private final int col;
    private final boolean isBlack;

    /**
     * Constructor.
     *
     * @param row     The current row of the bishop.
     * @param col     The current column of the bishop.
     * @param isBlack The color of the bishop.
     */
    public Bishop(int row, int col, boolean isBlack) {
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
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol)
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            // Case 1: Diagonal movement
            return true;
        }
        // Case 2: Either it wasn't diagonal, or it was but that failed
        return false;
    }
}
