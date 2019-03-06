import javax.swing.*;
import java.awt.*;

public class Board {


    public static void main(String[] args) {

        JFrame checkers = new JFrame("Draughts");
        JPanel panel = new JPanel();
        //GridBagLayout bagLayout = new GridBagLayout();
        GridLayout layout = new GridLayout(8,8,0,0);
        //GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.BOTH;
        Square[] s = new Square[64];


        for (int i=0; i<64; i++) {
            if (i >= 0 && i <= 7 || i>= 16 && i<= 23 || i>=32 && i <=39 || i >= 48 && i <= 55) {
                if (i%2 == 0) {
                    s[i] = new Square(0, 0, panel, "empty2.png");
                    panel.setLayout(layout);
                }
                else {
                    s[i] = new Square(0,0, panel, "empty.png");
                    panel.setLayout(layout);
                }
            }
            else {
                if (i%2 == 0) {
                    s[i] = new Square(0, 0, panel, "empty.png");
                    panel.setLayout(layout);
                }
                else {
                    s[i] = new Square(0,0, panel, "empty2.png");
                    panel.setLayout(layout);
                }
            }
        }

        checkers.setContentPane(panel);
        checkers.setSize(800, 800);
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setResizable(false);
        checkers.setVisible(true);

    }
}