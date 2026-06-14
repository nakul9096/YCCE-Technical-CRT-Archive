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
	B3_111()
	{
		setTitle("Nakul's Team Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		//top panel
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
		
		cbSearchType = new JComboBox<>(new String[] {"Where GPA >","Like Name","BETWEEN GPA"});
		
		tfSearch1 = new JTextField(10);
		tfSearch2 = new JTextField(10);
		btnSearch = new JButton("Search");
		
		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("Search Type"));
		
		searchPanel.add(cbSearchType);
		searchPanel.add(tfSearch1);
		searchPanel.add(tfSearch2);
		
		add(searchPanel,BorderLayout.SOUTH);
		formPanel.add(btnPanel);
		add(formPanel,BorderLayout.NORTH);
		
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
        
        //DB CONNECION
        String url = "jdbc:mysql://localhost:9096/";
        String username = "root";
        String pass="root";
        try {
        con = DriverManager.getConnection(url, username, pass);
      	JOptionPane.showMessageDialog(this, "Connected to DB");
        }catch(Exception e) {
        	JOptionPane.showMessageDialog(this, "DB ERROR: "+e.getMessage());
        }
		
	}
	public static void main(String[] args) {
		
	}	
}
