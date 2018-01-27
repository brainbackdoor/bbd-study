package com.educhoice.motherchoice.utils.aws;

import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class S3ControlServiceTest {

    @Autowired
    private S3ControlService s3ControlService;

    private CorporateAccount corporateAccount;

    @Before
    public void setUp() {
        this.corporateAccount = CorporateAccount.builder()
                .accountName("elly")
                .paid(true)
                .build();
    }

    @Test
    public void 버킷생성작업() {
        s3ControlService.init();
        s3ControlService.generateBucket(this.corporateAccount);
    }

}