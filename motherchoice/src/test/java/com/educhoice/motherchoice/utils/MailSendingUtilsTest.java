package com.educhoice.motherchoice.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.educhoice.motherchoice.models.nonpersistent.authorization.MailSource;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MailSendingUtilsTest {

	private static final Logger log = LoggerFactory.getLogger(MailSendingUtilsTest.class);

	private Token token;

	@Autowired
	RandomStringUtils randomStringUtils;

	@Autowired
	MailSendingUtils mailSendingUtils;

	@Value("${local.server.port}")
	private int serverPort;

	@Value("${property.receiverMailAddress}")
	public String receiverMailAddress;

	@Value("${property.mailSenderId}")
	public String mailSenderId;

	@Value("${property.mailSenderPassword}")
	public String mailSenderPassword;

	@Value("${property.mailLinkUrl}")
	public String mailLinkUrl;

	@Before
	public void setup() {
		RestAssured.port = serverPort;

		Token setupToken = new Token();
		setupToken.setEmail(receiverMailAddress);
		setupToken.setTokenValue(randomStringUtils.generateRandomString(20));
		this.token = setupToken;
	}

	@Test
	public void mailSendTest() {
		log.debug("receiver mail address : {}", receiverMailAddress);
		mailSendingUtils.setReceiverMailAddress(receiverMailAddress);
		mailSendingUtils.sendMailForToken(token);
	}

}