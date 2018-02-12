package bbd.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.dao.AcademyUserDao;
import bbd.dao.UserDao;
import bbd.model.AcademyUser;
import bbd.model.User;


@Controller
@RequestMapping("/academy")
public class AcademyUserController {
	@GetMapping("/{academyUser.emailId}/form")
	public ModelAndView showModifyForm(HttpSession session) {
		Object value = session.getAttribute("academyUser");
		AcademyUser user = (AcademyUser) value;
		
		AcademyUserDao userDao = new AcademyUserDao();
		AcademyUser dbUser = userDao.findUserByUserId(user.getEmailId());

		session.setAttribute("academyUser", dbUser);
		ModelAndView mav = new ModelAndView("/pages/academyModifyForm");
		
		return mav;
	}
	
	@PostMapping("/modifyInformation")
	public ModelAndView modifyParentInformation(HttpSession session, String password1, String password2, String academyName) {

		if (!isValidPassword(password1, password2)) {
			ModelAndView mav = new ModelAndView("/pages/academyModifyForm");
			return mav;
		}
		Object value = session.getAttribute("academyUser");
		AcademyUser user = (AcademyUser) value;
		
		ModelAndView mav = new ModelAndView("/pages/index");
		AcademyUserDao userDao = new AcademyUserDao();
		AcademyUser modifyUser = new AcademyUser(user.getEmailId(), password1, academyName);
		userDao.updateUser(modifyUser);
		
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


	private boolean isValidPassword(String password1, String password2) {
		return true;
	}
}
