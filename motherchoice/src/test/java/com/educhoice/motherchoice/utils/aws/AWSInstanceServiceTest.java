package com.educhoice.motherchoice.utils.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AWSInstanceServiceTest {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @Test
    public void 자격증명생성() {
        AWSCredentialsProvider provider = awsInstanceService.returnProfileCredentials();

        assertThat(provider.getClass().getName(), is("com.amazonaws.auth.profile.ProfileCredentialsProvider"));

    }

}