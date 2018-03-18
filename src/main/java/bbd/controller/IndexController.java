package bbd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bbd.domain.Car;
import bbd.domain.Cars;
import bbd.exceptions.InvalidInputException;
import bbd.utils.StringUtils;

@Controller
public class IndexController {

	public static Cars cars;
	int turn;
	int carCount;

	@GetMapping("/")
	public String getIndexPage() {
		return "index";
	}

	@PostMapping("/name")
	public String getCarNames(String names, Model model) {
		if (names == null) {
			throw new InvalidInputException("입력 값이 없습니다!");
		}

		ArrayList<String> inputList = StringUtils.splitInput(names);

		cars = new Cars();
		for (String name : inputList) {
			cars.add(new Car(name));
		}
		
		model.addAttribute("cars", cars);

		return "ajaxGame";
	}
	
	@GetMapping("/result")
	public String getResult(@RequestParam("turn") int turn, Model model) {
		
		for (int i = 0; i < turn; i++) {
			cars.moveCars();
		}
		model.addAttribute("cars", cars);		
		return "result";
	}
}
