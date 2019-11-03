package com.library.util;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String username = "jsltp";
			String pw = "1234";
			
			//而ㅻ꽖�뀡���씠 �깉濡쒖슫 而ㅻ꽖�뀡�쓣 �깮�꽦�븷 �븣 �궗�슜�븷 而ㅻ꽖�뀡�뙥�넗由щ�� �깮�꽦.
			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			 // PoolableConnection�쓣 �깮�꽦�븯�뒗 �뙥�넗由� �깮�꽦.
            // DBCP�뒗 而ㅻ꽖�뀡�쓣 蹂닿��븷 �븣 PoolableConnection �쓣 �궗�슜
            // �떎�젣 而ㅻ꽖�뀡�쓣 �떞怨� �엳�엳�쑝硫�, 而ㅻ꽖�뀡 ���쓣 愿�由ы븯�뒗�뜲 �븘�슂�븳 湲곕뒫�쓣 �젣怨듯븳�떎.
            // 而ㅻ꽖�뀡�쓣 close�븯硫� 醫낅즺�븯吏� �븡怨� 而ㅻ꽖�뀡 ���뿉 諛섑솚
			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			
			//而ㅻ꽖�뀡�씠 �쑀�슚�븳吏� �뿬遺�瑜� 寃��궗�븷 �븣 �궗�슜�븯�뒗 荑쇰━瑜� 吏��젙�븳�떎.
			poolableConnFactory.setValidationQuery("select 1");

			
			//而ㅻ꽖�뀡 ���쓽 �꽕�젙 �젙蹂대�� �깮�꽦�븳�떎.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			
			 //�쑀�쑕 而ㅻ꽖�뀡 寃��궗 二쇨린	
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);//�쑀�슚 而ㅻ꽖�뀡 寃��궗 二쇨린(諛�由ъ큹)
			poolConfig.setTestWhileIdle(true);//而ㅻ꽖�뀡�씠 �쑀�슚�븳吏� 寃��궗
			poolConfig.setMinIdle(4);//���씠 �쑀吏��븷 理쒖냼 而ㅻ꽖�뀡 �닔
			poolConfig.setMaxTotal(50);//���씠 愿�由ы븷 而ㅻ꽖�뀡 理쒕� 媛��닔

			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = 
					(PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("oracledb", connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}