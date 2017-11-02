package bbd.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	String host = "smtp.naver.com";
	final String user = "linl14";
	final String password = "ahrzosel1!";
	final String domain = "http://localhost:8080/users/parentJoinForm2nd";
	
	
	// Get the session object
	Properties props = new Properties();

	public MailSender(){
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
	}
	
	public void sendMail(String user_id, String token) {

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

			message.setSubject("Edu-connect 가입 인증 메일입니다.");

			message.setText("Link : "+domain+"?token="+token);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}