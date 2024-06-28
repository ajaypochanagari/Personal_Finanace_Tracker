import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CurrentBalance {

	String balance;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentBalance window = new CurrentBalance("praneethsriram087gmail.com");
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
	public CurrentBalance(String email) {
		initialize(email);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String email) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "");
			Statement stmt = con.createStatement();
			String qry = "select * from customerlogin";
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()) {
				if(rs.getString(3).equalsIgnoreCase(email)) {
					balance=rs.getString(6);
					break;
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		String text="Current Balance: "+(balance);
		JLabel currentBalance = new JLabel(text);
		currentBalance.setFont(new Font("Times New Roman", Font.BOLD, 20));
		currentBalance.setBounds(45, 125, 282, 14);
		frame.getContentPane().add(currentBalance);
		
		JButton btnNewButton = new JButton("Back to home page");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage lp=new HomePage(email);
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(217, 212, 153, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

}
