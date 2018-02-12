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
public class ParentUserJoinController {
	private static final Logger log = LoggerFactory.getLogger(ParentUserJoinController.class);

	@PostMapping("/parentJoin")
	public String login(String emailId) {
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUserId(emailId);
		if (user == null) {
			MailSender joinAuthMail = new MailSender();
			Token token = new Token(emailId);
			userDao.insertToken(token);

			joinAuthMail.sendMail(emailId, token);
			return "redirect:/pages/parentJoinAuthWaitForm";
		}
		return "redirect:/pages/parentJoinForm";
	}

	@GetMapping("/parentJoinFormAfterAuth")
	public ModelAndView form2nd(int tokenNumber) {

		UserDao userDao = new UserDao();
		Token token = userDao.findByToken(tokenNumber);
		User user = userDao.findUserByUserId(token.getEmailId());

		if (user == null) {
			ModelAndView mav = new ModelAndView("/pages/parentJoinFormAfterAuth");
			mav.addObject("emailId", token.getEmailId());
			mav.addObject("tokenNumber", token.getToken());
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/parentJoinForm");
		return mav;
	}

	@PostMapping("/verifyValidationOfParentJoinForm")
	public ModelAndView verifyValidationOfParentJoinForm(String emailId, String password1, String password2,
			String alias, String tokenNumber) {
		if (!isValidPassword(password1, password2) || !isValidAlias(alias)) {
			ModelAndView mav = new ModelAndView("/pages/parentJoinFormAfterAuth");
			mav.addObject("tokenNumber", tokenNumber);
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/parentJoinForm2nd");
		mav.addObject("emailId", emailId);
		mav.addObject("password", password1);
		mav.addObject("alias", alias);
		mav.addObject("tokenNumber", tokenNumber);
		return mav;

	}

	@PostMapping("/enrollParent")
	public ModelAndView enrollParent(String emailId, String password, String alias, String tokenNumber) {
		if (!isAuthPhone() || !isCheckAgreement()) {
			ModelAndView mav = new ModelAndView("/pages/parentJoinForm2nd");
			mav.addObject("tokenNumber", tokenNumber);
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/index");
		UserDao userDao = new UserDao();
		User user = new User(emailId, password, alias);
		userDao.insertUser(user);
		userDao.deleteToken(userDao.findByToken(Integer.parseInt(tokenNumber)));
		return mav;

	}

	private boolean isCheckAgreement() {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isAuthPhone() {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidAlias(String alias) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidPassword(String password1, String password2) {
		return true;
	}
}
