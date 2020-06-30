import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


public class changepass extends JFrame {
	private Connection conn = null;	
	private PreparedStatement ps = null;
	
	private JLabel user;
	private JLabel oldpass;
	private JLabel newpass;
	
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JPasswordField newPassword_chk;
	private JButton submit;
	
	changepass() {
		this.setTitle("修改密码");
		user = new JLabel("原密码");
		oldpass = new JLabel("新密码");
		newpass = new JLabel("确认新密码");
		
		oldPassword = new JPasswordField(15);
		newPassword = new JPasswordField(15);
		newPassword_chk = new JPasswordField(15);
		
		submit = new JButton("确定");
		setLayout(null);
		user.setBounds(5, 10, 70, 20);
		oldPassword.setBounds(80, 10, 150, 20);
		oldpass.setBounds(5, 30, 70, 20);
		newPassword.setBounds(80, 30, 150, 20);
		newpass.setBounds(5, 50, 70, 20);
		newPassword_chk.setBounds(80, 50, 150, 20);
		submit.setBounds(150, 70, 80, 20);
		
		add(user);
		add(oldPassword);
		add(oldpass);
		add(newPassword);
		add(newpass);
		add(newPassword_chk);
		add(submit);
		
		this.setBounds(400, 350, 250, 150);
		this.setVisible(false);
		this.validate();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setvisible(false);
			}
		});
		
		submit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changenow();
			}

		});
	}
	
	
	private void changenow() {
		String sql = null;
		String password_old = String.valueOf(oldPassword.getPassword());
		String password_new = String.valueOf(newPassword_chk.getPassword());
		System.out.println(password_old);
		System.out.println(password_new);

		sql = "update user set password=? where password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
			ps = conn.prepareStatement(sql);
			ps.setString(1, password_new);
			ps.setString(2, password_old);
			ps.executeUpdate();
			
			System.out.println("change password successful!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				if(conn != null) {
					conn.close();
					conn = null;
				}
				if(ps != null) {
					ps.close();
					ps = null;
				}
				setvisible(false);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void setvisible(boolean b) {
		this.setVisible(b);
		
	}

}
