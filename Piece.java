import java.util.Scanner;
public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) { // constructor
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // Determines if a piece should be promoted, and if so, prompts the user to do so
    public boolean promotePiece(Scanner scan) {
        if (this.equals(new Piece('\u2659', 0, 0, false)) && this.row==0) {
            /*
            If there is a white pawn at row 0
             */
            System.out.println("The white pawn you moved can be promoted.");
            System.out.println("Type B for bishop, K for knight, R for rook, or Q for queen.");
            /*
            Prompts player with interaction loop.
             */
            loop: while (true) {
                String newPiece = scan.nextLine();
                switch (newPiece) {
                    /*
                    Promotion to Bishop.
                     */
                    case "b":
                    case "B":
                        this.character = '\u2657';
                        break loop;

                    /*
                    Promotion to knight.
                     */
                    case "k":
                    case "K":
                        this.character = '\u2658';
                        break loop;

                    /*
                    Promotion to rook.
                     */
                    case "r":
                    case "R":
                        this.character = '\u2656';
                        break loop;

                    /*
                    Promotion to queen.
                     */
                    case "q":
                    case "Q":
                        this.character = '\u2655';
                        break loop;

                    /*
                    Invalid input.
                     */
                    default:
                        System.out.println("Invalid input. Please try again.");
                } // switch cases
            } // while loop
            return true;

        } else if (this.equals(new Piece('\u265f', 0, 0, true)) && this.row==7) {
            /*
            This is the exact same as the White pawn promotion but instead checks for a black pawn on row 7.
             */
            System.out.println("The black pawn you moved can be promoted.");
            System.out.println("Type B for bishop, K for knight, R for rook, or Q for queen.");
            loop: while (true) {
                String newPiece = scan.nextLine();
                switch (newPiece) {
                    case "b":
                    case "B":
                        this.character = '\u265d';
                        break loop;
                    case "k":
                    case "K":
                        this.character = '\u265e';
                        break loop;
                    case "r":
                    case "R":
                        this.character = '\u265c';
                        break loop;
                    case "q":
                    case "Q":
                        this.character = '\u265b';
                        break loop;
                    default:
                        System.out.println("Invalid input. Please try again.");
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);

            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);

            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);

            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);

            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);

            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);

            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current chess unicode character.
     * @return Unicode character.
     */
    public char getCharacter(){
        return this.character;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other){
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }


}
