public class Queen {
    // Instance variables
    private final int row;
    private final int col;
    private final boolean isBlack;

    /**
     * Constructor.
     *
     * @param row     The current row of the queen.
     * @param col     The current column of the queen.
     * @param isBlack The color of the queen.
     */
    public Queen(int row, int col, boolean isBlack) { // constructor
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
        /*
        See verifyVertical(), verifyHorizontal(), verifyDiagonal, and verifySourceAndDestination() in Board.java.
         */
        if (board.verifyVertical(this.row, this.col, endRow, endCol)
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            // Case 1: Vertical movement
            return true;

        } else if (board.verifyHorizontal(this.row, this.col, endRow, endCol)
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            // Case 2: Horizontal movement
            return true;

        } else if (board.verifyDiagonal(this.row, this.col, endRow, endCol)
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            // Case 3: Diagonal movement
            return true;
        }

        // Case 4: Either it wasn't in any of those directions, or it was but that failed
        return false;
    }
}