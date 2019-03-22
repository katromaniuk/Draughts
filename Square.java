import javax.swing.*;
import javax.xml.transform.Source;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class Square extends JButton {

    private ImageIcon icon;
    private ImageIcon white = new ImageIcon("white.png");
    private ImageIcon emptyWhite = new ImageIcon("empty.png");
    private ImageIcon red = new ImageIcon("red.png");
    private ImageIcon red_king = new ImageIcon("red-king.png");
    private ImageIcon white_king = new ImageIcon("white-king.png");
    private ImageIcon selected = new ImageIcon("selected.png");
    private boolean isEnemyRedLeft = false;
    private boolean isEnemyRedRight = false;
    private boolean isEnemyWhiteLeft = false;
    private boolean isEnemyWhiteRight = false;
    private int Ylocation;
    private int Xlocation;
    private int whatPiece;
    private static final int NONE_WHITE = 0, NONE_BLACK = 1, WHITE = 2, RED = 3, RED_KING = 4, WHITE_KING = 5;


    //get X location on board
    public int getXlocation() {
        return Xlocation;
    }
    //get Y location on board
    public int getYlocation() {
        return Ylocation;
    }
    //return the type of piece that is placed on the square
    public int getPiece() {
        return whatPiece;
    }
    //set the type of a piece that is on the sqaure
    public void setPiece(int piece) {
        whatPiece = piece;
    }

    //check if the type of a piece on a square equals to the piece provided as a parameter
    public boolean pieceEquals(int n) {
        if (this.getPiece() == n) {
            return true;
        }
        return false;
    }
    //method checking where the current piece can move based on the type of a piece that is currently on a square
    //takes another square as a parameter, the one we want clasify as a valid move
    public boolean canMoveTo(Square sqr) {
        
        int x = sqr.getXlocation();
        int y = sqr.getYlocation();

        int a = this.getXlocation();
        int b = this.getYlocation();


        if (this.pieceEquals(WHITE)) {
            
            //if the piece if white the square provided as a parameter can only be x-1 and y-1 from the current square OR...
            if (sqr.pieceEquals(NONE_WHITE) && b - 1 == y && (a - 1 == x || a + 1 == x)) {
                return true;
            }
            else if (sqr.pieceEquals(RED) && b - 1 == y && a - 1 == x) {
                isEnemyRedLeft = true;
                return false;
            }
            else if (sqr.pieceEquals(RED) && b - 1 == y && a + 1 == x) {
                isEnemyRedRight = true;
                return false;
            }
        }
        else if (this.pieceEquals(RED)) {
            //if the piece is red square provided as a parameter can only be x-1 and y+1 from the current square OR...
            if (sqr.pieceEquals(NONE_WHITE) && b + 1 == y && (a - 1 == x || a + 1 == x)) {
                return true;
            }
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

    public boolean canJumpTo(Square sqr) {
        
        int x = sqr.getXlocation();
        int y = sqr.getYlocation();

        int a = this.getXlocation();
        int b = this.getYlocation();


        if (this.pieceEquals(WHITE)) {
            
            //if the piece if white the square provided as a parameter can only be x-1 and y-1 from the current square OR...
            if (sqr.pieceEquals(NONE_WHITE) && b - 2 == y && a - 2 == x && isEnemyRedLeft == true) {
                return true;
            }
            else if (sqr.pieceEquals(NONE_WHITE) && b - 2 == y && a + 2 == x && isEnemyRedRight == true) {
                return true;
            }
        }
        else if (this.pieceEquals(RED)) {
            //if the piece is red square provided as a parameter can only be x-1 and y+1 from the current square OR...
            if (sqr.pieceEquals(NONE_WHITE) && b + 2 == y && a - 2 == x && isEnemyWhiteLeft == true) {
                return true;
            }
            else if (sqr.pieceEquals(NONE_WHITE) && b + 2 == y && a + 2 == x && isEnemyWhiteRight == true) {
                return true;
            }
        }
        return false;
    }
    
    //highlight the square provided as a parameter provided that parameter is a valid move 
    public void highlightMove(Square square, Square square2) {

        boolean bool = this.canMoveTo(square);

        if (this.canJumpTo(square2) == true) {
            bool = false;
            square2.setIcon(selected);
            return;
        }
        else if (bool == true) {
            square.setIcon(selected);
        }
        return;
    }

    //removing the highlight from the square provided as a parameter if no piece was placed on it
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

    //moving the current current piece to the square provided as a parameter (changing their icons)
    //also changing the status of the square based on a piece that was put on it
    public void moveTo(Square sq) {
        
        if (sq.getYlocation() == 0 && this.pieceEquals(WHITE)) {
            sq.setIcon(white_king);
            sq.setPiece(WHITE_KING);
        }
        else if (sq.getYlocation() == 7 && this.pieceEquals(RED)) {
            sq.setIcon(red_king);
            sq.setPiece(RED_KING);
        }
        else if (this.pieceEquals(WHITE)) {
            sq.setIcon(white);
            sq.setPiece(WHITE);
        }
        else if (this.pieceEquals(RED)) {
            sq.setIcon(red);
            sq.setPiece(RED);
        }
        this.setIcon(emptyWhite);
        this.setPiece(NONE_WHITE);

        isEnemyRedLeft = false;
        isEnemyWhiteLeft = false;
        isEnemyRedRight = false;
        isEnemyWhiteRight = false;
    }

    //clickable Square constructor, used to fill in the board
    /*Takes up integer to indicate the location on the x axis,
    *another integer for the location on the y axis,
    * panel on which the square is to be placed,
    *string with the name of the file used as a graphical
    *representation of the square
    */
    public Square(int x, int y, int pi) {

        Xlocation = x;
        Ylocation = y;

        if (pi == WHITE) {
            whatPiece = pi;
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
        this.setIcon(icon);
    }
}