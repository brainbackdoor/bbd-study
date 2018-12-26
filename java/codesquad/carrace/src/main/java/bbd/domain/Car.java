package bbd.domain;

import bbd.utils.RandomUtils;
import bbd.utils.StringUtils;

public class Car {

	private String name;

	Car() {
		this.name = "default";
	}

	public Car(String name) {
		this.name = name;
	}

	private int position;

	public int getPosition() {
		return this.position;
	}
	
	public String getHtmlPosition() {
		return StringUtils.positionPrint(this.position) + "&#128652;";
	}
	
	public void move() {
		move(RandomUtils.makeRandom());
	}

	void move(int random) {
		if (random > 8) {
			this.position++;
		}
	}

	public String getName() {
		return this.name;
	}

}
