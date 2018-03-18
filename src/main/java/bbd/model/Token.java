package bbd.model;

import java.util.Random;

public class Token {
	private String emailId;
	private int token;

	public Token(String emailId) {
		this.emailId = emailId;
		this.token = generateToken();
	}
	public Token(String emailId, int token) {
		this.emailId = emailId;
		this.token = token;
	}
	private int generateToken() {
		Random rand = new Random();
		int token = rand.nextInt(1000000000) + 100000000;
		if (token > 1000000000) {
			token = token - 100000000;
		}
		return token;
	}

	public int getToken() {
		return token;
	}

	public String getEmailId() {
		return emailId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + token;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (token != other.token)
			return false;
		return true;
	}

}
