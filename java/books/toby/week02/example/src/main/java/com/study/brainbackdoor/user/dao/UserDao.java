package com.study.brainbackdoor.user.dao;

import com.study.brainbackdoor.user.domain.User;

import java.sql.*;

/*
    UserDao의 관심사 :
        JDBC API를 사용할 것인가, DB 전용 API를 사용할 것인가,
        어떤 테이블 이름과 필드 이름을 사용해 어떤 SQL 을 만들 것인가,
        어떤 오브젝트를 통해 DB에 저장할 정보를 전달받고 DB에서 꺼내온 정보를 저장해서 넘겨줄 것인가와 같은 관심을 가진 코드를 모아둔 것이다.

        따라서 이런 관심사가 바뀌면 그 때 변경이 일어난다.
 */

public  class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
        connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeNewConnection();
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
