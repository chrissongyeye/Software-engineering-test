import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class modify extends superwindow {
	
	private JButton submit,search,clear,quit;
	private JLabel label1,label2,label3,label4,label5,label6;
	
	private JTextField name, sex, age, phone, major;
	 private Connection conn=null;
		private PreparedStatement ps=null;
		private ResultSet rs=null;
		private JPanel p1,p2;
		String sNum_str = "";
		private JTextField num;
		String[] s = new String[5];
		public modify(){
			
			north = new JPanel();	//new�����������JPanel����
			center = new JPanel();
			south = new JPanel();
			
			submit = new JButton("�޸�");
			search=new JButton("��ѯѧ��");
			clear=new JButton("���");
			quit=new JButton("�˳�");
			
			num = new JTextField(8);
			name = new JTextField(8);
			sex = new JTextField(8);
			age = new JTextField(8);
			phone = new JTextField(8);
			major = new JTextField(8);
			
	   
	        setNorthPanel(north);			//�����Լ�����������JPanel()����
			setCenterPanel(center);
			setSouthPanel(south);
			
			this.setTitle("��Ϣ�޸Ĵ���");
			this.setResizable(false);
			setlocate(300, 200, 500, 350);
			setLayout(new GridLayout(3, 1));
			add(north);		//������JPanel������ӵ�������
			add(center);
			add(south);
			
			validate();
			
			
			
		}
	

	public void setvisible(boolean b) {
		this.setVisible(b);
		
	}

	public void showme(final modify mod) {
		mod.addWindowListener(new WindowAdapter() {		
			public void windowClosing(WindowEvent e) {
				setvisible(false);			
			}
		});
		
		
		submit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getSource() == submit){
				result();}
			}
		});
		search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getSource() == search){
				result1();}
			}
		});
		clear.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getSource() == clear){
				result2();}
			}
		});
		quit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				 if(e.getSource() == quit){
				result3();}
			}
		});
			
	}

public void result() {
		
		
		StuBean modifyStu = new StuBean();
		modifyStu.stuModify(num.getText(), name.getText(), sex.getText(), age.getText(), phone.getText(), major.getText());
		modifyStu.stuSearch(num.getText());
		s = modifyStu.stuSearch(sNum_str);

		name.setText(s[0]);
		sex.setText(s[1]);
		age.setText(s[2]);
		phone.setText(s[3]);
		major.setText(s[4]);
		
	
	
			
			
	}
	protected void result2() {
		setNull();
		num.setText("���ѯѧ��");
		
	}


	protected void result1() {
	
		numsearch siss = new numsearch(this);
		siss.pack();
		siss.setVisible(true);
		try{
			sNum_str = siss.getSnum();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "û�в��ҵ���ѧ�ţ�"); 
		}

		StuBean searchStu = new StuBean();
		s = searchStu.stuSearch(sNum_str);
		if (s == null) {
			JOptionPane.showMessageDialog(null, "��¼�����ڣ�");
			num.setText("���ѯѧ��");
			name.setText("");
			sex.setText("");
			age.setText("");
			phone.setText("");
			major.setText("");

			name.setEditable(false);
			sex.setEditable(false);
			age.setEditable(false);
			phone.setEditable(false);
			major.setEditable(false);
			submit.setEnabled(false);
			return;
		} else {
			num.setText(sNum_str);
			name.setText(s[0]);
			sex.setText(s[1]);
			age.setText(s[2]);
			phone.setText(s[3]);
			major.setText(s[4]);
			

			name.setEditable(true);
			sex.setEditable(true);
			age.setEditable(true);
			phone.setEditable(true);
			major.setEditable(true);
			submit.setEnabled(true); 
		}
	}
		
	
	protected void result3() {
		this.dispose();
		
	}

	private void setNull() {
		num.setText(null);
		name.setText(null);
		sex.setText(null);
		age.setText(null);
		phone.setText(null);
		major.setText(null);
		
		
	}



	@Override
	public void setCenterPanel(JPanel center) {
		p1=new JPanel();
		p2=new JPanel();
		p1.add(new JLabel("ѧ��"));
		p1.add(num);
		p1.add(new JLabel("����"));
		p1.add(name);
		p1.add(new JLabel("�Ա�"));
		p1.add(sex);
		p2.add(new JLabel("����"));
		p2.add(age);
		p2.add(new JLabel("��ϵ��ʽ"));
		p2.add(phone);
		p2.add(new JLabel("רҵ"));
		p2.add(major);
		center.setLayout(new GridLayout(2,1));
		center.add(p1);
		center.add(p2);
		
		
	}


	@Override
	public void setNorthPanel(JPanel north) {
		JLabel label = new JLabel("ѧ����Ϣ�޸�");
		label.setFont(new Font("TimesRoman",Font.BOLD,34));
		label.setForeground(Color.RED);
		north.add(label);
		
	}


	@Override
	public void setSouthPanel(JPanel south) {
		
		south.setLayout(new FlowLayout());
		south.add(search);
		south.add(submit);
		south.add(clear);
		south.add(quit);
		
	}
	
}
