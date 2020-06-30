import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class StuBean {
	String sql;
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	String sNum;
	String sName;
	String sSex;
	String sBirth;
	String sPhone;
	String sMajor;
	
	int stuId;
	public void stuModify(String num, String name, String sex, String age, String phone, String major
			) {
	this.sNum = num;
	this.sName = name;
	this.sSex = sex;
	this.sBirth =age;
	this.sPhone = phone;
	this.sMajor = major;
	
		if(name == null||name.equals("")){
			JOptionPane.showMessageDialog(null, "请输入学生姓名", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else{
			sql = "update information set sName =? , sSex = ?, sBirth =? , sPhone = ?,sMajor = ?"
                + " where sNum = "+ sNum + "";
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1,sName);
				ps.setString(2,sSex);
				ps.setString(3, sBirth);
				ps.setString(4, sPhone);
				ps.setString(5, sMajor);
				int flag = ps.executeUpdate();
				ps.close();
				conn.close();	
				if(flag>0){
				JOptionPane.showMessageDialog(null, "成功修改一条新的纪录！");
			    } 
				else{
					JOptionPane.showMessageDialog(null, "该学生不存在", "该学生不存在", JOptionPane.ERROR_MESSAGE);
				}}
			catch(Exception e){
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "更新失败", "错误", JOptionPane.ERROR_MESSAGE); 
			}}}
		
	
	public String[] stuSearch(String num) {
		
		this.sNum = num;
		String[] s = new String[5];
		sql = "select * from information where sNum = "+sNum+"";
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
			Statement statement = conn.createStatement();
			  	rs=statement.executeQuery(sql);
			if(rs.next()){
				s[0] = rs.getString("sName");
				s[1] = rs.getString("sSex");
				s[2] = rs.getString("sBirth");
				s[3] = rs.getString("sPhone");
				s[4] = rs.getString("sMajor");
				
			}
			else
				s = null;
		}
		catch(Exception e){
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
  				
  			}}
		return s;
		
		
	}
	public String[] getAllId(){
		String[] s = null;
		int row = 0;
		int i = 0;
		
		sql = "select sNum from information";
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
			Statement statement = conn.createStatement();
			  	rs=statement.executeQuery(sql);
			if(rs.last()){
				row = rs.getRow();
			}

			if(row == 0){
				s = null;
			}
			else{
				s = new String[row];
				rs.first();
				rs.previous();
				while(rs.next()){
					s[i] = rs.getString(1);
					i++;
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
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
				
			}}
		
		return s;
	}
	public void studelete(String num, String name, String sex, String age, String phone, String major
			) {
		        this.sNum = num;
				sql = "delete from information where sNum = "+sNum+"";
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=123456");
					PreparedStatement ps = conn.prepareStatement(sql);
					int flag = ps.executeUpdate();
					ps.close();
					conn.close();	
					if(flag>0){
					JOptionPane.showMessageDialog(null, "成功删除一条纪录！");
				    } 
					}
				catch(Exception e){
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE); 
				}}}
		
		


