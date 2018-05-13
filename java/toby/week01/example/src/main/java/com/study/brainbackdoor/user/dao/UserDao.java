package com.study.brainbackdoor.user.dao;

import com.study.brainbackdoor.user.domain.User;

import java.sql.*;

/*
    JDBC 작업 순서 :
        1. DB 연결을 위한 Connection을 가져옴
        2. SQL을 담은 Statement(또는 PreparedStatement)를 만듦
        3. 만들어진 Statement를 실행
        4. 조회의 경우 SQL 쿼리 실행결과를 ResultSet에 받아서 정보를 저장할 오브젝트에 옮겨줌
        5. 작업중 생성된 Connection, Statement, ResultSet 등의 리소스를 반납
        - 추가적으로, JDBC API Exception 처리가 필요함
 */

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/studydb", "bbd", "bbd");
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "bbd", "bbd");
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
