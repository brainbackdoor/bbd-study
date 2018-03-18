package com.educhoice.motherchoice.models.persistent.authorization;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicAccountTest {

    private static final Logger log = LoggerFactory.getLogger(BasicAccountTest.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    private BasicAccount basicAccount;

    @Before
    public void setUp() {
        this.basicAccount = new CorporateAccount("wheejuni", "1234", BasicAccount.AccountRoles.ADMIN);
    }

    @Test
    public void 비밀번호암호화() {
        this.basicAccount.encryptPassword(this.passwordEncoder);
        log.debug(this.basicAccount.getPassword());
    }


}