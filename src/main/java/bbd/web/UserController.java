package bbd.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.dao.UserDao;
import bbd.model.Token;
import bbd.model.User;
import bbd.utils.MailSender;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/parentJoin")
	public String login(String emailId) {
		MailSender joinAuthMail = new MailSender();
		UserDao userDao = new UserDao();
		Token token = new Token(emailId);
		userDao.insertToken(token);

		joinAuthMail.sendMail(emailId, token);
		return "redirect:/pages/parentJoinAuthWaitForm";
	}

	@GetMapping("parentJoinForm2nd")
	public ModelAndView form2nd(int tokenNumber) {
		ModelAndView mav = new ModelAndView("/pages/parentJoinForm2nd");

		UserDao userDao = new UserDao();
		Token token = userDao.findByToken(tokenNumber);
		User user = userDao.findUserByUserId(token.getEmailId());

		if (user != null) {
			mav.addObject("email_id", user.getEmailId());
			log.debug("Email : " + user.getEmailId());
			log.debug("Token : " + token.getToken());
			userDao.deleteToken(token);
		}
		return mav;
	}
}
