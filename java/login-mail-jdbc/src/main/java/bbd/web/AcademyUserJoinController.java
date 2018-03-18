package bbd.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.dao.AcademyUserDao;
import bbd.model.AcademyUser;
import bbd.model.Token;
import bbd.utils.MailSender;

@Controller
@RequestMapping("/users")
public class AcademyUserJoinController {
	private static final Logger log = LoggerFactory.getLogger(ParentUserJoinController.class);

	@PostMapping("/academyJoin")
	public String login(String emailId) {
		AcademyUserDao userDao = new AcademyUserDao();
		AcademyUser user = userDao.findUserByUserId(emailId);
		if (user == null) {
			MailSender joinAuthMail = new MailSender();
			Token token = new Token(emailId);
			userDao.insertToken(token);

			joinAuthMail.academySendMail(emailId, token);
			return "redirect:/pages/academyJoinAuthWaitForm";
		}
		return "redirect:/pages/academyJoinForm";
	}

	@GetMapping("/academyJoinFormAfterAuth")
	public ModelAndView form2nd(int tokenNumber) {

		AcademyUserDao userDao = new AcademyUserDao();
		Token token = userDao.findByToken(tokenNumber);
		AcademyUser user = userDao.findUserByUserId(token.getEmailId());

		if (user == null) {
			ModelAndView mav = new ModelAndView("/pages/academyJoinFormAfterAuth");
			mav.addObject("emailId", token.getEmailId());
			mav.addObject("tokenNumber", token.getToken());
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/academyJoinForm");
		return mav;
	}

	@PostMapping("/verifyValidationOfAcademyJoinForm")
	public ModelAndView verifyValidationOfParentJoinForm(String emailId, String password1, String password2, String tokenNumber) {
		if (!isValidPassword(password1, password2)) {
			ModelAndView mav = new ModelAndView("/pages/academyJoinFormAfterAuth");
			mav.addObject("tokenNumber", tokenNumber);
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/academyJoinForm2nd");
		mav.addObject("emailId", emailId);
		mav.addObject("password", password1);
		mav.addObject("tokenNumber", tokenNumber);
		return mav;

	}

	@PostMapping("/enrollAcademy")
	public ModelAndView enrollParent(String emailId, String password, String academyName, String academyNum, String ownerName, String tokenNumber) {
		if (!isAuthPhone() || !isCheckAgreement()) {
			ModelAndView mav = new ModelAndView("/pages/academyJoinForm2nd");
			mav.addObject("tokenNumber", tokenNumber);
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/index");
		AcademyUserDao userDao = new AcademyUserDao();
		AcademyUser user = new AcademyUser(emailId, password, academyName,academyNum, ownerName );
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
