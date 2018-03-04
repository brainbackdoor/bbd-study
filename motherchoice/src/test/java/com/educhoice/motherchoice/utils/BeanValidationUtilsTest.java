package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.models.nonpersistent.authorization.EmailAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanValidationUtilsTest {

    @Autowired
    private BeanValidationUtils utils;

    private EmailAddress address;

    @Before
    public void setUp() {
        this.address = new EmailAddress("fuck!");
    }

    @Test
    public void BEAN_VALIDATION_BY_UTIL() {
        assertThat(utils.validateObject(address).size(), is(1));
    }
}