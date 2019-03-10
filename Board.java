import javax.swing.*;
import java.awt.*;

public class Board {

    private JFrame checkers = new JFrame("Draughts");
    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(8,8,0,0);
    private Square[] s = new Square[64];
    //int returnValueX = 0;
    //int returnValueY = 0;
    private int j = 0;
    private int k = 0;


    public Board() {

        //Creating 64 instances of a Square, white or black depending on location
        //Storing details about each Square's location
        for (int i=0; i<64; i++) {
            if (i >= 0 && i <= 7 || i>= 16 && i<= 23 || i>=32 && i <=39 || i >= 48 && i <= 55) {
                if (i%2 == 0) {
                    s[i] = new Square(k, j, panel, "empty2.png");
                    k++;
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
                else {
                    s[i] = new Square(k, j, panel, "empty.png");
                    k++;
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
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
                else {
                    s[i] = new Square(k, j, panel, "empty2.png");
                    k++;
                    if (k > 7) {
                        k = 0;
                        j++;
                    }
                }
            }
            panel.setLayout(layout);
        }

        //Testing if correct location of the Square is shown
        /*int g = 12;

        *returnValueX = s[g].getXlocation();
        *returnValueY = s[g].getYlocation();
        *JOptionPane.showMessageDialog(null, "Chosen element: " + g + " --> X location: " + returnValueX + ", Y location: " + returnValueY);
        */
        //End of test

        //Adding components to our board
        checkers.setContentPane(panel);
        checkers.setSize(800, 800);
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setResizable(false);
        checkers.setVisible(true);

    }

    public static void main(String[] args) {
        Board board = new Board();
    }
}