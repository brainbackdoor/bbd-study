package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    AccountRepository accountRepository;

    private MemberAddress memberAddress;
    private Account account;

    @Before
    public void setUp() {
        this.memberAddress = new MemberAddress("경기도", "김포시", "풍무동");
        this.account = new Account("wheejuni@gmail.com","1234","포비",this.memberAddress);
    }

    @Test
    public void 객체저장() {
        accountRepository.save(account);
        BasicAccount testAccount = accountRepository.findByEmail("wheejuni@gmail.com").get();
        System.out.println((Account)testAccount);
        assertNotNull(testAccount);
    }

}