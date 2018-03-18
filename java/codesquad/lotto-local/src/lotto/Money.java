package lotto;

public class Money {
	int money = 0;

	Money(int money) {
		this.money = money;
	}

	int paymentOfLotto() {
		int count = countOfLotto();
		return count;
	}

	int countOfLotto() {
		return money / 1000;
	}

	public int getMoney() {
		return money;
	}

}
