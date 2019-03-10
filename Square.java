import javax.swing.*;
import java.awt.*;

public class Square {

    private ImageIcon i;
    private JButton b = new JButton(i);
    private int Xlocation;
    private int Ylocation;
    private String whatPiece;


    //get location on the X axis
    public int getXlocation() { 
        return Xlocation;
    }

    //get location on the Y axis
    public int getYlocation() {
        return Ylocation;
    }

    //clickable Square constructor, used to fill the board
    /*Takes up integer to indicate the location on the x axis,
    *another integer for the location on the y axis,
    * panel on which the square is to be placed,
    *string with the name of the file used as a graphical
    *representation of the square
    */
    public Square(int x, int y, JPanel p, String s, String pi) {

        Xlocation = x;
        Ylocation = y;

        if (pi.contentEquals("WHITE") == true) {
            whatPiece = pi;
            i = new ImageIcon("white.png");
        }
        else if (pi.contentEquals("RED") == true) {
            whatPiece = pi;
            i = new ImageIcon("red.png");
        }
        else {
            pi = "NONE";
            whatPiece = pi;
            i = new ImageIcon(s);
        }

        b.setIcon(i);
        p.add(b);
    }
}