import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Board implements ActionListener {

    private JFrame checkers = new JFrame("Draughts");
    private JPanel panel = new JPanel();
    private ImageIcon white = new ImageIcon("white.png");
    private ImageIcon red = new ImageIcon("red.png");
    private int firstX, firstY, state = 0;
    private static final int NONE_WHITE = 0, NONE_BLACK = 1, WHITE = 2, RED = 3, RED_KING = 4, WHITE_KING = 5;
    private GridLayout layout = new GridLayout(8,8,0,0);
    private Square[][] s = new Square[8][8];
    //private int returnValueX = 0;             only needed for a location test
    //private int returnValueY = 0;             only needed for a location test



    public Board() {

        //Creating 64 instances of a Square, white or black depending on location
        //Storing details about each Square's location

       for (int r=0; r<8; r++) {
           for (int c=0; c<8; c++) {
               if (r%2 == 0 && c%2 == 0) {
                   s[r][c] = new Square(c, r, NONE_BLACK);
               }
               else if (r%2 == 1 && c%2 == 1) {
                s[r][c] = new Square(c, r, NONE_BLACK);
               }
               else {
                s[r][c] = new Square(c, r, NONE_WHITE);
               }
               panel.add(s[r][c]);
               s[r][c].addActionListener(this);
               panel.setLayout(layout);
           }
       }

        //Testing if the correct location of the Square is shown
        /*int g = 12;
        *returnValueX = s[g].getXlocation();
        *returnValueY = s[g].getYlocation();
        *JOptionPane.showMessageDialog(null, "Chosen element: " + g + " --> X location: " + returnValueX + ", Y location: " + returnValueY);
        */
        //End of test

        //Adding components to the Board
        checkers.setContentPane(panel);
        checkers.setSize(800, 800);
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setResizable(false);
        checkers.setVisible(true);

    }

    //method for adding white pieces to the exact location on the board (x,y)
    public void addWhitePieces(int x, int y) {
        s[x][y].setIcon(white);
        s[x][y].setPiece(WHITE);
    }

    //method for adding white pieces to the exact location on the board (x,y)
    public void addRedPieces(int x, int y) {
        s[x][y].setIcon(red);
        s[x][y].setPiece(RED);
    }


    //moving white pieces when for one tile to another
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //checking if the source is one of the squares
        //movement option made only for white pieces so far
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                //if source is one of the squares and there was no previous click get coordinates of the square and indicate there was a click
                if (source == s[j][i] && state == 0) {
                    //setting additional conditons for white pieces on side edges
                    if (s[j][i].pieceEquals(WHITE) || s[j][i].pieceEquals(RED)) {
                        if (i == 0) {
                            if (s[j][i].pieceEquals(WHITE))
                                if (j==1) {
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
                            if (s[j][i].pieceEquals(WHITE))
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
                        else if (i == 1) {
                            if (s[j][i].pieceEquals(WHITE)) {
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
                        else if (i==6) {
                            if (s[j][i].pieceEquals(WHITE)) {
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
                        else if (j+1>7 && s[j][i].pieceEquals(WHITE)) {
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                        }
                        else if (j+2>7 && s[j][i].pieceEquals(WHITE)) {
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                        }
                        else if (j+2>7 && s[j][i].pieceEquals(RED)) {
                            s[j][i].highlightMove(s[j+1][i+1], s[j-1][i+1]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j-1][i-1]);
                        }
                        else if (j-1<0 && s[j][i].pieceEquals(RED)) {
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                        }
                        else if (j-2<0 && s[j][i].pieceEquals(RED)) {
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                        }
                        else if (j-2<0 && s[j][i].pieceEquals(WHITE)) {
                            s[j][i].highlightMove(s[j-1][i+1], s[j+1][i+1]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j+1][i-1]);
                        }
                        else {
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i+1], s[j-2][i-2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i+2]);
                            s[j][i].highlightMove(s[j-1][i-1], s[j-2][i-2]);
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i+2]);
                            s[j][i].highlightMove(s[j+1][i+1], s[j+2][i-2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i-2]);
                            s[j][i].highlightMove(s[j+1][i-1], s[j+2][i+2]);
                        }
                    }
                    //remembering the location of the firstly selected piece
                    firstX = s[j][i].getXlocation();
                    firstY = s[j][i].getYlocation();
                    state = 1;
                }
                //if there was a click before, check if the source now is one of the squares plus whether it is a valid move...
                //source can only be x-1,y-1 from the first click or x+1,y-1
                else if (source == s[j][i] && state == 1 && (s[firstY][firstX].canMoveTo(s[j][i]) == true || s[firstY][firstX].canJumpTo(s[j][i]) == true)) {
                    s[firstY][firstX].moveTo(s[j][i]);
                    s[j][i].removeHighlight(s);
                    //set the state back to 0 so new "first" click can occur         
                    state = 0;
                }
                else if (source == s[j][i] && state == 1 && (s[firstY][firstX].canMoveTo(s[j][i]) == false || s[firstY][firstX].canJumpTo(s[j][i]) == false)) {
                    s[j][i].removeHighlight(s);
                    state = 0;
                }
            }
        }
    }

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