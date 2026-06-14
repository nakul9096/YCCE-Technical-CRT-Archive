//using swing form creation
import java.awt.*;
import javax.swing.*;

import javax.swing.*;
public class GUI_613 extends JFrame{
    JLabel l1,l2;
    JTextField tf1,tf2;
    JButton b1,b2,b3;
    JFrame jf = new JFrame();
    GUI_613(){
        jf.setTitle("Flow Layout Form");
        jf.setSize(400,400);
        l1 = new JLabel("Enter Name: ");
        l2 = new JLabel("Enter City: ");
        tf1 = new JTextField(15);
        tf2 = new JTextField(15);
        b1 = new JButton("Clear");
        b2 = new JButton("Submit");
        b3 = new JButton("Exit");
        jf.setLayout(new FlowLayout());
        jf.add(l1);
        jf.add(tf1);
        jf.add(l2);
        jf.add(tf2);
        jf.add(b1);
        jf.add(b2);
        jf.add(b3);
        jf.setVisible(true);    
        
    }
    public static void main(String[] args) {
        new GUI_613();
    }
}