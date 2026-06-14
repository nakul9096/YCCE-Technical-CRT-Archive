import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class B3_111 extends JFrame implements ActionListener{

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

	B3_111(){

		setTitle("Nakul's Team Management System");

		setSize(900,600);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		JPanel formPanel =
		new JPanel(new GridLayout(10,2,5,5));

		formPanel.add(new JLabel("Name"));
		tfName = new JTextField();
		formPanel.add(tfName);

		formPanel.add(new JLabel("Age(ID)"));
		tfAge = new JTextField();
		formPanel.add(tfAge);

		formPanel.add(new JLabel("Gender(Dept)"));
		tfGender = new JTextField();
		formPanel.add(tfGender);

		formPanel.add(new JLabel("Course(Salary)"));
		tfCourse = new JTextField();
		formPanel.add(tfCourse);

		formPanel.add(new JLabel("Year(Date)"));
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
		btnOrderBy = new JButton("Order Salary");

		JPanel btnPanel = new JPanel();

		btnPanel.add(btnInsert);
		btnPanel.add(btnFetch);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnDelete);
		btnPanel.add(btnOrderBy);

		cbSearchType =
		new JComboBox<>(new String[]{

		"Salary >",
		"Like Name",
		"Salary Between"

		});

		tfSearch1 = new JTextField(10);
		tfSearch2 = new JTextField(10);

		btnSearch = new JButton("Search");

		JPanel searchPanel = new JPanel();

		searchPanel.add(new JLabel("Search"));

		searchPanel.add(cbSearchType);

		searchPanel.add(tfSearch1);

		searchPanel.add(tfSearch2);

		searchPanel.add(btnSearch);

		add(searchPanel,BorderLayout.SOUTH);

		formPanel.add(btnPanel);

		add(formPanel,BorderLayout.NORTH);

		model = new DefaultTableModel();

		model.setColumnIdentifiers(

		new String[]{

		"ID",
		"Name",
		"Dept",
		"Salary",
		"Date"

		});

		table = new JTable(model);

		JScrollPane scrollPane =
		new JScrollPane(table);

		add(scrollPane,
		BorderLayout.CENTER);

		btnInsert.addActionListener(this);

		btnFetch.addActionListener(this);

		btnUpdate.addActionListener(this);

		btnDelete.addActionListener(this);

		btnOrderBy.addActionListener(this);

		btnSearch.addActionListener(this);

		try{

			con =
			DriverManager.getConnection(

			"jdbc:mysql://localhost:9096/nakul_crt",

			"root",

			"root"

			);

			stmt =
			con.createStatement();

			JOptionPane.showMessageDialog(

			this,

			"Connected"

			);

		}

		catch(Exception ex){

			JOptionPane.showMessageDialog(

			this,

			ex

			);

		}

		setVisible(true);

	}

	public static void main(String args[]){

		new B3_111();

	}

	public void actionPerformed(ActionEvent e){

		try{

			if(e.getSource()==btnInsert){

				String q =

				"INSERT INTO employees " +

				"VALUES(" +

				tfAge.getText() + ",'" +

				tfName.getText() + "','" +

				tfGender.getText() + "'," +

				tfCourse.getText() + ",'" +

				tfYear.getText() + "')";

				stmt.executeUpdate(q);

				JOptionPane.showMessageDialog(

				this,

				"Inserted"

				);

			}

			if(e.getSource()==btnFetch){

				model.setRowCount(0);

				rs =
				stmt.executeQuery(

				"select * from employees"

				);

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

			if(e.getSource()==btnDelete){

				String q =

				"delete from employees where emp_id=" +

				tfAge.getText();

				stmt.executeUpdate(q);

				JOptionPane.showMessageDialog(

				this,

				"Deleted"

				);

			}

			if(e.getSource()==btnUpdate){

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

			}

			if(e.getSource()==btnOrderBy){

				model.setRowCount(0);

				rs =
				stmt.executeQuery(

				"select * from employees order by salary desc"

				);

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

			ex

			);

		}

	}

}