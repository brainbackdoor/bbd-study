package bbd.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.dao.UserDao;
import bbd.model.User;


@Controller
@RequestMapping("/parent")
public class ParentUserController {
	@GetMapping("/{sessionedUser.emailId}/form")
	public ModelAndView showModifyForm(HttpSession session) {
		Object value = session.getAttribute("sessionedUser");
		User user = (User) value;
		
		UserDao userDao = new UserDao();
		User dbUser = userDao.findUserByUserId(user.getEmailId());

		session.setAttribute("sessionedUser", dbUser);
		ModelAndView mav = new ModelAndView("/pages/parentModifyForm");
		
		return mav;
	}
	
	@PostMapping("/modifyInformation")
	public ModelAndView modifyParentInformation(HttpSession session, String password1, String password2, String alias) {

		if (!isValidPassword(password1, password2) || !isValidAlias(alias)) {
			ModelAndView mav = new ModelAndView("/pages/parentModifyForm");
			return mav;
		}
		Object value = session.getAttribute("sessionedUser");
		User user = (User) value;
		
		ModelAndView mav = new ModelAndView("/pages/index");
		UserDao userDao = new UserDao();
		User modifyUser = new User(user.getEmailId(), password1, alias);
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

	private boolean isValidAlias(String alias) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isValidPassword(String password1, String password2) {
		return true;
	}
}
