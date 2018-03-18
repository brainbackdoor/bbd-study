package bbd.domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {
	public List<Car> cars = new ArrayList<>();
	
	public void add(Car car) {
		cars.add(car);	
	}
	public int getCount() {
		return this.cars.size();
	}
	
	public List<Car> getCars() {
		return this.cars;
	}
	
	public void moveCars() {
		for (Car car : cars) {
			car.move();
		}
	}
	
}
