
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeGUI extends JFrame {

    JTextField t1,t2,t3;

    JButton insert,next,prev,first,last;

    Connection con;
    Statement st;
    ResultSet rs;

    public EmployeeGUI(){

        setLayout(new FlowLayout());

        add(new JLabel("No"));
        t1=new JTextField(10);
        add(t1);

        add(new JLabel("Name"));
        t2=new JTextField(10);
        add(t2);

        add(new JLabel("Salary"));
        t3=new JTextField(10);
        add(t3);

        insert=new JButton("Insert");
        next=new JButton("Next");
        prev=new JButton("Prev");
        first=new JButton("First");
        last=new JButton("Last");

        add(insert);
        add(first);
        add(next);
        add(prev);
        add(last);

        try{

            con=DatabaseConnection.getConnection();

            st=con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            rs=st.executeQuery(
                    "select * from employee"
            );

        }catch(Exception e){
            e.printStackTrace();
        }

        insert.addActionListener(e->insertData());

        next.addActionListener(e->showNext());

        prev.addActionListener(e->showPrev());

        first.addActionListener(e->showFirst());

        last.addActionListener(e->showLast());

        setSize(400,300);

        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void insertData(){

        try{

            PreparedStatement ps=
            con.prepareStatement(
            "insert into employee values(?,?,?)");

            ps.setInt(1,
            Integer.parseInt(t1.getText()));

            ps.setString(2,
            t2.getText());

            ps.setDouble(3,
            Double.parseDouble(t3.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
            this,"Inserted");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void display(){

        try{

            t1.setText(
            rs.getString(1));

            t2.setText(
            rs.getString(2));

            t3.setText(
            rs.getString(3));

        }catch(Exception e){}
    }

    void showNext(){

        try{
            if(rs.next())
                display();

        }catch(Exception e){}
    }

    void showPrev(){

        try{
            if(rs.previous())
                display();

        }catch(Exception e){}
    }

    void showFirst(){

        try{
            rs.first();
            display();

        }catch(Exception e){}
    }

    void showLast(){

        try{
            rs.last();
            display();

        }catch(Exception e){}
    }

    public static void main(String args[]){

        new EmployeeGUI();

    }
}