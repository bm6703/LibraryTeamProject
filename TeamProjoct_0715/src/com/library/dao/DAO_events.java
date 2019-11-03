package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.util.DBConn;

public class DAO_events {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private DAO_events() {
	}

	private static DAO_events instance = new DAO_events();

	public static DAO_events getInstance() {
		return instance;
	}

	// get connection
	private Connection getConnection() throws Exception {
		conn = DBConn.getConnection();
		return conn;
	}

	// event 테이블에 있는 id 숫자를 산출하는 메소드.
	public int getMaxValue() {
		int maxValue = 0;
		String sql = "select max(id) from events";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxValue = rs.getInt("max(id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return maxValue;
	}

	// event 테이블에 있는 id 값을 +1 해주는 메소드.
	public void updateMaxValue() {
		String sql = "update events set id=id+1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// close all
	private void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
