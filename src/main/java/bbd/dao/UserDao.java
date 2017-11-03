package bbd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bbd.jdbc.ConnectionManager;
import bbd.jdbc.JdbcTemplate;
import bbd.jdbc.RowMapper;
import bbd.model.Token;
import bbd.model.User;

public class UserDao {
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	private Connection con = ConnectionManager.getConnection();

	public void insertUser(User user) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "INSERT INTO MEMBER_TB (EMAIL_ID, PASSWORD, ALIAS, AREA) VALUES (?, ?, ?, ?)";
		template.update(sql, user.getEmailId(), user.getPassword(), user.getAlias(), user.getArea());

	}
	public void insertToken(Token token) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "INSERT INTO TOKEN_TB (EMAIL_ID, TOKEN) VALUES (?, ?)";
		log.debug("insert token email : "+token.getEmailId());
		log.debug("insert token token : "+token.getToken());
		template.update(sql, token.getEmailId(), token.getToken());

	}
	public <T> T getToken(Token token) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "SELECT EMAIL_ID, TOKEN FROM TOKEN_TB WHERE EMAIL_ID=?";
		return (T) template.queryForObject(sql, new RowMapper() {

			@Override
			public Token mapRow(ResultSet rs) throws SQLException {
				return token;
			}

		}, token.getEmailId());
	}

	public void updateUser(User user) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "UPDATE MEMBER_TB SET PASSWORD=?,ALIAS=?,AREA=? WHERE EMAIL_ID=?";
		template.update(sql, user.getPassword(), user.getAlias(), user.getArea(), user.getEmailId());
	}
	public void deleteUser(User user) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "DELETE FROM MEMBER_TB WHERE EMAIL_ID=?";
		template.update(sql, user.getEmailId());
	}
	
	public void deleteToken(Token token) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "DELETE FROM TOKEN_TB WHERE TOKEN=?";
		template.update(sql, token.getToken());
	}
	public <T> List<T> findAll() {
		JdbcTemplate template = new JdbcTemplate();

		String sql = "SELECT * FROM MEMBER_TB";
		return template.query(sql, new RowMapper() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("EMAIL_ID"), rs.getString("PASSWORD"), rs.getString("ALIAS"),
						rs.getString("AREA"));
			}

		});
	}

	public <T> T findUserByUserId(String userId) {
		JdbcTemplate template = new JdbcTemplate();

		String sql = "SELECT EMAIL_ID, PASSWORD, ALIAS, AREA FROM MEMBER_TB WHERE EMAIL_ID=?";
		return (T) template.queryForObject(sql, new RowMapper() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("EMAIL_ID"), rs.getString("PASSWORD"), rs.getString("ALIAS"),
						rs.getString("AREA"));
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((con == null) ? 0 : con.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDao other = (UserDao) obj;
		if (con == null) {
			if (other.con != null)
				return false;
		} else if (!con.equals(other.con))
			return false;
		return true;
	}


}
