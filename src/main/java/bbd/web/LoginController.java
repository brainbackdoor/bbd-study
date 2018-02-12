package bbd.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.dao.AcademyUserDao;
import bbd.dao.UserDao;
import bbd.model.AcademyUser;
import bbd.model.User;

@Controller
public class LoginController {
	@PostMapping("/login")
	public String auth(String emailId, String password, HttpSession session) {
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUserId(emailId);
		if (user == null) {
			return "redirect:/pages/login";
		}
		if(!user.getPassword().equals(password)) {
			return "redirect:/pages/login";
		}
		session.setAttribute("sessionedUser", user);
		return "redirect:/";
		
	}
	
	@PostMapping("/loginAcademy")
	public String authAcademy(String emailId, String password, HttpSession session) {
		AcademyUserDao userDao = new AcademyUserDao();
		AcademyUser user = userDao.findUserByUserId(emailId);
		if (user == null) {
			return "redirect:/pages/loginAcademy";
		}
		if(!user.getPassword().equals(password)) {
			return "redirect:/pages/loginAcademy";
		}
		session.setAttribute("academyUser", user);
		return "redirect:/";
		
	}	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return new ModelAndView("redirect:/");
	}
}
