package com.study.brainbackdoor;

import com.study.brainbackdoor.user.dao.UserDao;
import com.study.brainbackdoor.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BrainbackdoorApplication {
    private static final Logger log = LoggerFactory.getLogger(BrainbackdoorApplication.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SpringApplication.run(BrainbackdoorApplication.class, args);
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("bbd");
        user.setName("동규라몽");
        user.setPassword("브레인빽도어");

        dao.add(user);
        log.debug("{} 등록 성공", user.getId());

        User user2 = dao.get(user.getId());
        log.debug("{}", user2.getName());
        log.debug("{}", user2.getPassword());

        log.debug("{} 조회 성공", user2.getId());
    }
}
