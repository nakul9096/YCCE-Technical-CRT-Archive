import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class Test2 extends JFrame implements ActionListener {

    JTextField tfName, tfAge, tfGender, tfCourse, tfYear;
    JTextField tfEmail, tfPhone, tfAddress, tfGpa;
    JTextField tfSearch1, tfSearch2;

    JButton btnInsert, btnFetch, btnUpdate;
    JButton btnDelete, btnOrderBy, btnSearch;

    JComboBox<String> cbSearchType;

    JTable table;
    DefaultTableModel model;

    Connection con;
    Statement stmt;
    ResultSet rs;

   Test2() {

        setTitle("Student Management System");
        setSize(1000, 650);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(10,2,5,5));

        formPanel.add(new JLabel("Name"));
        tfName = new JTextField();
        formPanel.add(tfName);

        formPanel.add(new JLabel("Age"));
        tfAge = new JTextField();
        formPanel.add(tfAge);

        formPanel.add(new JLabel("Gender"));
        tfGender = new JTextField();
        formPanel.add(tfGender);

        formPanel.add(new JLabel("Course"));
        tfCourse = new JTextField();
        formPanel.add(tfCourse);

        formPanel.add(new JLabel("Year"));
        tfYear = new JTextField();
        formPanel.add(tfYear);

        formPanel.add(new JLabel("Email"));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);

        formPanel.add(new JLabel("Phone"));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);

        formPanel.add(new JLabel("Address"));
        tfAddress = new JTextField();
        formPanel.add(tfAddress);

        formPanel.add(new JLabel("GPA"));
        tfGpa = new JTextField();
        formPanel.add(tfGpa);

        add(formPanel, BorderLayout.NORTH);

        btnInsert = new JButton("Insert");
        btnFetch = new JButton("Fetch");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnOrderBy = new JButton("Order GPA");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnInsert);
        buttonPanel.add(btnFetch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnOrderBy);

        add(buttonPanel, BorderLayout.WEST);

        model = new DefaultTableModel();

        model.setColumnIdentifiers(
        new String[]{
        "Name","Age","Gender","Course",
        "Year","Email","Phone",
        "Address","GPA"
        });

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        add(sp, BorderLayout.CENTER);

        cbSearchType = new JComboBox<>(
        new String[]{
        "Where GPA >",
        "Like Name",
        "Between GPA"
        });

        tfSearch1 = new JTextField(10);
        tfSearch2 = new JTextField(10);

        btnSearch = new JButton("Search");

        JPanel searchPanel = new JPanel();

        searchPanel.add(cbSearchType);
        searchPanel.add(tfSearch1);
        searchPanel.add(tfSearch2);
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.SOUTH);

        btnInsert.addActionListener(this);
        btnFetch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnOrderBy.addActionListener(this);
        btnSearch.addActionListener(this);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
            "jdbc:mysql://localhost:9096/studentdb",
            "root",
            "root"
            );

            stmt = con.createStatement();

            JOptionPane.showMessageDialog(
            this,
            "Connected"
            );

        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
            this,
            e
            );
        }

        setVisible(true);
    }

    void loadData(String query)
    {
        try {

            model.setRowCount(0);

            rs = stmt.executeQuery(query);

            while(rs.next())
            {
                model.addRow(new Object[]{

                rs.getString(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getDouble(9)

                });
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        try {

            if(e.getSource()==btnInsert)
            {
                String q =
                "insert into students values(?,?,?,?,?,?,?,?,?)";

                PreparedStatement ps =
                con.prepareStatement(q);

                ps.setString(1,tfName.getText());
                ps.setInt(2,Integer.parseInt(tfAge.getText()));
                ps.setString(3,tfGender.getText());
                ps.setString(4,tfCourse.getText());
                ps.setString(5,tfYear.getText());
                ps.setString(6,tfEmail.getText());
                ps.setString(7,tfPhone.getText());
                ps.setString(8,tfAddress.getText());
                ps.setDouble(9,
                Double.parseDouble(tfGpa.getText()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                this,
                "Inserted"
                );
            }

            else if(e.getSource()==btnFetch)
            {
                loadData("select * from students");
            }

            else if(e.getSource()==btnDelete)
            {
                stmt.executeUpdate(
                "delete from students where name='"
                +tfName.getText()+"'"
                );

                JOptionPane.showMessageDialog(
                this,
                "Deleted"
                );
            }

            else if(e.getSource()==btnUpdate)
            {
                stmt.executeUpdate(

                "update students set gpa="
                +tfGpa.getText()
                +" where name='"
                +tfName.getText()+"'"

                );

                JOptionPane.showMessageDialog(
                this,
                "Updated"
                );
            }

            else if(e.getSource()==btnOrderBy)
            {
                loadData(
                "select * from students order by gpa desc"
                );
            }

            else if(e.getSource()==btnSearch)
            {
                String type =
                cbSearchType.getSelectedItem().toString();

                if(type.equals("Where GPA >"))
                {
                    loadData(
                    "select * from students where gpa>"
                    +tfSearch1.getText()
                    );
                }

                else if(type.equals("Like Name"))
                {
                    loadData(
                    "select * from students where name like '%"
                    +tfSearch1.getText()
                    +"%'"
                    );
                }

                else
                {
                    loadData(
                    "select * from students where gpa between "
                    +tfSearch1.getText()
                    +" and "
                    +tfSearch2.getText()
                    );
                }
            }

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex);
        }
    }

    public static void main(String[] args)
    {
        new Test2();
    }
}