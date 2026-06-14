//
import java.awt.*;
public class GUI_611 extends Frame{
    public GUI_611(){
                Button button = new Button("Submit");
        button.setBounds(30,100,80,30);
        add(button);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args){
        new GUI_611();
    }
}