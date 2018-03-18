package bbd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Statistics {
	@Id
	@GeneratedValue
	Long id;
	
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_lottostatistics_user"))
	private User byer;
	
	@Column(nullable = false)
	int payment;
	@Column(nullable = false)
	float profit;
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public User getByer() {
		return byer;
	}
	public void setByer(User byer) {
		this.byer = byer;
	}
	
}
