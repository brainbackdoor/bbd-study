package com.educhoice.motherchoice.beans;

import com.educhoice.motherchoice.models.nonpersistent.authorization.MailSource;
import com.educhoice.motherchoice.utils.aws.AWSInstanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AWSInstanceService profileCredentialsService() {
        return new AWSInstanceService();
    }

}
