package bookManager;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

//import net.proteanit.sql.DbUtils;

public class app {

	private static final DefaultTableModel DefaultTableModel = null;
	private JFrame frame;
	private JTextField txtBookName;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTable table;
	private JTextField txtBookID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		initialize();
		Connect();
		table_load();
	}
	
	
	//import java.sql.*;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//MySQL Connection
	public void Connect() {
		
		String url = "jdbc:mysql://localhost/javabookmanager";
		String user = "root";
		String pass = "";
		
		try {
			//OLD: com.mysql.jdbc.Driver -> NEW: com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/javabookmanager", "root", "");
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	
	//Load Table
	public void table_load() {
		try {
//			pst = con.prepareStatement("SELECT * FROM books");
//			rs = pst.executeQuery();
//			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			String query = "SELECT * FROM books";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			String id, name, edition, price;
			
			while(rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				edition = rs.getString(3);
				price = rs.getString(4);
				String [] row = {id, name, edition, price};
				
				model.addRow(row);
			}
			
//			ResultSetMetaData rsmd = rs.getMetaData();
//			DefaultTableModel model = (DefaultTableModel) table.getModel();
//			
//			int cols = rsmd.getColumnCount();
//			
//			String[] colname = new String[cols];
//			
//			for(int i=0; i < cols; i++) {
//				colname[i] = rsmd.getColumnName(i++);
//			}
//			
//			model.setColumnIdentifiers(colname);
//			
//			String id, name, edition, price;
//			
//			while(rs.next()) {
//				id = rs.getString(1);
//				name = rs.getString(2);
//				edition = rs.getString(3);
//				price = rs.getString(4);
//				String [] row = {id, name, edition, price};
//				
//				model.addRow(row);
//			}
			
			
			
		}
		
		catch(Exception e) {
			
		}
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 782, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(42, 30, 234, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 97, 380, 146);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 26, 102, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(26, 59, 102, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(26, 92, 102, 23);
		panel.add(lblNewLabel_1_2);
		
		txtBookName = new JTextField();
		txtBookName.setBounds(138, 30, 209, 19);
		panel.add(txtBookName);
		txtBookName.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(138, 63, 209, 19);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(138, 96, 209, 19);
		panel.add(txtPrice);
		
		
		//Insert
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookName, edition, price;
				
				bookName = txtBookName.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				
				try {		
					pst = con.prepareStatement("INSERT INTO books(name,edition,price) VALUES (?,?,?)");
					pst.setString(1,bookName);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Book Successfully Added.");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		btnSave.setBounds(42, 253, 113, 51);
		frame.getContentPane().add(btnSave);
		
		
		
		//EXIT
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(177, 253, 113, 51);
		frame.getContentPane().add(btnExit);
		
		
		
		//CLEAR
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookName.setText("");
				txtEdition.setText("");
				txtPrice.setText("");
				txtBookID.setText("");
			}
		});
		btnClear.setBounds(309, 253, 113, 51);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 101, 295, 206);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Book Name", "Edition", "Price"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(42, 314, 380, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(23, 26, 102, 23);
		panel_1.add(lblNewLabel_1_1_1);
		
		
		//Search
		txtBookID = new JTextField();
		txtBookID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String b_id = txtBookID.getText();
				
				String query = "SELECT name,edition,price FROM books WHERE id = ?";
				
				try {
					pst = con.prepareStatement(query);
					pst.setString(1, b_id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtBookName.setText(name);
						txtEdition.setText(edition);
						txtPrice.setText(price);	
					}
					
					else {
						txtBookName.setText("");
						txtEdition.setText("");
						txtPrice.setText("");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtBookID.setColumns(10);
		txtBookID.setBounds(135, 30, 209, 19);
		panel_1.add(txtBookID);
		
		
		
		
		//Update
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookName, edition, price, b_id;
				
				b_id = txtBookID.getText();
				bookName = txtBookName.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				
				try {		
					pst = con.prepareStatement("UPDATE books SET name=?, edition=?, price=? WHERE id=?");
					pst.setString(1,bookName);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.setString(4,b_id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Updated.");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(458, 324, 113, 51);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String b_id = txtBookID.getText();
				
				try {		
					pst = con.prepareStatement("DELETE FROM books WHERE id=?");
					pst.setString(1,b_id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Deleted");
					table_load();
					txtBookName.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					
					txtBookName.requestFocus();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(581, 324, 113, 51);
		frame.getContentPane().add(btnDelete);
	}
}
