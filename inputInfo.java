import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.model.core.Adapter;


public class inputInfo extends superwindow{
	private JButton sumbit,connect,quit;
	
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;

	private JPanel p1,p2;
	
	private JTextField in_name;
	private JTextField in_sex;
	private JTextField in_age;
	private JTextField in_phone;
	private JTextField in_num;
	private JTextField in_major;
	
	public inputInfo(){
		north = new JPanel();	
		center = new JPanel();
		south = new JPanel();
		sumbit =new JButton("确定");
		connect=new JButton("数据库连接");
		quit=new JButton("退出");
		in_num=new JTextField(8);
		in_name=new JTextField(8);
		in_sex=new JTextField(8);
		in_age=new JTextField(8);
		in_phone=new JTextField(8);
		in_major=new JTextField(8);
		
		
		setTitle("学生信息管理系统");
		setBounds(200, 200,500, 300);
		
		setLayout(new FlowLayout());
		validate();
		
	}
     public void showme(inputInfo inInfo) {
    	 
    	 inInfo.setNorthPanel(north);			//调用自己的三个设置JPanel()方法
    	 inInfo.setCenterPanel(center);
    	 inInfo.setSouthPanel(south);
 		
    	 inInfo.setLayout(new GridLayout(3, 1));
    	 inInfo.add(north);						//把三个JPanel对象添加到自己的窗口中
    	 inInfo.add(center);
    	 inInfo.add(south);
		inInfo.addWindowListener(new WindowAdapter(){
			public void windowClosing(ActionEvent e){
				
				setvisible(false);
			}
				
		});
		connect.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				testconn();
			}
	});
		sumbit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
		System.out.println("现在提交");
		sentdata();
			}
		});
		quit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getSource() == quit){
				exit();}
			}

			
		});
     }

	protected void exit() {
		this.dispose();
		
	}
	protected void sentdata() {
	String sql="insert  into information values(?,?,?,?,?,?)";
	try{
		ps=conn.prepareStatement(sql);
		
		ps.setString(1, in_num.getText());
		ps.setString(2, in_name.getText());
		ps.setString(3, in_sex.getText());
		ps.setString(4, in_age.getText());
		ps.setString(5, in_phone.getText());
		ps.setString(6, in_major.getText());
		ps.executeUpdate();
		System.out.println("数据输入成功");
		}catch(SQLException e1){
			e1.printStackTrace();
		}finally{
			try{
				if(conn !=null){
					System.out.println("close conn");
					conn.close();
					conn=null;
				}
				if(rs !=null){
					rs.close();
					rs=null;
				}
				if(ps !=null){
					ps.close();
					ps=null;
				}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
			}
		
	}
	protected void testconn() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("错误");
			e.printStackTrace();
		}
		try {	
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}System.out.println("成功");
		
	}
	public void setvisible(boolean isvisible) {
		in_num.setText("");
		in_name.setText("");
		in_sex.setText("");
		in_age.setText("");
		in_phone.setText("");
		in_major.setText("");
		
		this.setVisible(isvisible);
		
		
	}
	@Override
	public void setCenterPanel(JPanel center) {
		p1=new JPanel();
		p1=new JPanel();
		p2=new JPanel();
		p1.add(new JLabel("学号"));
		p1.add(in_num);
		p1.add(new JLabel("姓名"));
		p1.add(in_name);
		p1.add(new JLabel("性别"));
		p1.add(in_sex);
		p2.add(new JLabel("年龄"));
		p2.add(in_age);
		p2.add(new JLabel("联系方式"));
		p2.add(in_phone);
		p2.add(new JLabel("专业"));
		p2.add(in_major);
		center.setLayout(new GridLayout(2,1));
		center.add(p1);
		center.add(p2);
		
	}
	@Override
	public void setNorthPanel(JPanel north) {
		JLabel label = new JLabel("学生信息输入");
		label.setFont(new Font("TimesRoman",Font.BOLD,34));
		label.setForeground(Color.RED);
		north.add(label);
		
	}
	@Override
	public void setSouthPanel(JPanel south) {
		
		south.setLayout(new FlowLayout());
		south.add(sumbit);
		south.add(connect);
		south.add(quit);
	}

	

	

}
