package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmailAddressTest {

    private static final Logger log = LoggerFactory.getLogger(EmailAddressTest.class);

    private EmailAddress email;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Before
    public void setUp() {
        this.email = new EmailAddress("hi@hi.com");
    }

    @Test
    public void JSONCreation_Of_Email() throws Exception{
        log.info(new ObjectMapper().writeValueAsString(email));
    }

    @Test
    public void BEAN_VALIDATION_FILTERS_NON_EMAIL(){
        EmailAddress address = new EmailAddress("shit!");

        validator.validate(address).stream().forEach(c -> log.debug(c.getMessage()));
        assertThat(validator.validate(address).size(), is(1));

    }

}