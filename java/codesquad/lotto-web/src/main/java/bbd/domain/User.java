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

	@Column(nullable = false, unique = true, length = 25)
	String userId;

	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	String name;
	String email;
	int money;
	int pay;
	public boolean update(User user, String password_new) {
		if (matchPassword(user.password)) {
			this.password=password_new; 
			this.name=user.name;
			this.email=user.email;
			return true;
		}
		return false;
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMoney() {
		return money;
	}

	public int getPay() {
		return pay;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}



}
