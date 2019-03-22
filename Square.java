import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

/**
 * Models a square button. Can be used with the Board class to create 
 * instances of square and display them on the screen.
 * Furthermore, can be used to develop a simple draughts game.
 */
public class Square extends JButton {

    // The following instance variables define the information needed to represent a square button.
    //Supporting variables were added for further development of the draughts game.

    private ImageIcon icon;                                             //Icon displaying on the square; universal - change as you go.
    private ImageIcon white = new ImageIcon("white.png");               //Icon reserved for displaying a white draughts piece.
    private ImageIcon emptyWhite = new ImageIcon("empty.png");          //Icon reserved for displaying a white square.
    private ImageIcon red = new ImageIcon("red.png");                   //Icon reserved for displaying a red draughts piece.
    private ImageIcon red_king = new ImageIcon("red-king.png");         //Icon reserved for displaying a red king draughts piece.
    private ImageIcon white_king = new ImageIcon("white-king.png");     //Icon reserved for displaying a white king draughts piece.
    private ImageIcon selected = new ImageIcon("selected.png");         //Icon reserved for displaying a yellow square indication an object being selected.
    private boolean isEnemyRedLeft = false;                             //Boolean operator to determine any red pieces adjacent to the left of a white piece.
    private boolean isEnemyRedRight = false;                            //Boolean operator to determine any red pieces adjacent to the right of a white piece.
    private boolean isEnemyWhiteLeft = false;                           //Boolean operator to determine any white pieces adjacent to the left of a red piece.
    private boolean isEnemyWhiteRight = false;                          //Boolean operator to determine any white pieces adjacent to the right of a red piece.
    private int Ylocation;                                              //Y coordinate of the square in a grid
    private int Xlocation;                                              //X coordinate of the square in a grid
    private int whatPiece;                                              //Information on what piece is currently placed on a square.
    private static final int NONE_WHITE = 0, NONE_BLACK = 1;            //Black and white squares.
    private static final int WHITE = 2, RED = 3;                        //Available pieces.
    private static final int RED_KING = 4, WHITE_KING = 5;              //Available pieces.


     /**
     * Obtains the current position of the square.
     * @return the X coordinate of this square within the Board grid.
     */
    public int getXlocation() {
        return Xlocation;
    }
    
    /**
     * Obtains the current position of the square.
     * @return the Y coordinate of this square within the Board grid.
     */
    public int getYlocation() {
        return Ylocation;
    }
    
    /**
     * Obtains the status of a square; pieces that are currently placed (if any).
     * @return NONE_WHITE, NONE_BLACK, WHITE, RED, RED_KING, WHITE_KING.
     */
    public int getPiece() {
        return whatPiece;
    }
    
    /**
     * Updates the status of a square; useful when adding/removing
     * the pieces, changing one piece for another.
     * @param piece the new piece type that was placed on the square(if any).
     * <BR><BR>
     * Permissable values are:
     * NONE_WHITE NONE_BLACK WHITE RED RED_KING WHITE_KING
     */
    public void setPiece(int piece) {
        whatPiece = piece;
    }

    /**
     * Checks if the piece on the square is the same as the one
     * provided as a parameter.
     * @param n the type of piece that is to be compared
     * @return true if the pieces are the same, false otherwise
     * <BR><BR>
     * Permissable values are:
     * NONE_WHITE NONE_BLACK WHITE RED RED_KING WHITE_KING
     */
    public boolean pieceEquals(int n) {
        if (this.getPiece() == n) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the square provided as a parameter is a valid draughts game move.
     * @param sqr the square that a draughts piece is to be moved to
     * @return true if a square is a valid move, false otherwise
     */
    public boolean canMoveTo(Square sqr) {
        
        int x = sqr.getXlocation();
        int y = sqr.getYlocation();

        int a = this.getXlocation();
        int b = this.getYlocation();


        if (this.pieceEquals(WHITE)) {
            
            /*If the current piece is white the square provided as a parameter can only be 
              x-1/x+1 and y-1 from the current square and has to be empty*/
            if (sqr.pieceEquals(NONE_WHITE) && b - 1 == y && (a - 1 == x || a + 1 == x)) {
                return true;
            }
            //If the piece is not white we want to check if it is red and obtain adjacency details for left and right side
            else if (sqr.pieceEquals(RED) && b - 1 == y && a - 1 == x) {
                isEnemyRedLeft = true;
                return false;
            }
            else if (sqr.pieceEquals(RED) && b - 1 == y && a + 1 == x) {
                isEnemyRedRight = true;
                return false;
            }
        }
        /*If the current piece is red the square provided as a parameter can only be
          x-1/x+1 and y+1 from the current square and has to be empty*/
        else if (this.pieceEquals(RED)) {

            if (sqr.pieceEquals(NONE_WHITE) && b + 1 == y && (a - 1 == x || a + 1 == x)) {
                return true;
            }
            /*If the piece is not red we want to check if it is white and obtain 
              adjacency details for left and right side*/
            else if (sqr.pieceEquals(WHITE) && b + 1 == y && a + 1 == x) {
                isEnemyWhiteRight = true;
                return false;
            }
            else if (sqr.pieceEquals(WHITE) && b + 1 == y && a - 1 == x) {
                isEnemyWhiteLeft = true;
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if the square provided as parameter can be a valid
     * move when executing a jump over an enemy draughts piece
     * @param sqr the square that the piec is to jump to
     * @return true if the parameter is a valid move, false otherwise
     */
    public boolean canJumpTo(Square sqr) {
        
        int x = sqr.getXlocation();
        int y = sqr.getYlocation();

        int a = this.getXlocation();
        int b = this.getYlocation();


        if (this.pieceEquals(WHITE)) {
            
            /*If the current piece is white the square provided as a parameter can only be 
            x-2/x+2 and y-2 from the current square and there has to be an enemy piece 
            adjacent to the left or right*/
            if (sqr.pieceEquals(NONE_WHITE) && b - 2 == y && a - 2 == x && isEnemyRedLeft == true) {
                return true;
            }
            else if (sqr.pieceEquals(NONE_WHITE) && b - 2 == y && a + 2 == x && isEnemyRedRight == true) {
                return true;
            }
        }
        else if (this.pieceEquals(RED)) {
           
            /*If the current piece is red the square provided as a parameter can only be 
            x-2/x+2 and y+2 from the current square and there has to be an enemy piece 
            adjacent to the left or right*/
            if (sqr.pieceEquals(NONE_WHITE) && b + 2 == y && a - 2 == x && isEnemyWhiteLeft == true) {
                return true;
            }
            else if (sqr.pieceEquals(NONE_WHITE) && b + 2 == y && a + 2 == x && isEnemyWhiteRight == true) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Highlights the valid moves of the current piece.
     * @param square indicates the square to highlight provided
     * that it is a casual and valid move; this method uses 
     * canMoveTo() method to check that.
     * @param square2 indicates the square to highlight provided that
     * it is a jump and a valid move; this method uses canJumpTo()
     * method to check that.
     */
    public void highlightMove(Square square, Square square2) {

        boolean bool = this.canMoveTo(square);

        if (this.canJumpTo(square2) == true) {                       //If jumping is available ignore other moves.
            bool = false;                                  
            square2.setIcon(selected);
            return;
        }
        else if (bool == true) {                                    //If not, proceed.
            square.setIcon(selected);
        }
        return;
    }

    /**
     * Removes any highlight from the array of squares 
     * after no piece was placed on it.
     * @param sq array of square buttons
     */
    public void removeHighlight(Square[][] sq) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (sq[i][j].getPiece() == NONE_WHITE) {
                    sq[i][j].setIcon(emptyWhite);
                }
                else {
                    continue;
                }
            }
        }
        return;
    }

    /**
     * Moving the current piece to the square provided
     * as a parameter (changing the icons displayed).
     * @param sq square that the piece is to be placed on
     */
    public void moveTo(Square sq) {
        
        if (sq.getYlocation() == 0 && this.pieceEquals(WHITE)) {            //Check if the white piece reached the end of the board
            sq.setIcon(white_king);                                         //If yes, change it to king.
            sq.setPiece(WHITE_KING);                                        //Update the square status.
        }
        else if (sq.getYlocation() == 7 && this.pieceEquals(RED)) {         //Same for the red piece.
            sq.setIcon(red_king);
            sq.setPiece(RED_KING);
        }
        else if (this.pieceEquals(WHITE)) {                                 //If current piece is white place also a white
            sq.setIcon(white);                                              //piece on new square, then update the square status.
            sq.setPiece(WHITE);                                             
        }
        else if (this.pieceEquals(RED)) {                                   //Same for the red piece.
            sq.setIcon(red);
            sq.setPiece(RED);
        }
        this.setIcon(emptyWhite);                                           //When done, change the current square to display
        this.setPiece(NONE_WHITE);                                          //an empty white square.

        isEnemyRedLeft = false;                                             //Update each boolean so that analysis of enemies
        isEnemyWhiteLeft = false;                                           //nearby can be conducted again.
        isEnemyRedRight = false;
        isEnemyWhiteRight = false;
    }


     /**
     *
     * Constructor. Create a new instance of a Square.
     *
     * @param x The X co-ordinate in the Board grid where this Square will initially be located.
     * @param y The Y co-ordinate in the Board grid where this Square will initially be located.
     * @param pi the type of a piece (if any) that this Square will contain.
     * @see setPiece for a description of permissable piece types.
     *
     */
    public Square(int x, int y, int pi) {

        Xlocation = x;
        Ylocation = y;

        if (pi == WHITE) {                                  //Depending on the value of pi, allocated an appropriate
            whatPiece = pi;                                 //image to the icon variable.
            icon = new ImageIcon("white.png");
        }
        else if (pi == RED) {
            whatPiece = pi;
            icon = new ImageIcon("red.png");
        }
        else if (pi == NONE_WHITE) {
            whatPiece = pi;
            icon = new ImageIcon("empty.png");
        }
        else {
            whatPiece = NONE_BLACK;
            icon = new ImageIcon("empty2.png");
        }
        this.setIcon(icon);                                 //Set the square to display that image when created.
    }
}