package bbd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bbd.jdbc.ConnectionManager;
import bbd.jdbc.JdbcTemplate;
import bbd.jdbc.KeyHolder;
import bbd.jdbc.RowMapper;
import bbd.model.AcademyUser;
import bbd.model.Token;

public class AcademyUserDao {
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	private Connection con = ConnectionManager.getConnection();
	private JdbcTemplate template = JdbcTemplate.getInstance();

	public void insertUser(AcademyUser user) {
		KeyHolder keyHolder = new KeyHolder();
		String sql = "INSERT INTO ACADEMY_USER_TB (ACADEMY_EMAIL_ID, PASSWORD, ACADEMY_NM, ACADEMY_NUM, OWNER_NM) VALUES (?, ?, ?, ?, ?)";
		template.update(sql, keyHolder, user.getEmailId(), user.getPassword(), user.getAcademyName(), user.getAcademyNum(), user.getOwnerName());

	}
	public void insertToken(Token token) {
		KeyHolder keyHolder = new KeyHolder();
		String sql = "INSERT INTO TOKEN_TB (EMAIL_ID, TOKEN) VALUES (?, ?)";
		template.update(sql, keyHolder, token.getEmailId(), token.getToken());

	}
	public <T> T getToken(Token token) {
		String sql = "SELECT EMAIL_ID, TOKEN FROM TOKEN_TB WHERE EMAIL_ID=?";
		return (T) template.queryForObject(sql, new RowMapper() {
			@Override
			public Token mapRow(ResultSet rs) throws SQLException {
				return token;
			}
		}, token.getEmailId());
	}

	public void updateUser(AcademyUser user) {
		KeyHolder keyHolder = new KeyHolder();
		String sql = "UPDATE ACADEMY_USER_TB SET PASSWORD=?,ACADEMY_NM=?,OWNER_NM=?,ACADEMY_AREA=? WHERE ACADEMY_EMAIL_ID=?";
		template.update(sql,keyHolder, user.getPassword(), user.getArea(), user.getEmailId());
	}
	public void deleteUser(AcademyUser user) {
		KeyHolder keyHolder = new KeyHolder();
		String sql = "DELETE FROM ACADEMY_USER_TB WHERE ACADEMY_EMAIL_ID=?";
		template.update(sql, keyHolder, user.getEmailId());
	}
	
	public void deleteToken(Token token) {
		KeyHolder keyHolder = new KeyHolder();
		String sql = "DELETE FROM TOKEN_TB WHERE TOKEN=?";
		template.update(sql, keyHolder, token.getToken());
	}
	public <T> List<T> findAll() {
		String sql = "SELECT * FROM ACADEMY_USER_TB";
		return template.query(sql, new RowMapper() {
			@Override
			public AcademyUser mapRow(ResultSet rs) throws SQLException {
				return new AcademyUser(rs.getString("ACADEMY_EMAIL_ID"), rs.getString("PASSWORD"), rs.getString("ACADEMY_NM"), rs.getString("ACADEMY_NUM") ,
						rs.getString("OWNER_NM"));
			}
		});
	}

	public <T> T findUserByUserId(String userId) {
		JdbcTemplate template = new JdbcTemplate();

		String sql = "SELECT ACADEMY_EMAIL_ID, PASSWORD, ACADEMY_NM,ACADEMY_NUM, OWNER_NM FROM ACADEMY_USER_TB WHERE ACADEMY_EMAIL_ID=?";
		return (T) template.queryForObject(sql, new RowMapper() {
			@Override
			public AcademyUser mapRow(ResultSet rs) throws SQLException {
				return new AcademyUser(rs.getString("ACADEMY_EMAIL_ID"), rs.getString("PASSWORD"), rs.getString("ACADEMY_NM"),
						 rs.getString("ACADEMY_NUM"), rs.getString("OWNER_NM"));
			}
		}, userId);

	}
	public <T> T findByToken(int token) {
		JdbcTemplate template = new JdbcTemplate();

		String sql = "SELECT EMAIL_ID, TOKEN FROM TOKEN_TB WHERE TOKEN=?";
		return (T) template.queryForObject(sql, new RowMapper() {
			@Override
			public Token mapRow(ResultSet rs) throws SQLException {
				return new Token(rs.getString("EMAIL_ID"),rs.getInt("TOKEN"));
			}
		}, token);

	}
}
