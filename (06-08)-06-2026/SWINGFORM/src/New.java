import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class New extends JFrame implements ActionListener {

    JTextField tfName, tfAge, tfGender, tfCourse, tfYear, tfEmail, tfPhone, tfAddress, tfGpa;
    JButton btnInsert, btnFetch, btnUpdate, btnDelete, btnOrderBy;
    JTable table;
    DefaultTableModel model;
    JComboBox<String> cbSearchType;
    JTextField tfSearch1, tfSearch2;
    JButton btnSearch;

    Connection con;
    Statement stmt;
    ResultSet rs;

    New() {

        setTitle("Nakul's Team Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));

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

        btnInsert = new JButton("Insert");
        btnFetch = new JButton("Fetch");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnOrderBy = new JButton("Order By GPA");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnInsert);
        btnPanel.add(btnFetch);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnOrderBy);

        formPanel.add(btnPanel);

        add(formPanel, BorderLayout.NORTH);

        cbSearchType = new JComboBox<>(
                new String[] { "Where GPA >", "Like Name", "BETWEEN GPA" });

        tfSearch1 = new JTextField(10);
        tfSearch2 = new JTextField(10);
        btnSearch = new JButton("Search");

        JPanel searchPanel = new JPanel();

        searchPanel.add(new JLabel("Search Type"));
        searchPanel.add(cbSearchType);
        searchPanel.add(tfSearch1);
        searchPanel.add(tfSearch2);
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.SOUTH);

        model = new DefaultTableModel();

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        btnInsert.addActionListener(this);
        btnFetch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnOrderBy.addActionListener(this);
        btnSearch.addActionListener(this);

        String url = "jdbc:mysql://localhost:9096/nakuldb";
        String username = "root";
        String pass = "root";

        try {

            con = DriverManager.getConnection(url, username, pass);

            JOptionPane.showMessageDialog(this,
                    "Connected to DB");

            setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "DB ERROR : " + e.getMessage());
        }

    }

    public static void main(String[] args) {

        new New();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnInsert) {

            try {

                stmt = con.createStatement();

                String q = "INSERT INTO onkars_team_ois "
                        + "(name,age,gender,course,"
                        + "year,email,phone,address,gpa) "
                        + "VALUES('"
                        + tfName.getText() + "',"
                        + tfAge.getText() + ","
                        + "'" + tfGender.getText() + "',"
                        + "'" + tfCourse.getText() + "',"
                        + tfYear.getText() + ","
                        + "'" + tfEmail.getText() + "',"
                        + "'" + tfPhone.getText() + "',"
                        + "'" + tfAddress.getText() + "',"
                        + tfGpa.getText() + ")";

                stmt.execute(q);

                JOptionPane.showMessageDialog(this,
                        "Data Inserted Successfully");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this,
                        "ERROR : " + e.getMessage());
            }
        }else if (ae.getSource() == btnFetch) {
        	try {
        		stmt = con.createStatement();
        		rs = stmt.executeQuery("SELECT * FROM onkars_team_ois ");
            	ResultSetMetaData rsmd = rs.getMetaData();
            	
            	int cols = rsmd.getColumnCount();
            	model.setRowCount(0);
            	model.setColumnCount(0);
  
            	for(int i=1;i<=cols;i++) {
            		model.addColumn(rsmd.getColumnName(i));
            	}
            	while(rs.next()) {
            		Object[] row = new Object[cols];
            		for(int i=1;i<=cols;i++) {
            			row[i-1]=rs.getObject(i);
            		}
            		model.addRow(row);
            	}
            	
        	}catch(Exception e) {
                JOptionPane.showMessageDialog(this,
                        "ERROR : " + e.getMessage());
        	}
        }else if(ae.getSource()==btnUpdate){
        	try {
        		stmt = con.createStatement();
				String q =

				"update employees set " +

				"emp_name='" + tfName.getText() + "'," +

				"dept='" + tfGender.getText() + "'," +

				"salary=" + tfCourse.getText() + "," +

				"join_date='" + tfYear.getText() + "' " +

				"where emp_id=" +

				tfAge.getText();

				stmt.executeUpdate(q);

				JOptionPane.showMessageDialog(

				this,

				"Updated"

				);
        	}catch(Exception e) {
        	    JOptionPane.showMessageDialog(this,
                        "ERROR : " + e.getMessage());
        	}

			}else if(ae.getSource()==btnDelete){
				try {
	        		stmt = con.createStatement();
					String q =

							"delete from employees where emp_id=" +

							tfAge.getText();

							stmt.executeUpdate(q);

							JOptionPane.showMessageDialog(

							this,

							"Deleted"

							);
				}catch(Exception e) {
				    JOptionPane.showMessageDialog(this,
	                        "ERROR : " + e.getMessage());
				}
			}
			else if(ae.getSource() == btnOrderBy) {
				try {
	        		stmt = con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM "+" onkars_team_ois ORDER BY GPA DESC ");
					ResultSetMetaData rsmd = rs.getMetaData();
					int cols = rsmd.getColumnCount();
					model.setRowCount(0);
					model.setColumnCount(0);
					for(int i=1;i<=cols;i++) {
						model.addColumn(rsmd.getColumnName(i));
					}while(rs.next()) {
						Object[] row = new Object[cols];
						for(int i=1;i<=cols;i++) {
							row[i-1]=rs.getObject(i);
						}
						model.addRow(row);
					}
				}catch(Exception e) {
				    JOptionPane.showMessageDialog(this,
	                        "ERROR : " + e.getMessage());
					
				}
			}else if(ae.getSource()==btnSearch) {

				try {

					stmt = con.createStatement();

					String type =
					(String)cbSearchType.getSelectedItem();

					String q = "";

					if(type.equals("Where GPA >")) {

						q =
						"SELECT * FROM onkars_team_ois " +
						"WHERE gpa > " +
						tfSearch1.getText();

					}

					else if(type.equals("Like Name")) {

						q =
						"SELECT * FROM onkars_team_ois " +
						"WHERE name LIKE '%" +
						tfSearch1.getText() +
						"%'";

					}

					else if(type.equals("BETWEEN GPA")) {

						q =
						"SELECT * FROM onkars_team_ois " +
						"WHERE gpa BETWEEN " +
						tfSearch1.getText() +
						" AND " +
						tfSearch2.getText();

					}

					rs =
					stmt.executeQuery(q);

					ResultSetMetaData rsmd =
					rs.getMetaData();

					int cols =
					rsmd.getColumnCount();

					model.setRowCount(0);

					model.setColumnCount(0);

					for(int i=1;i<=cols;i++) {

						model.addColumn(
						rsmd.getColumnName(i));

					}

					while(rs.next()) {

						Object[] row =
						new Object[cols];

						for(int i=1;i<=cols;i++) {

							row[i-1] =
							rs.getObject(i);

						}

						model.addRow(row);

					}
				}
				catch(Exception e) {

					JOptionPane.showMessageDialog(

					this,

					"ERROR : " +
					e.getMessage()

					);

				}

			}
    }
}