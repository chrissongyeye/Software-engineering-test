
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
		north = new JPanel();	//new�����ඨ�������JPanel
		center = new JPanel();
		south = new JPanel();
		in=new JButton("��Ϣ����");
		find=new JButton(" ��ѯ");
		modify1=new JButton("�޸�");
		delete=new JButton("ɾ��");
		passchang=new JButton("�����޸�");
		quit=new JButton("�˳�ϵͳ");
		time=new JLabel();
		
		lg=new login("��¼");
		lg.login(this);
		inInfo = new inputInfo();
		searwin=new search();
		mod=new modify();
		delinfo=new deleteinfo();
		password=new changepass();
		
	     
		
		}
	public  void showwindow(mainframe mf){
		mf.setTitle("ѧ����Ϣ��ѯϵͳ");
		mf.setBounds(300, 200, 400, 300);
		
		mf.setNorthPanel(north);			//�����Լ�����������JPanel()����
		mf.setCenterPanel(center);
		mf.setSouthPanel(south);
		
		mf.setLayout(new GridLayout(3, 1));
		mf.add(north);						//������JPanel������ӵ��Լ��Ĵ�����
		mf.add(center);
		mf.add(south);

		
		String date=new SimpleDateFormat("yyyy��MM��dd��").format(Calendar.getInstance().getTime()); 
		time.setText("������: "+ date);

		
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
		if(JOptionPane.showConfirmDialog(this, "ȷ���˳�ϵͳ��", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			this.setVisible(false);
			System.exit(0);
		}
		
	}
	public static void main(String[] args) {	//main����
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
		label =new JLabel("��ӭʹ��ѧ����Ϣ����ϵͳ",JLabel.CENTER);
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
