package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.util.DBConn;

public class DAO_booklocation {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private DAO_booklocation() {}
	private static DAO_booklocation instance = new DAO_booklocation();
	
	public static DAO_booklocation getInstance() {
		return instance;
	}

	// get connection
	private Connection getConnection() throws Exception {
		conn = DBConn.getConnection();
		return conn;
	}
	

	//��Ÿ�� ABCD
	public List<String[]> booktNc(){
		String sql = "select booktype,count(*) from booklist group by booktype order by booktype";
		List<String[]> tNcList =new ArrayList<String[]>();
		
		int booknumInshelf = 28;
		
		//tNcList�ȿ� tNc�迭�� ����
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			

			
			while(rs.next()) {
				//vo=new VO_Booklist();�ִºκ��� vo������ ������ string�迭�� �ִ´�.
				String[] tNc= new String[2];
				tNc[0]=rs.getString("booktype");
				tNc[1]=""+  rs.getInt(2)/booknumInshelf + 1;
				tNcList.add(tNc);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		//System.out.println("dao_member / vo : " + vo);
		
		
		
		
		
		/* ���߿� ���켼��
		String[] temp = new String[2];
		temp[0] = "E";
		temp[1] = "12";
		tNcList.add(temp);
		*/
		
		return tNcList;
	}
	
	
	
	//close all
	private void closeAll() {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


