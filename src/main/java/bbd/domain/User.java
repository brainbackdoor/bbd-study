package bbd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	@Id
	@GeneratedValue
	Long id;

	@Column(nullable = false, unique = true)
	String userId;

	@Column(nullable = false)
	String password;
	
	@Column(nullable = false)
	String name;

	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	public boolean matchId(Long userId2) {
		return this.id.equals(userId2);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
