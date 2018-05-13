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

/*
    리팩토링 : 기존의 코드를 외부의 동작방식에는 변화없이 내부 구조를 변경해서 재구성하는 작업을 말한다.
 */

/*
    슈퍼클래스에 기본적인 로직의 흐름(커넥션 가져오기, SQL 생성, 실행, 반환)을 만들고,
    그기능의 일부를 추상 메소드나 오버라이딩이 가능한 protected 메소드 등으로 만든 뒤 서브 클래스에서 이런 메소드를 필요에 맞게 구현해서 사용하도록 하는 방법을
    디자인 패턴에서 '템플릿 메소드 패턴' 이라고 한다.

    그리고 UserDao의 서브클래스의 getConnection() 메소드는 어떤 Connection 클래스의 오브젝트를 어덯게 생성할 지를 결정하는 방법이라고도 볼 수 있다.
    이렇게 서브클래스에서 구체적인 오브젝트 생성 방법을 결정하게 하는 것을 팩토리 메소드 패턴이라고 부르기도 한다.
 */

/*
    상속은 한계점이 있다.
    1. 자바는 다중상속을 허용하지 않는다.
    2. 상속관계는 생각보다 밀접하다.
    3. 다른 클래스들이 계속 만들어질 경우 getConnection() 구현코드가 중복된다.
 */

public abstract class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        c.close();
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
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
