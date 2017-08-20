package bbd.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.domain.Statistics;
import bbd.domain.StatisticsRepository;
import bbd.domain.User;
import bbd.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	static final String USER_PATH = "/users";

	@Autowired // Spring Framework에서 초기화하기 때문에 따로 선언없이 쓸 수 있다.
	UserRepository userRepository;
	
	@Autowired
	StatisticsRepository statisticsRepository;


	@GetMapping("/{userId}")
	public ModelAndView show(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		User user = (User) value;
		User dbUser = userRepository.findByUserId(user.getUserId());
		session.setAttribute("sessionedUser", dbUser);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("pay", dbUser.getMoney());
		mav.addObject("money", dbUser.getPay());
		
		Statistics[] statistics = statisticsRepository.findByByer(dbUser); 
		mav.addObject("statistics", statistics);
		return mav;
	}
	@GetMapping("/{userId}/lotto")
	public ModelAndView showLotto(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		User user = (User) value;
		User dbUser = userRepository.findByUserId(user.getUserId());
		session.setAttribute("sessionedUser", dbUser);
		ModelAndView mav = new ModelAndView("pages/lotto");
		mav.addObject("pay", dbUser.getMoney());
		mav.addObject("money", dbUser.getPay());
		return mav;
	}

	@PostMapping("")
	public String create(User user) {
		userRepository.save(user);
		return  "redirect:/";
	}

	@GetMapping("/{userId}/form")
	public ModelAndView updateForm(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		if (value == null) {
			return new ModelAndView("redirect:/");
		}
		User user = (User) value;
		ModelAndView mav = new ModelAndView("user/updateForm");
		user = userRepository.findByUserId(user.getUserId());
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/{userId}")
	public String update(String userId, User user, String password_new) {
		User dbUser = userRepository.findByUserId(userId);
		if(dbUser.update(user,password_new)) { 
			userRepository.save(dbUser);
			return "redirect:/users/{userId}";	
		}
		return "redirect:/users/{userId}/form";
	}

	@PostMapping("/loginCheck")
	public String login(String userId, String password, HttpSession session)   {
		User dbUser = userRepository.findByUserId(userId);
		if (dbUser == null) {
			return "user/login_failed";
		}
		if (!dbUser.matchPassword(password)) {
			return "user/login_failed";	
		}
		session.setAttribute("sessionedUser", dbUser);
		return "redirect:/users/"+userId;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return new ModelAndView("redirect:/");
	}
}
