package bbd.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {
	Connection conn;
	public void connection() {
		String addr = "jdbc:mysql://192.168.128.215/ACADEMY";
		String user = "hotsix";
		String pw = "qjxjdhkvmf";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No Driver");
			System.err.println(e.getMessage());
			return;
		}

		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Success to connect");

		} catch (SQLException e) {
			System.err.println("No Connection");
			System.err.println(e.getMessage());
		}
	}

	public String getEmail(String token) {
		ResultSet rs;
		String sql;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			sql = "SELECT * FROM TOKEN_TB WHERE TOKEN = '"+token+"'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int uid = rs.getInt(1);
				String email_id = rs.getString("EMAIL_ID");
				token = rs.getString("TOKEN");
				System.out.printf("%d, %s, %s\n", uid, email_id, token);
				return email_id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setToken(String user_id, String token) {

		String sql = "INSERT INTO TOKEN_TB(EMAIL_ID,TOKEN) VALUES(?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id );
			pstmt.setString(2, token);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delToken(String token) {
		String sql = "DELETE FROM TOKEN_TB WHERE TOKEN = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, token);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}


	
}
