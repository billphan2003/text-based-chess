import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        /*
        Boilerplate which creates new starting board and sets the current player to White.
         */
        Scanner move = new Scanner(System.in);
        Board playBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", playBoard);
        String currentPlayer = "white";
        boolean isBlack = false;

        /*
        Three nested while loops which implement major game functions; commented per loop.
         */
        while (true) { // outermost loop is defined mostly by
            // stopping when a winner has been declared, aka one/both kings is not present.

            /*
            User interaction code which prints out prompts for the player.
             */
            System.out.println("Board:");
            System.out.println(playBoard);
            System.out.println("It is currently " + currentPlayer + "'s turn to play.");
            System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");

            /*
            The piece variables for the game.
             */
            int startRow;
            int startCol;
            int endRow;
            int endCol;


            while(true) {
                /*
                Second-level while loop is mostly defined by an "egregiously bad move" conditional where a move is
                simply out of bounds of the board, and prints out error messages accordingly.
                 */

                while (true) {
                    /*
                    Third-level while loop actually takes in user input calling relevant Scanner functions
                    and comprehending user input (Int Int Int Int) by parsing users' string moves into numbers and
                    assigns them to the piece variables.
                     */
                    try {
                        String moveInfo = move.nextLine();
                        startRow = Character.getNumericValue(moveInfo.charAt(0));
                        startCol = Character.getNumericValue(moveInfo.charAt(2));
                        endRow = Character.getNumericValue(moveInfo.charAt(4));
                        endCol = Character.getNumericValue(moveInfo.charAt(6));

                        // Checks for correct spacing of input (Int Int Int Int)
                        if (moveInfo.charAt(1)==' ' && moveInfo.charAt(3) == ' '
                                && moveInfo.charAt(5) == ' ' && moveInfo.length()==7) {
                            break;
                        } else {
                            throw new IllegalArgumentException(); // and if fails, throw this error.
                        }

                    } catch (Exception e) { // then print out an error message.
                        System.out.println("Please enter an answer that fits the format.");
                    }
                }

                if ((startRow <= 7 && startCol <= 7 && endRow <= 7 && endCol <= 7) &&
                        playBoard.movePiece(startRow, startCol, endRow, endCol, isBlack)) {
                    break; // Very quick, important check to see if the move is even "in the board."
                } else {
                    System.out.println("That is an illegal move. Please try again.");
                }
            }
            if (playBoard.isGameOver()) {
                /*
                Given by the Board.java class, and happens when one or both kings is not on the board.
                 */
                System.out.println("The winner is " + currentPlayer + "!");
                break;

                /*
                Constant check for if there is any piece which is eligible for promotion.
                 */
            } else {playBoard.getPiece(endRow, endCol).promotePiece(move);}

            /*
            Loops between White and Black's turn to play
             */
            if (isBlack) {
                currentPlayer = "white";
                isBlack = false;
            } else {
                currentPlayer = "black";
                isBlack = true;
            }
        } // outermost while loop
    } // main
}
