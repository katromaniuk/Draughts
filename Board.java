import javax.swing.*;
import java.awt.*;

public class Board {


    public static void main(String[] args) {

        JFrame checkers = new JFrame("Draughts");
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(8,8,0,0);
        Square[] s = new Square[64];
        int returnValueX = 0;
        int returnValueY = 0;
        int j = 0;
        int k = 0;


        for (int i=0; i<64; i++) {
            if (i >= 0 && i <= 7 || i>= 16 && i<= 23 || i>=32 && i <=39 || i >= 48 && i <= 55) {
                if (i%2 == 0) {
                    s[i] = new Square(k, j, panel, "empty2.png");
                    k++;
                    panel.setLayout(layout);
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
                else {
                    s[i] = new Square(k, j, panel, "empty.png");
                    k++;
                    panel.setLayout(layout);
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
            }
            else {
                if (i%2 == 0) {
                    s[i] = new Square(k, j, panel, "empty.png");
                    k++;
                    panel.setLayout(layout);
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
                else {
                    s[i] = new Square(k, j, panel, "empty2.png");
                    k++;
                    panel.setLayout(layout);
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
            }
        }

        int g = 12;

        returnValueX = s[g].getXlocation();
        returnValueY = s[g].getYlocation();
        JOptionPane.showMessageDialog(null, "Chosen element: " + g + " --> X location: " + returnValueX + ", Y location: " + returnValueY);
        checkers.setContentPane(panel);
        checkers.setSize(800, 800);
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setResizable(false);
        checkers.setVisible(true);

    }
}