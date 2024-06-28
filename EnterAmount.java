import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EnterAmount {
	String amount;
	int balance;
	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterAmount window = new EnterAmount("Grocery","praneeth@gmail.com");
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
	public EnterAmount(String name,String email) {
		initialize(name,email);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name,String email) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter amount");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(39, 115, 115, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(176, 117, 7, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(202, 116, 209, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount=textField.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "");
					Statement stmt = con.createStatement();
					String sql = "insert into statement values ('"+email+"','"+name+"','"+amount+"')";
					stmt.executeUpdate(sql);
					stmt.close();
					con.close();
				}
				catch(Exception ec) {
					ec.printStackTrace();
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "");
					Statement stmt = con.createStatement();
					String sql = "select balance from customerlogin where email='"+email+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();
					int amount=Integer.parseInt(textField.getText());
					balance=Integer.parseInt(rs.getString(1));
					if((balance-amount)>=0) {
						int bal=(balance-amount);
						sql="UPDATE customerlogin SET balance='"+bal+"' where email='"+email+"';";
						stmt.executeUpdate(sql);
						Table lp=new Table(name,Integer.toString(amount),email);
						frame.setVisible(false);
						//PreparedStatement pstmt = con.prepareStatement("UPDATE customerlogin SET balance=? WHERE email=?");
						//pstmt.setString(1,Integer.toString(bal));
						//pstmt.setString(2, email);
					}
					else {
						JOptionPane.showMessageDialog(frame, "Insufficient balance");
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(285, 204, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Items lp=new Items(email);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(63, 206, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}

}
