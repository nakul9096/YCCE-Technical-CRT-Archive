import java.awt.*;                 // GUI components
import java.awt.event.*;           // Event handling
import javax.swing.*;              // Swing library
import javax.swing.table.*;        // JTable model
import java.sql.*;                 // JDBC classes

public class Test1 extends JFrame implements ActionListener {

    // Input fields
    JTextField tfId, tfName, tfDept, tfSalary, tfDate;

    // Buttons
    JButton btnInsert, btnFetch, btnUpdate;
    JButton btnDelete, btnOrder, btnSearch;

    JTable table;
    DefaultTableModel model;

    JComboBox<String> cbSearch;
    JTextField tfSearch1, tfSearch2;

    Connection con;
    Statement stmt;
    ResultSet rs;

    Test1() {

        setTitle("Employee Management");
        setSize(900,600);
        setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== FORM PANEL =====
        JPanel form = new JPanel(
        new GridLayout(6,2,5,5));

        form.add(new JLabel("Employee ID"));
        tfId = new JTextField();
        form.add(tfId);

        form.add(new JLabel("Employee Name"));
        tfName = new JTextField();
        form.add(tfName);

        form.add(new JLabel("Department"));
        tfDept = new JTextField();
        form.add(tfDept);

        form.add(new JLabel("Salary"));
        tfSalary = new JTextField();
        form.add(tfSalary);

        form.add(new JLabel("Join Date"));
        tfDate = new JTextField();
        form.add(tfDate);

        // ===== BUTTON PANEL =====
        btnInsert = new JButton("Insert");
        btnFetch = new JButton("Fetch");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnOrder = new JButton("Order Salary");

        JPanel bp = new JPanel();

        bp.add(btnInsert);
        bp.add(btnFetch);
        bp.add(btnUpdate);
        bp.add(btnDelete);
        bp.add(btnOrder);

        form.add(bp);

        add(form,BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();

        model.setColumnIdentifiers(
        new String[]{
        "ID",
        "Name",
        "Dept",
        "Salary",
        "Join Date"
        });

        table = new JTable(model);

        JScrollPane sp =
        new JScrollPane(table);

        add(sp,BorderLayout.CENTER);

        // ===== SEARCH PANEL =====
        cbSearch = new JComboBox<>(
        new String[]{
        "Salary >",
        "Like Name",
        "Salary Between"
        });

        tfSearch1 = new JTextField(10);
        tfSearch2 = new JTextField(10);

        btnSearch = new JButton("Search");

        JPanel search = new JPanel();

        search.add(cbSearch);
        search.add(tfSearch1);
        search.add(tfSearch2);
        search.add(btnSearch);

        add(search,BorderLayout.SOUTH);

        // Button events
        btnInsert.addActionListener(this);
        btnFetch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnOrder.addActionListener(this);
        btnSearch.addActionListener(this);

        // ===== DATABASE CONNECTION =====
        try {

            Class.forName(
            "com.mysql.cj.jdbc.Driver");

            con =
            DriverManager.getConnection(
            "jdbc:mysql://localhost:9096/nakul_crt",
            "root",
            "root");

            stmt = con.createStatement();

            JOptionPane.showMessageDialog(
            this,
            "Connected");

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(
            this,
            e);

        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            // ===== INSERT =====
            if(e.getSource()==btnInsert){

                String q =
                "insert into employees values(?,?,?,?,?)";

                PreparedStatement ps =
                con.prepareStatement(q);

                ps.setInt(1,
                Integer.parseInt(
                tfId.getText()));

                ps.setString(2,
                tfName.getText());

                ps.setString(3,
                tfDept.getText());

                ps.setInt(4,
                Integer.parseInt(
                tfSalary.getText()));

                ps.setString(5,
                tfDate.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                this,
                "Inserted");
            }

            // ===== FETCH =====
            if(e.getSource()==btnFetch){

                model.setRowCount(0);

                rs =
                stmt.executeQuery(
                "select * from employees");

                while(rs.next()){

                    model.addRow(
                    new Object[]{

                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5)

                    });

                }
            }

            // ===== UPDATE =====
            if(e.getSource()==btnUpdate){

                String q =
                "update employees set emp_name=?, dept=?, salary=?, join_date=? where emp_id=?";

                PreparedStatement ps =
                con.prepareStatement(q);

                ps.setString(1,
                tfName.getText());

                ps.setString(2,
                tfDept.getText());

                ps.setInt(3,
                Integer.parseInt(
                tfSalary.getText()));

                ps.setString(4,
                tfDate.getText());

                ps.setInt(5,
                Integer.parseInt(
                tfId.getText()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                this,
                "Updated");
            }

            // ===== DELETE =====
            if(e.getSource()==btnDelete){

                String q =
                "delete from employees where emp_id=?";

                PreparedStatement ps =
                con.prepareStatement(q);

                ps.setInt(1,
                Integer.parseInt(
                tfId.getText()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                this,
                "Deleted");
            }

            // ===== ORDER BY SALARY =====
            if(e.getSource()==btnOrder){

                model.setRowCount(0);

                rs =
                stmt.executeQuery(
                "select * from employees order by salary desc");

                while(rs.next()){

                    model.addRow(
                    new Object[]{

                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5)

                    });

                }
            }

            // ===== SEARCH =====
            if(e.getSource()==btnSearch){

                model.setRowCount(0);

                PreparedStatement ps=null;

                String type =
                cbSearch.getSelectedItem()
                .toString();

                if(type.equals("Salary >")){

                    ps =
                    con.prepareStatement(
                    "select * from employees where salary > ?");

                    ps.setInt(
                    1,
                    Integer.parseInt(
                    tfSearch1.getText()));
                }

                else if(type.equals("Like Name")){

                    ps =
                    con.prepareStatement(
                    "select * from employees where emp_name like ?");

                    ps.setString(
                    1,
                    "%" +
                    tfSearch1.getText()
                    + "%");
                }

                else{

                    ps =
                    con.prepareStatement(
                    "select * from employees where salary between ? and ?");

                    ps.setInt(
                    1,
                    Integer.parseInt(
                    tfSearch1.getText()));

                    ps.setInt(
                    2,
                    Integer.parseInt(
                    tfSearch2.getText()));
                }

                rs = ps.executeQuery();

                while(rs.next()){

                    model.addRow(
                    new Object[]{

                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5)

                    });

                }
            }

        }
        catch(Exception ex){

            JOptionPane.showMessageDialog(
            this,
            ex);

        }

    }

    public static void main(String args[]) {

        new Test1();

    }
}