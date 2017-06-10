package com.core;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnDB {
	  public Connection conn = null;
	  public Statement stmt = null;
	  public ResultSet rs = null;
	  private static String propFileName = "/com/connDB.properties";
	  private static Properties prop = new Properties();
	  private static String dbClassName ="com.mysql.jdbc.Driver";
	  private static String dbUrl =
	      "jdbc:mysql://127.0.0.1:3306/db_librarySys?user=root&password=941203&useUnicode=true";
	  public ConnDB(){
	    try {
	      InputStream in=getClass().getResourceAsStream(propFileName);
	      prop.load(in);									//ͨ���������������Properties�ļ�
	      dbClassName = prop.getProperty("DB_CLASS_NAME");	//��ȡ��ݿ���
	      dbUrl = prop.getProperty("DB_URL",dbUrl);
	    }
	    catch (Exception e) {
	      e.printStackTrace();		//����쳣��Ϣ
	    }
	  }

	  public static Connection getConnection() {
	    Connection conn = null;
	    try {
	      Class.forName(dbClassName).newInstance();
	      conn = DriverManager.getConnection(dbUrl);
	    }
	    catch (Exception ee) {
	      ee.printStackTrace();
	    }
	    if (conn == null) {
	      System.err.println(
	          "����: DbConnectionManager.getConnection() �����ݿ�����ʧ��.\r\n\r\n��������:" +
	          dbClassName + "\r\n����λ��:" + dbUrl);
	    }
	    return conn;
	  }

	/*
	 * ���ܣ�ִ�в�ѯ���
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs;
	}

	/*
	 * ����:ִ�и��²���
	 */
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			conn = getConnection();				
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);		
		} catch (SQLException ex) {
			result = 0;
		}
		return result;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
