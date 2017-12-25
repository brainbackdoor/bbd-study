package com.educhoice.motherchoice.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;

import com.educhoice.motherchoice.models.nonpersistent.authorization.MailSource;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;


public class MailSendingUtils {

	final String HOST = "smtp.naver.com";

	MailSource ms;

	private String RECEIVER_MAIL_ADDRESS;

	// Get the session object
	private Properties props = new Properties();

	public MailSendingUtils(String RECEIVER_MAIL_ADDRESS, MailSource ms) {
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", "true");
		this.ms = ms;
		this.RECEIVER_MAIL_ADDRESS = RECEIVER_MAIL_ADDRESS;
	}

	private void sendMail(String subject, String mailMessage) {
		try {
			MimeMessage message = setMailBasic(subject);
			message.setText(mailMessage);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private MimeMessage setMailBasic(String subject) throws MessagingException, AddressException {
		Session session = getMailSenderSession();
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(ms.getMailSenderId()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVER_MAIL_ADDRESS));
		message.setSubject(subject);
		return message;
	}

	private Session getMailSenderSession() {
		return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ms.getMailSenderId(), ms.getMailSenderPassword());
			}
		});
	}

	public void sendMailForToken(Token token) {
		String subject = "가입 인증 메일입니다.";
		sendMail(subject, generateMailMessageForToken(token));
	}

	private String generateMailMessageForToken(Token token) {
		return "Link : " + ms.getMailTokenLinkUrl() + "?token=" + token.getTokenValue();
	}

}