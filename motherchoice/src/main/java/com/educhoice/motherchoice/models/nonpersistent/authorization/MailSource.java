package com.educhoice.motherchoice.models.nonpersistent.authorization;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

@Getter
public class MailSource {

	@Value("${property.mailSenderId}")
	private String mailSenderId;

	@Value("${property.mailSenderPassword}")
	private String mailSenderPassword;

	@Value("${property.mailLinkUrl}")
	private String mailLinkUrl;

	private String mailTokenLinkUrl;

	public MailSource() {
	}

	public MailSource(String sender, String sender_password, String sender_url) {
		this.mailSenderId = sender;
		this.mailSenderPassword = sender_password;
		this.mailLinkUrl = sender_url;
		this.mailTokenLinkUrl = sender_url + "/api/token";
	}

	@Override
	public String toString() {
		return "MailSource [mailSenderId=" + mailSenderId + ", mailLinkUrl=" + mailLinkUrl + ", mailTokenLinkUrl="
				+ mailTokenLinkUrl + "]";
	}


}
