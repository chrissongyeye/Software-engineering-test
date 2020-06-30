import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class deleteinfo extends superwindow implements ActionListener{
	private JButton delete,search,clear,quit;
	
	private JTextField name, sex, age, phone, major;
	 private Connection conn=null;
		private PreparedStatement ps=null;
		private ResultSet rs=null;
		private JPanel p1,p2;
		String sNum_str = "";
		private JTextField num;
		
		public deleteinfo(){
			north = new JPanel();	
			center = new JPanel();
			south = new JPanel();
			delete = new JButton("删除");
			search=new JButton("查询学号");
			clear=new JButton("清空");
			quit=new JButton("退出");
			
			num = new JTextField(8);
			name = new JTextField(8);
			sex = new JTextField(8);
			age = new JTextField(8);
			phone = new JTextField(8);
			major = new JTextField(8);
			
	   
	        setNorthPanel(north);			//调用自己的三个设置JPanel()方法
			setCenterPanel(center);
			setSouthPanel(south);
			
			this.setTitle("信息删除窗口");
			this.setResizable(false);
			setlocate(300, 200, 500, 350);
			setLayout(new GridLayout(3, 1));
			add(north);		//将三个JPanel对象添加到窗口中
			add(center);
			add(south);
			
			validate();
			search.addActionListener(this);
			delete.addActionListener(this);
			clear.addActionListener(this);
			quit.addActionListener(this);
			
		}
		
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			String[] s = new String[5];
			 if(obj==search){
				numsearch siss = new numsearch(this);
				siss.pack();
				siss.setVisible(true);
				try{
					sNum_str = siss.getSnum();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "没有查找到该学号！"); 
				}

				StuBean searchStu = new StuBean();
				s = searchStu.stuSearch(sNum_str);
				if (s == null) {
					JOptionPane.showMessageDialog(null, "记录不存在！");
					num.setText("请查询学号");
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

					delete.setEnabled(false);
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
					delete.setEnabled(true); 
				}
				
			}else if(obj==delete){
				StuBean deleteStu = new StuBean();
				deleteStu.studelete(num.getText(), name.getText(), sex.getText(), age.getText(), phone.getText(), major.getText());
				deleteStu.stuSearch(num.getText());
			}
			else if(obj==clear){
				setNull();
				num.setText("请查询学号");
			}else if(obj==quit){
				this.dispose();
			}
			
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
		p1.add(new JLabel("学号"));
		p1.add(num);
		p1.add(new JLabel("姓名"));
		p1.add(name);
		p1.add(new JLabel("性别"));
		p1.add(sex);
		p2.add(new JLabel("年龄"));
		p2.add(age);
		p2.add(new JLabel("联系方式"));
		p2.add(phone);
		p2.add(new JLabel("专业"));
		p2.add(major);
		center.setLayout(new GridLayout(2,1));
		center.add(p1);
		center.add(p2);
		
	}

	@Override
	public void setNorthPanel(JPanel north) {
		JLabel label = new JLabel("学生信息删除");
		label.setFont(new Font("TimesRoman",Font.BOLD,34));
		label.setForeground(Color.RED);
		north.add(label);
		
	}

	@Override
	public void setSouthPanel(JPanel south) {
		south.setLayout(new FlowLayout());
		south.add(search);
		south.add(delete);
		south.add(clear);
		south.add(quit);
		
	}

	public void setvisible(boolean b) {
		this.setVisible(b);
		
	}
}
	