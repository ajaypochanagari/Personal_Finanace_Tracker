import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.*;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Statement {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statement window = new Statement("praneeth@gmail.com");
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
	public Statement(String email) {
		initialize(email);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String email) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Items");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(31, 25, 87, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAmount.setBounds(284, 25, 87, 24);
		frame.getContentPane().add(lblAmount);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage lp=new HomePage(email);
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(311, 527, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindetails", "root", "");
			java.sql.Statement stmt = con.createStatement();
			String qry = "select * from statement";
			ResultSet rs = stmt.executeQuery(qry);
			int y=65;
			while(rs.next()) {
				if(rs.getString(1).equalsIgnoreCase(email)) {
					JLabel itemname = new JLabel(rs.getString(2));
					itemname.setHorizontalAlignment(SwingConstants.CENTER);
					itemname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
					itemname.setBounds(34, y, 90, 24);
					frame.getContentPane().add(itemname);
					
					JLabel lblamount = new JLabel(rs.getString(3));
					lblamount.setHorizontalAlignment(SwingConstants.CENTER);
					lblamount.setFont(new Font("Times New Roman", Font.PLAIN, 15));
					lblamount.setBounds(280, y, 87, 24);
					frame.getContentPane().add(lblamount);
					y=y+25;
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		frame.setVisible(true);
		
	}

}
