import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Models a board that is used to build a simple draughts game.
 * When combined with the Square class can display black and white
 * tiles as well as white and red pieces.
 */

public class Board implements ActionListener {

    private JFrame checkers = new JFrame("Draughts");              //Creates simple window to diplay graphical objects.
    private JPanel panel = new JPanel();                           //Creates panel to add the objects to.
    private ImageIcon white = new ImageIcon("white.png");          //Icon reserved for displaying a white draughts piece.
    private ImageIcon red = new ImageIcon("red.png");              //Icon reserved for displaying a red draughts piece.
    private int firstX, firstY, state = 0;                         //X and Y location of the square tile along with the state indicating first click or a second click.
    private static final int NONE_WHITE = 0, NONE_BLACK = 1;       //Information on what piece is currently placed on a square.
    private static final int WHITE = 2, RED = 3;                   //Black and white squares, white and red pieces.
    private GridLayout layout = new GridLayout(8,8,0,0);           //Managing the layout of the squares in an 8x8 grid.
    private Square[][] s = new Square[8][8];                       //2-D 8x8 array of the Square instances.


    /**
     *Constructor. Create a new instance of a Board.
     * Does not take any parameters, models an 8x8 grid of black
     * and white squares depending on their location.
     * Adds newly created square instances to the s array.
     * In a grid the Y parameter is taken first indicating the row
     * number and X parameter is second for a column number.
     */
    public Board() {

       for (int r=0; r<8; r++) {
           for (int c=0; c<8; c++) {
               if (r%2 == 0 && c%2 == 0) {                          //Creating black square instances for the row and column numbers that are even.
                   s[r][c] = new Square(c, r, NONE_BLACK);
               }
               else if (r%2 == 1 && c%2 == 1) {                    //Creating other black square instances for the row and column number that are odd.
                s[r][c] = new Square(c, r, NONE_BLACK);
               }
               else {
                s[r][c] = new Square(c, r, NONE_WHITE);            //For the row and column numbers that left, creating white square instances.
               }
               panel.add(s[r][c]);                                 //Adding each instance of the square to the panel.
               s[r][c].addActionListener(this);                    //Adding a functionality of clicking on a square and expecting an event.
               panel.setLayout(layout);                           //Setting the layout in panel to grid layout.
           }
       }

        checkers.setContentPane(panel);
        checkers.setSize(800, 800);
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setResizable(false);
        checkers.setVisible(true);

    }

    /**
     * Adds white pieces to this Board to the square
     * indicated in parameters.
     * @param x X location of the square instance in a grid
     * @param y Y location of the square instance in a grid
     */
    public void addWhitePieces(int x, int y) {
        s[x][y].setIcon(white);
        s[x][y].setPiece(WHITE);
    }

     /**
     * Adds red pieces to this Board to the square
     * indicated in parameters.
     * @param x X location of the square instance in a grid
     * @param y Y location of the square instance in a grid
     */
    public void addRedPieces(int x, int y) {
        s[x][y].setIcon(red);
        s[x][y].setPiece(RED);
    }


    /**
     * Executes a particular event when a Square button is clicked on.
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //Checks if the source of an event is one of the squares in a grid.
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (source == s[j][i] && state == 0) {                                  //If it is in a grid and any square has not been clicked on before (state = 0)
                    if (s[j][i].pieceEquals(WHITE) || s[j][i].pieceEquals(RED)) {       //Check if the piece placed on the square invoking an event is either
                        if (i == 0) {                                                   //white or red.
                            if (s[j][i].pieceEquals(WHITE))
                                if (j==1) {                                             //Set additional conditions for pieces moving alongside side edges.
                                    s[j][i].highlightMove(s[j-1][i+1], s[j-1][i+1]);
                                }
                                else {
                                    s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                                }
                            else {
                                s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            }
                        }
                        else if (i == 7) {
                            if (s[j][i].pieceEquals(WHITE))                             //Set additional conditions for pieces moving alongside side edges.
                                s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                            else {
                                if (j==6) {
                                    s[j][i].highlightMove(s[j+1][i-1], s[j+1][i-1]);
                                }
                                else {
                                    s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                                }
                            }
                        }
                        else if (i == 1) {                                              //Set additional conditions for pieces that may go out of the board
                            if (s[j][i].pieceEquals(WHITE)) {                           //when jumping over is detected.
                                    s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                                    s[j][i].highlightMove(s[j-1][i-1], s[j-2][i+2]);
                            }
                            else {
                                if (i == 1 && j == 6) {
                                    s[j][i].highlightMove(s[j+1][i+1], s[j+1][i+1]);
                                    s[j][i].highlightMove(s[j+1][i-1], s[j+1][i+1]);
                                }
                                else {
                                    s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                                    s[j][i].highlightMove(s[j+1][i-1], s[j+2][i+2]);
                                }
                            }
                        }
                        else if (i==6) {                                                //Set additional conditions for pieces that may go out of the board
                            if (s[j][i].pieceEquals(WHITE)) {                           //when jumping over is detected.
                                if (j ==1) {
                                    s[j][i].highlightMove(s[j-1][i-1], s[j-1][i-1]);
                                    s[j][i].highlightMove(s[j-1][i+1], s[j-1][i-1]);
                                }
                                else {
                                    s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                                    s[j][i].highlightMove(s[j-1][i+1], s[j-2][i-2]);
                                }
                            }
                            else {
                                s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                                s[j][i].highlightMove(s[j+1][i+1], s[j+2][i-2]);
                            }
                        }
                        else if (j+1>7 && s[j][i].pieceEquals(WHITE)) {                 //Set additional conditions for pieces starting from the bottom edge
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);            //when moving by one tile.
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                        }
                        else if (j+2>7 && s[j][i].pieceEquals(WHITE)) {
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                        }
                        else if (j+2>7 && s[j][i].pieceEquals(RED)) {                   //Set additional conditions for pieces starting from the top edge
                            s[j][i].highlightMove(s[j+1][i+1], s[j-1][i+1]);            //and reaching the bottom edge.
                            s[j][i].highlightMove(s[j+1][i-1], s[j-1][i-1]);
                        }
                        else if (j-1<0 && s[j][i].pieceEquals(RED)) {                   //Set additional conditions for pieces starting from the top edge
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);            //when moving by one tile.
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                        }
                        else if (j-2<0 && s[j][i].pieceEquals(RED)) {                   //Set additional conditions for pieces starting from the top edge.
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                        }
                        else if (j-2<0 && s[j][i].pieceEquals(WHITE)) {                 //Set additional conditions for pieces starting from the bottom edge.
                            s[j][i].highlightMove(s[j-1][i+1], s[j+1][i+1]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j+1][i-1]);
                        }
                        else {
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);            //Indicating all other possible movements to check.
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i-2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i-2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i+2]);
                        }
                    }
                    
                    firstX = s[j][i].getXlocation();                                    //Writing the location of the firstly selected square.
                    firstY = s[j][i].getYlocation();
                    state = 1;                                                          //Changing the state to 1 to indicate that the piece was chosen.
                }

                /*If there was a click before on one of the squares, check if the
                 source now is one of the squares again and determine whether it 
                 is a valid move*/

                else if (source == s[j][i] && state == 1 && (s[firstY][firstX].canMoveTo(s[j][i]) == true || s[firstY][firstX].canJumpTo(s[j][i]) == true)) {
                    s[firstY][firstX].moveTo(s[j][i]);
                    s[j][i].removeHighlight(s);  
                    //Set the state back to 0 so new "first" click can occur.      
                    state = 0; 
                }
                //If there was a Square clicked but the second location is not a valid move
                //Remove the highlight and set state to 0 again so new "first" click can occur.
                else if (source == s[j][i] && state == 1 && (s[firstY][firstX].canMoveTo(s[j][i]) == false || s[firstY][firstX].canJumpTo(s[j][i]) == false)) {
                    s[j][i].removeHighlight(s);
                    state = 0;
                }
            }
        }
    }

    /**
     * Main method that creates an instance of a Board
     * and adds white and red pieces to it according 
     * to the draughts layout.
     */
    public static void main(String[] args) {
        Board board = new Board();

        //adding white pieces to the board
        for (int i=1; i<8; i=i+2) {
            board.addWhitePieces( 6,i);
        }
        for (int i=0; i<8; i=i+2) {
            for (int j=5; j<8; j=j+2) {
                board.addWhitePieces(j,i);
            }
        }

        //adding red pieces to the board
        for (int i=0; i<7; i=i+2) {
            board.addRedPieces( 1,i);
        }
        for (int i=1; i<8; i=i+2) {
            for (int j=0; j<3; j=j+2) {
                board.addRedPieces(j,i);
            }
        }
    }
}