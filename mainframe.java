
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public  class mainframe extends superwindow {
	
		
	
	private  JButton in,find,modify1,delete,passchang,quit;
	
	private JLabel time;
	private login lg;
	private inputInfo inInfo;	
	private search searwin;
	private modify mod;
	private deleteinfo delinfo;
	private changepass password;
	private JPanel p1,p2;
	public mainframe(){
		north = new JPanel();	//new出父类定义的三个JPanel
		center = new JPanel();
		south = new JPanel();
		in=new JButton("信息输入");
		find=new JButton(" 查询");
		modify1=new JButton("修改");
		delete=new JButton("删除");
		passchang=new JButton("密码修改");
		quit=new JButton("退出系统");
		time=new JLabel();
		
		lg=new login("登录");
		lg.login(this);
		inInfo = new inputInfo();
		searwin=new search();
		mod=new modify();
		delinfo=new deleteinfo();
		password=new changepass();
		
	     
		
		}
	public  void showwindow(mainframe mf){
		mf.setTitle("学生信息查询系统");
		mf.setBounds(300, 200, 400, 300);
		
		mf.setNorthPanel(north);			//调用自己的三个设置JPanel()方法
		mf.setCenterPanel(center);
		mf.setSouthPanel(south);
		
		mf.setLayout(new GridLayout(3, 1));
		mf.add(north);						//把三个JPanel对象添加到自己的窗口中
		mf.add(center);
		mf.add(south);

		
		String date=new SimpleDateFormat("yyyy年MM月dd日").format(Calendar.getInstance().getTime()); 
		time.setText("今天是: "+ date);

		
		mf.setResizable(false);
		mf.setVisible(false);
		
		mf.validate();
		
		in.addMouseListener(new  MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				inInfo.setvisible(true);
				inInfo.showme(inInfo);
			}
			
		});
		find.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				searwin.setvisible(true);
				searwin.showme(searwin);
			}
		});
		modify1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				mod.setvisible(true);
				mod.showme(mod);
			}
			
		});
		delete.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				delinfo.setvisible(true);
			
				
			}
		});
		passchang.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				password.setvisible(true);
				
				
			}
		});
			
		quit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				close_or_not();
			}
		});
		
	}
	protected void close_or_not() {
		if(JOptionPane.showConfirmDialog(this, "确定退出系统吗", "确认对话框", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			this.setVisible(false);
			System.exit(0);
		}
		
	}
	public static void main(String[] args) {	//main方法
		mainframe mf = new mainframe();
		mf.showwindow(mf);
	}
	@Override
	public void setCenterPanel(JPanel center) {
		 p1=new JPanel();
		 p1.add(in);
		 p1.add(find);
		 p1.add(modify1);
		 p1.add(delete);
		 p1.add(passchang);
		center.setLayout(new GridLayout());
		center.add(p1);
		
		
	}
	@Override
	public void setNorthPanel(JPanel north) {
		JLabel label;
		label =new JLabel("欢迎使用学生信息管理系统",JLabel.CENTER);
		label.setFont(new Font("TimesRoman",Font.BOLD,25));
		label.setForeground(Color.RED);//
		north.add(label);
		
	}
	@Override
	public void setSouthPanel(JPanel south) {
		FlowLayout ft = new FlowLayout();
		south.setLayout(ft);
		ft.setAlignment(FlowLayout.CENTER);
		ft.setVgap(40);
		ft.setHgap(60);
		south.add(time); 
		south.add(quit);
		
	}
	
	

}
