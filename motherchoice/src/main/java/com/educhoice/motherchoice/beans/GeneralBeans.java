package com.educhoice.motherchoice.beans;

import com.educhoice.motherchoice.models.nonpersistent.authorization.MailSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneralBeans {

    @Value("${property.mailSenderId}")
    public String mailSenderId;

    @Value("${property.mailSenderPassword}")
    public String mailSenderPassword;

    @Value("${property.mailLinkUrl}")
    public String mailLinkUrl;

    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public MailSource getMailSource() {
        return new MailSource(mailSenderId, mailSenderPassword, mailLinkUrl);
    }
}
