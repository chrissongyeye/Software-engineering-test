import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;


import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.sun.xml.internal.bind.v2.model.core.Adapter;


public class search extends superwindow implements ItemListener{
	private JLabel label1;
	private JTextField text;
	private JComboBox choice;
 
    private JButton sumbit,connect;
    private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private JPanel p1,p2;
 int n;
	
	JTable table;
	

	public search(){
		north = new JPanel();	
		center = new JPanel();
		south = new JPanel();
		this.setTitle("信息查询窗口");
		text=new JTextField(8);
		label1=new JLabel("请输入学号");
		
		choice=new JComboBox();

		choice.addItem("学号");
		choice.addItem("姓名");
		choice.addItemListener(this);
		sumbit=new JButton("确定");
		connect=new JButton("连接数据库");
	
		table = new JTable(7, 2);
		
		table.setValueAt("学号：", 0, 0);
		table.setValueAt("姓名：", 1, 0);
		table.setValueAt("性别：", 2, 0);
		table.setValueAt("年龄：", 3, 0);
		table.setValueAt("联系方式：", 4, 0);
		table.setValueAt("专业：", 5, 0);
		 setNorthPanel(north);			//调用自己的三个设置JPanel()方法
			setCenterPanel(center);
			setSouthPanel(south);
			this.setTitle("信息查询窗口");
			this.setResizable(false);
			setlocate(300, 200, 400, 350);
			setLayout(new GridLayout(3, 1));
			add(north);		//将三个JPanel对象添加到窗口中
			add(center);
			add(south);
		
		this.setVisible(false);
		this.validate();
		this.pack();
	}
	public void testconn() {

		try {
			Class.forName("com.mysql.jdbc.Driver");	//获取并装置JDBC驱动程序	
		} catch(ClassNotFoundException e) {
			System.out.println("register error");
			e.printStackTrace();
		}
		
		try {	//建立数据库连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}System.out.println("连接成功");
	}
	

		
		public void itemStateChanged(ItemEvent e)
		{
			if(choice.getSelectedIndex()==0)
			{
				n=0;	//name
				label1.setText("请输入学号");
			}
			else
			{
				n=1;	//num
				label1.setText("请输入姓名");
			}
				
	}
	
	public void setvisible(boolean b) {
		this.setVisible(b);
		
	}

	public void showme(search searwin) {
	searwin.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e) {
			dispose();
			setvisible(false);		
		}
	});
	
	connect.addMouseListener(new MouseAdapter() {	//注册连接数据库按钮的鼠标事件监听器
		public void mouseClicked(MouseEvent e) {
			testconn();					//调用方法,测试数据库连接
			
		}
	});	
	sumbit.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
	        if(e.getSource() == sumbit){
	        	resultinfo();}
	        	
	        
		}
	});

	
		
	}
	private  void resultinfo() {
		
		  	if(n==0){
		  		if(text.getText().equals("")){
		  			 JOptionPane.showMessageDialog(null, "请输入学号", "警告",
                             JOptionPane.WARNING_MESSAGE);
		  		}else{try{
    			  	String sql = "select * from information where sNum=\'"+text.getText().toString()+"\'";
    			  PreparedStatement ps=conn.prepareStatement(sql);
    			  	rs=ps.executeQuery(sql);
  				  if(rs.next()){
  					 this.setSize(500, 350);
	  					
	  					table.setValueAt(rs.getString("sNum"), 0, 1);
	  					table.setValueAt(rs.getString("sName"), 1, 1);
	  					table.setValueAt(rs.getString("sSex"), 2, 1);
	  					table.setValueAt(rs.getString("sBirth"), 3, 1);
	  					table.setValueAt(rs.getString("sPhone"), 4, 1);
	  					table.setValueAt(rs.getString("sMajor"),5, 1);
	  				    this.add(table);
  				  }else
				  {
						JOptionPane.showMessageDialog(null, "记录不存在", "警告",
							JOptionPane.WARNING_MESSAGE);
					}
		  		}catch(Exception e){
  				   
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
  				
  			}}}}
		  		
		else{
	  if(n==1){
		  if(text.getText().equals(""))
			{
				        JOptionPane.showMessageDialog(null, "请输入姓名", "警告",
                            JOptionPane.WARNING_MESSAGE);
			}
		  else{
			  	try{String sql = "select * from information where sName=\'"+ text.getText().toString() +"\'";
			  		PreparedStatement ps=conn.prepareStatement(sql);
			  		rs=ps.executeQuery(sql);
			  if(rs.next()){
  				
				  this.setSize(500,350);
					
					
					table.setValueAt(rs.getString("sNum"), 0, 1);
					table.setValueAt(rs.getString("sName"), 1, 1);
					table.setValueAt(rs.getString("sSex"), 2, 1);
					table.setValueAt(rs.getString("sBirth"), 3, 1);
					table.setValueAt(rs.getString("sPhone"), 4, 1);
					table.setValueAt(rs.getString("sMajor"), 5, 1);
				this.add(table);
				  }else
				{
					JOptionPane.showMessageDialog(null, "记录不存在", "警告",
						JOptionPane.WARNING_MESSAGE);
				}
			
	 
			
			  }catch(Exception e){
				  
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
	  }
	  }
}
	@Override
	public void setCenterPanel(JPanel center) {
		p1=new JPanel();
		p2=new JPanel();
		p1.add(label1);
		p1.add(text);
		p1.add(choice);
		p1.add(sumbit);
		p1.add(connect);
	    p2.add(table);
	    center.setLayout(new GridLayout());
	    center.add(p1);
	    center.add(p2);
		
	}
	@Override
	public void setNorthPanel(JPanel north) {
		JLabel label = new JLabel("学生信息查询");
		label.setFont(new Font("TimesRoman",Font.BOLD,34));
		label.setForeground(Color.RED);
		north.add(label);
		
		
	}
	@Override
	public void setSouthPanel(JPanel south) {
		south.setLayout(new FlowLayout());
		south.add(sumbit);
		south.add(connect);
		
	}	}		

