import java.awt.event.*;
import java.awt.*;
public class GUI_622 extends Frame{
    Label label;
    GUI_622(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        label = new Label("Hello This is Nakul Dhande");
        this.add(label);
        setTitle("Nakul's Frame"); 
        setSize(300,300);
        setVisible(true);
    }
    public static void main(String[] args){
        new GUI_622();
    }
}
//Create a Calculator using GridLayout 