import java.awt.EventQueue;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginPage2 {

	String name,email,pwd,phno,source,sal;
	private JFrame frame;
	private JTextField nameField;
	private JTextField mobileField;
	private JTextField mailField;
	private JTextField sourceField;
	private JTextField salField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage2 window = new LoginPage2();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage2() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(49, 64, 56, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ph.NO.");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(49, 89, 56, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-Mail");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(49, 114, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Source of income");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(49, 140, 113, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount to add");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(49, 165, 108, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(49, 190, 79, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel(":");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(178, 64, 56, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(":");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(178, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(":");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setBounds(178, 114, 46, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(":");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_10.setBounds(178, 140, 46, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(":");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_11.setBounds(178, 165, 46, 14);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(":");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_12.setBounds(178, 178, 46, 39);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_14 = new JLabel("Register ");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setBounds(155, 9, 91, 32);
		frame.getContentPane().add(lblNewLabel_14);
		
		nameField = new JTextField();
		nameField.setBounds(204, 62, 208, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		mobileField = new JTextField();
		mobileField.setBounds(204, 87, 208, 20);
		frame.getContentPane().add(mobileField);
		mobileField.setColumns(10);
		
		mailField = new JTextField();
		mailField.setBounds(204, 112, 208, 20);
		frame.getContentPane().add(mailField);
		mailField.setColumns(10);
		
		sourceField = new JTextField();
		sourceField.setBounds(204, 138, 208, 20);
		frame.getContentPane().add(sourceField);
		sourceField.setColumns(10);
		
		salField = new JTextField();
		salField.setBounds(204, 163, 208, 20);
		frame.getContentPane().add(salField);
		salField.setColumns(10);
		
		JButton btnSignUp = new JButton("Next");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "");
					//Statement stmt = (Statement) con.createStatement();
					Statement stmt = con.createStatement();
					name = nameField.getText();
					email = mailField.getText();
					phno = mobileField.getText();
					sal = salField.getText();
					source = sourceField.getText();
					pwd = String.valueOf(passwordField.getPassword());
					String sql = "insert into customerlogin values ('"+name+"','"+phno+"', '"+email+"', '"+source+"', '"+pwd+"','"+sal+"')";
					stmt.executeUpdate(sql);
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(frame, "Registration Completed Successfully");
					LoginPage3 lp = new LoginPage3();
					frame.dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSignUp.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnSignUp.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage lp=new LoginPage();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBounds(39, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 188, 208, 20);
		frame.getContentPane().add(passwordField);
		frame.setVisible(true);
	}
}
