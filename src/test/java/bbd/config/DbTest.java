package bbd.config;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class DbTest {
	
	@Test
	public void 입력쿼리테스트() {
		Db db = new Db();
		db.connection();
		String sql = "INSERT INTO TOKEN_TB(EMAIL_ID,TOKEN) VALUES(?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, "ddd@google.com");
			pstmt.setString(2, "123456");
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void 토큰조회테스트() {
		Db db = new Db();
		db.connection();
		String token = db.getEmail("ddd@google.com");
		assertEquals("123456",token);
	}

	@Test
	public void selectTest() {
		Db db = new Db();
		db.connection();
		ResultSet rs;
		String sql;
		Statement stmt;
		try {
			stmt = db.getConn().createStatement();
			sql = "SELECT * FROM MEMBER_TB WHERE EMAIL_ID = 'abc@google.com'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int uid = rs.getInt(1);
				String name = rs.getString("EMAIL_ID");
				// Date date = rs.getDate("START_DATE"); //java.sql.date
				System.out.printf("%d, %s\n", uid, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
