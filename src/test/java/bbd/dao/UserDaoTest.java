package bbd.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import bbd.jdbc.ConnectionManager;
import bbd.model.Token;
import bbd.model.User;

public class UserDaoTest {
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    @Before
    public void setup() {
    }

    @Test
    public void crud() throws Exception {
        User expected = new User("musicoverybook@gmail.com", "password", "bbd", "seoul");
        UserDao userDao = new UserDao();
        userDao.insertUser(expected);
        User actual = userDao.findUserByUserId(expected.getEmailId());
        assertEquals(expected, actual);

        expected.update(new User("musicoverybook@gmail.com", "password2", "pobi2", "busan"));
        userDao.updateUser(expected);
        actual = userDao.findUserByUserId(expected.getEmailId());
        assertEquals(expected, actual);
        
    	userDao.deleteUser(actual);
    	assertNull(userDao.findUserByUserId(actual.getEmailId()));
        
    }
    
    @Test
	public void 유저삭제테스트() throws Exception {
//    	User expected =new User("musicoverybook@gmail.com", "password2", "pobi2", "busan");
    	User expected =new User("musicoverybook@gmail.com", "password", "aaa", "seoul");
    	UserDao userDao = new UserDao();
    	userDao.deleteUser(expected);
    	assertNull(userDao.findUserByUserId(expected.getEmailId()));
	}

    @Test
    public void findAll() throws Exception {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();
        assertEquals(4, users.size());
    }
    
    @Test
	public void 토큰생성_테스트() throws Exception {
		UserDao userDao = new UserDao();
		
		Token expected = new Token("musicoverybook@gmail.com");
		userDao.insertToken(expected);
		Token actual = userDao.getToken(expected);
		assertEquals(expected,actual);
		log.debug("expected : "+expected.getToken());
		log.debug("actual : "+actual.getToken());
	}
    
    @Test
	public void 토큰_유저_조회() throws Exception {
		UserDao userDao = new UserDao();
		
		Token expected = new Token("musicoverybook@gmail.com");
		userDao.insertToken(expected);
		Token token = userDao.findByToken(expected.getToken());
//		Token token = userDao.findByToken(902888590);
		log.debug("token email : "+token.getEmailId());
		User user = userDao.findUserByUserId(token.getEmailId());
		log.debug("User email : "+ user.getEmailId());
		assertEquals("musicoverybook@gmail.com",user.getEmailId());
	}
    @Test
	public void 토큰삭제_테스트() throws Exception {
    	UserDao userDao = new UserDao();
    	Token token = userDao.findByToken(107327474);
//    	Token token = userDao.findByToken(expected.getToken());
    	userDao.deleteToken(token);
    	assertNull(userDao.findByToken(token.getToken()));
	}
    
    
}