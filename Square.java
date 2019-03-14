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
    private ImageIcon selected = new ImageIcon("selected.png");
    private int Ylocation;
    private int Xlocation;
    private int whatPiece;
    private static final int NONE_WHITE = 0, NONE_BLACK = 1, WHITE = 2, RED = 3; 


    //get X location on board
    public int getXlocation() {
        return Xlocation;
    }

    public int getYlocation() {
        return Ylocation;
    }

    public int getPiece() {
        return whatPiece;
    }

    public void setPiece(int piece) {
        whatPiece = piece;
    }


    public boolean pieceEquals(int n) {
        if (this.getPiece() == n) {
            return true;
        }
        return false;
    }

    public boolean canMoveTo(Square sqr) {
        
        int x = sqr.getXlocation();
        int y = sqr.getYlocation();

        if (this.getPiece() == WHITE) {
        
            if (sqr.getPiece() == NONE_WHITE && this.getYlocation() - 1 == y && this.getXlocation() - 1 == x) {
                return true;
            }
            else if (sqr.getPiece() == NONE_WHITE && this.getYlocation() - 1 == y && this.getXlocation() + 1 == x) {
                return true;
            }
            return false;
        }
        else if (this.getPiece() == RED) {

            if (sqr.getPiece() == NONE_WHITE && this.getYlocation() + 1 == y && this.getXlocation() - 1 == x) {
                return true;
            }
            else if (sqr.getPiece() == NONE_WHITE && this.getYlocation() + 1 == y && this.getXlocation() + 1 == x) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    public void highlightMove(Square square) {

            if (this.canMoveTo(square) == true) {
                square.setIcon(selected);
            }
            return;
    }

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

    public void moveTo(Square sq) {

        if (this.pieceEquals(WHITE)) {
            sq.setIcon(white);
            sq.setPiece(WHITE);
        }
        else if (this.pieceEquals(RED)) {
            sq.setIcon(red);
            sq.setPiece(RED);
        }
        this.setIcon(emptyWhite);
        this.setPiece(NONE_WHITE);
    }

    //clickable Square constructor, used to fill the board
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