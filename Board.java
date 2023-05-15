public class Board {

    // Instance variables
    private final Piece[][] board;

    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods


    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. The Game class does not
    // directly call any other method of this class.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        Piece startPiece = board[startRow][startCol];
        /*
        First two cases: if you're trying to make a move where there isn't a piece, or if the piece you wanted
        to move can't be moved at that turn.
         */
        if (startPiece==null) {
            return false;
        } else if (startPiece.getIsBlack()!=isBlack) {
            return false;
        /*
        If the move is legal, set the new square to that piece (create a new, same piece), and the old square to null.
         */
        } else if (startPiece.isMoveLegal(this, endRow, endCol)) { // calls the specific piece's isMoveLegal().
            board[endRow][endCol] = new Piece(startPiece.getCharacter(), endRow, endCol, startPiece.getIsBlack());
            board[startRow][startCol] = null;
            return true;
        }
        return false;
    }

    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean blackKing = false;
        boolean whiteKing = false;


        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (board[i][j]==null) {assert true;} // if every square is empty then true

                /*
                Gives a true if either king is present
                 */
                else if (board[i][j].getCharacter()=='\u265a') {
                    blackKing = true;
                } else if (board[i][j].getCharacter()=='\u2654') {
                    whiteKing = true;
                }
            }
        }
        return !(whiteKing && blackKing); // if one/both kings are off the board then returns true.
    }

    // Used to make code easier
    String spacedNumbers = "01234567";
    public String toString() {
        /*
        Creates the board string by:
        Labeling the columns
        * Looping over spacedNumbers to label rows
        * Looping over each square to print the piece character and a line separating them, or a space if there
        isn't a piece at that square.
         */
        StringBuilder boardString = new StringBuilder("\u2001|0|1|2|3|4|5|6|7|\n");
        for (int i = 0; i<8; i++) {
            boardString.append(spacedNumbers.substring(i, i+1));
            for (int j = 0; j<8; j++) {
                if (board[i][j] == null) {
                    boardString.append("|" + "\u2001");
                } else {boardString.append("|" + board[i][j].toString());}
            }
            boardString.append("|\n");
        }
        return boardString.toString();
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    /*
    Loops through every square and sets it to null.
     */
    public void clear() {
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    // Ensures that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (0<=startRow && startRow<=7 && 0<=startCol && startCol<=7 && 0<=endRow && endRow<=7 && 0<=endCol && endCol<=7) {
            if (board[startRow][startCol]!=null) {
                if (board[startRow][startCol].getIsBlack()==isBlack) {
                    if (board[endRow][endCol]==null) {
                        return true;
                    } else return board[endRow][endCol].getIsBlack() != isBlack;
                }
            }
        }
        return false;
    }

    // Checks whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return (Math.abs(startRow-endRow)<=1 && Math.abs(startCol-endCol)<=1);
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        for (int i = startCol+Integer.signum(endCol-startCol); i!=endCol; i += Integer.signum(endCol-startCol)) {
            /*
            Is the space STRICTLY between the end and start square empty?
            Decrements or increments depending on going "left" or "right" across the board.
             */
            if (board[startRow][i]!=null) {
                return false;
            }
        }
        return (startRow==endRow);
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        for (int i = startRow+Integer.signum(endRow - startRow); i!=endRow; i += Integer.signum(endRow - startRow)) {
            /*
            Is the space STRICTLY between the end and start square empty?
            Decrements or increments depending on going "up" or "down" the board.
             */
            if (board[i][startCol]!=null) {
                return false;
            }
        }
        return (startCol==endCol);
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(endCol-startCol)!=Math.abs(endRow-startRow)) {
            return false; // immediately if it isn't a square diagonal, return false.
        }
        for (int i = 1; i<Math.abs(endRow-startRow); i += 1) {
            /*
            Is the space STRICTLY between the end and start square empty? If not then return false.
            Decrements or increments depending on going "up" or "down" the board.
             */
            if (board[startRow+i*Integer.signum(endRow-startRow)][startCol+i*Integer.signum(endCol-startCol)]!=null) {
                return false;
            }
        }
        return true;
    }
}