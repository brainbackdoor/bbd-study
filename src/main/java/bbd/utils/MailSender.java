package bbd.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bbd.model.Token;

public class MailSender {
	String host = "smtp.naver.com";
	final String user = "NAVER_ID";
	final String password = "PASSWORD";
	final String domain = "URL";

	// Get the session object
	Properties props = new Properties();

	public MailSender(){
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
	}
	
	public void sendMail(String user_id, Token token) {

		String to = user_id;

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("가입 인증 메일입니다.");

			message.setText("Link : "+domain+"?tokenNumber="+token.getToken());

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}