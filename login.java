import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.java.swing.plaf.windows.resources.windows;


public class login extends JFrame{
      private JTextField user;
      private JPasswordField password;
      private JLabel user_label,password_label;
       private String uuser;
       private String ppassword;
       private JButton submit;
       
       private Connection conn = null;		
   	private Statement sm = null;
   	private ResultSet rs = null;
   	
	public login(String title) {
		super.setTitle(title);
		this.setBackground(Color.blue);
		submit=new JButton("登录");
		user=new JTextField(10);
		password=new JPasswordField(10);
		user_label=new JLabel("用户名");
		password_label=new JLabel("密码");
		password.setEchoChar('*');
		this.setLayout(null);
		user_label.setBounds(5,10,70,20);
		user.setBounds(50, 10, 150, 20);
		password_label.setBounds(5, 30, 70, 20);
		password.setBounds(50, 30, 150, 20);
		submit.setBounds(130, 50, 70, 20);
		add(user_label);
		add(user);
		add(password_label);
		add(password);
		add(submit);
		this.setBounds(300, 300, 250, 150);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void login(final mainframe mf) {
	 submit.addMouseListener(new MouseAdapter(){
		 
		public void mouseClicked(MouseEvent e){
			uuser=user.getText().trim();
			ppassword=String.valueOf(password.getPassword());
			if(check()){
				System.out.println("恭喜，成功登录");
				mf.setVisible(true);
			}
			else{
				System.out.println("密码或用户名错误");
			}
		 } 
	 });
		 
	this.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	});
	}

	protected boolean check() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
			sm = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.out.println("缺少必须的文件，请联系管理员！");
			e.printStackTrace();
		}
		 catch (SQLException e) {
			
			e.printStackTrace();
		}
		String sql = "select user, password from user";
		try {
			rs = sm.executeQuery(sql);
			while(rs.next()) {
				if(uuser.equals(rs.getString(1)) && ppassword.equals(rs.getString(2))) {
					System.out.println("login successful!");
					this.setVisible(false);
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("数据库出错，请稍后再试！");
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
					conn = null;
				}
				if(sm != null) {
					sm.close();
					sm = null;
				}
				if(rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	

	


}
