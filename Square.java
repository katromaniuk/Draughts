import javax.swing.*;
import java.awt.*;

public class Square {

    private ImageIcon i;
    private JButton b = new JButton(i);
    private int Xlocation;
    private int Ylocation;
    
    public int getXlocation() { 
        Xlocation = b.getX();
        return Xlocation;
    }

    public int getYlocation() {
        Ylocation = b.getY();
        return Ylocation;
    }

    public Square(int x, int y, JPanel p, String s) {

        Xlocation = x;
        b.setAlignmentX(x);
        Ylocation = y;
        b.setAlignmentY(y);
        i = new ImageIcon(s);
        b.setIcon(i);
        p.add(b);
    }
}