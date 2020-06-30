import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class numsearch extends JDialog implements ActionListener {
	Container contentPane;
	JLabel jLabel1 = new JLabel();
	JButton searchInfo = new JButton("ȷ��");
	Dimension faceSize = new Dimension(300, 100);
	
	String[] s;
	JComboBox selectSnum;
	public numsearch(JFrame frame){
		super(frame, true);
		this.setTitle("ѧ�Ų�ѯ");
		this.setSize(faceSize);
		this.setResizable(false);
		try {
			Init();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation( (int) (screenSize.width - 400) / 2 ,
						(int) (screenSize.height - 300) / 2 +45);

	
		
	}
	private void Init() {
		this.setSize(faceSize);
		contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());
		jLabel1.setText("������ѧ�ţ�");
		jLabel1.setFont(new Font("Dialog",0,12));
		contentPane.add(jLabel1);
		StuBean getId = new StuBean();
		s = getId.getAllId();

	 selectSnum = new JComboBox(s);
	 selectSnum.setEditable(true);
		selectSnum.setFont(new Font("Dialog",0,12));
		contentPane.add(selectSnum);

		searchInfo.setText("��ѯ");
		searchInfo.setFont(new Font("Dialog",0,12));
		contentPane.add(searchInfo);
		
		searchInfo.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == selectSnum) { //�˳�
			this.dispose();
		}
		else if (obj == searchInfo) { //�޸�
			this.dispose();
		}
	}
	public String getSnum() {
		return (String)this.selectSnum.getSelectedItem();
	}

}
