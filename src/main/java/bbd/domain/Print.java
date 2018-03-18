package bbd.domain;

import bbd.utils.StringUtils;

public class Print {
	
	public static String carsPrint(Cars cars) {
		StringBuilder sb = new StringBuilder();
		
		for (Car car: cars.getCars()) {
			sb.append(car.getName() + " : ");
			sb.append(StringUtils.positionPrint(car.getPosition()));
			sb.append(StringUtils.LINE_SEPARATOR);
		}
		
		return sb.toString();
	
	}
	
	
}
