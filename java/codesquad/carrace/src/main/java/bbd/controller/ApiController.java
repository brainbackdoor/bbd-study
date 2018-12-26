package bbd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bbd.domain.Cars;
import bbd.domain.Result;
import bbd.exceptions.InvalidInputException;

@RestController
public class ApiController {
	
	@GetMapping("/api/move")
	public Result moveCar() {
		Cars cars = IndexController.cars;
		Result result = null;
		try {
		    cars.moveCars();
		    result = Result.ok(cars);
		    result.setCars(cars);
	        return result;
        } catch (InvalidInputException e) {
            result = Result.error(e.getMessage());
            result.setCars(cars);
            return result;
        }
	}
}
