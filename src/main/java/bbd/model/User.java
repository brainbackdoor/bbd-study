package bbd.model;

public class User {
	private String emailId;
	private String password;
	private String alias;
	private String area;
	public User(String emailId, String password, String alias) {
		this.emailId = emailId;
		this.password = password;
		this.alias = alias;
	}
	public User(String emailId, String password, String alias, String area) {
		this.emailId = emailId;
		this.password = password;
		this.alias = alias;
		this.area = area;
	}

	public void update(User updateUser) {
		this.password = updateUser.password;
		this.alias = updateUser.alias;
		this.area = updateUser.area;
	}


	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public String getAlias() {
		return alias;
	}

	public String getArea() {
		return area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		User other = (User) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + emailId + ", password=" + password + ", name=" + alias + ", email=" + area + "]";
	}
}
