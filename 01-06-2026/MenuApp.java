import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuApp extends JFrame implements ActionListener {
    JButton numberBtn, alphaBtn;
    JPanel cards;
    CardLayout cl;
    JPanel numberPanel;
    JPanel alphaPanel;
    public MenuApp() {

        setTitle("Nakul's Menu And Calculator Application");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top buttons
        JPanel top = new JPanel();

        numberBtn = new JButton("Number");
        alphaBtn = new JButton("Alphabet");

        numberBtn.addActionListener(this);
        alphaBtn.addActionListener(this);

        top.add(numberBtn);
        top.add(alphaBtn);

        add(top, BorderLayout.NORTH);

        // Card Layout
        cl = new CardLayout();
        cards = new JPanel(cl);

        createNumberPanel();
        createAlphabetPanel();

        cards.add(numberPanel, "NUM");
        cards.add(alphaPanel, "ALPHA");

        add(cards, BorderLayout.CENTER);

        setVisible(true);
    }

    void createNumberPanel() {

        numberPanel = new JPanel();

        for(int i=0; i<=9; i++) {
            JButton b = new JButton("" + i);
            numberPanel.add(b);
        }
    }

    void createAlphabetPanel() {

        alphaPanel = new JPanel();

        String letters[] = {"A","B","C","D"};

        for(String s : letters) {

            JButton b = new JButton(s);

            b.addActionListener(e -> {

                if(s.equals("C")) {
                    new CalculatorFrame();
                }

                else {
                    JOptionPane.showMessageDialog(
                            null,
                            s + " clicked");
                }
            });

            alphaPanel.add(b);
        }
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == numberBtn) {
            cl.show(cards, "NUM");
        }

        if(e.getSource() == alphaBtn) {
            cl.show(cards, "ALPHA");
        }
    }

    public static void main(String args[]) {
        new MenuApp();
    }
}


// Calculator Window
class CalculatorFrame extends JFrame
        implements ActionListener {

    JTextField tf;

    JButton add, sub, mul, div;

    CalculatorFrame() {

        setTitle("Calculator");

        setSize(300,200);

        setLayout(new FlowLayout());

        tf = new JTextField(20);

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        add(tf);

        add(add);
        add(sub);
        add(mul);
        add(div);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(
                this,
                "Calculator button clicked");
    }
}