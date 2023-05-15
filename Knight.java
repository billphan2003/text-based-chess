public class Knight {
    // Instance variables
    private final int row;
    private final int col;
    private final boolean isBlack;

    /**
     * Constructor.
     *
     * @param row     The current row of the knight.
     * @param col     The current column of the knight.
     * @param isBlack The color of the knight.
     */
    public Knight(int row, int col, boolean isBlack) { // constructor
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

        // Case 1: Normal movement of a knight
        // or case 2: either it was not to an available square, or it was but there was a friendly piece (returns false)
        return ((Math.abs(this.row - endRow) == 1 && Math.abs(this.col - endCol) == 2)
                || (Math.abs(this.row - endRow) == 2 && Math.abs(this.col - endCol) == 1))
                && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack);
    }
}