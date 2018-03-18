package bbd.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.domain.User;
import bbd.domain.UserRepository;

@Controller
public class HomeController {
	@Autowired 
	UserRepository userRepository;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	

}
