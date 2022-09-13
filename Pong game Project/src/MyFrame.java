import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyPanel panel;
    MyFrame(){
        panel=new MyPanel();
        add(panel);
        setTitle("Pong Game");
        setResizable(false);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
