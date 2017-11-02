package bbd.web;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.config.Db;
import bbd.utils.MailSender;

@Controller
@RequestMapping("/users")
public class UserController {

	@PostMapping("/parentJoin")
	public String login(String user_id)   {
		MailSender joinAuthMail = new MailSender();
		Db db = new Db();
		db.connection();
		
		String token = generateToken();
		db.setToken(user_id, token);
		
		joinAuthMail.sendMail(user_id,token);
		return "redirect:/pages/parentJoinAuthWaitForm";
		
	}

	@GetMapping("parentJoinForm2nd")
	public ModelAndView form2nd(String token) {
		ModelAndView mav = new ModelAndView("/pages/parentJoinForm2nd");
		Db db = new Db();
		db.connection();
		String email_id =db.getEmail(token); 
		if(email_id!="") {
			mav.addObject("email_id", email_id);
			System.out.println("Email : "+ email_id);
			System.out.println("Token : "+token);
			db.delToken(token);
		}
		return mav;
	}
	
//	@PostMapping("enroll")
//	public String enroll(String )
	private String generateToken() {
		Random rand = new Random();
		int token = rand.nextInt(1000000000)+100000000;
		if(token > 1000000000) {
			token = token-100000000;
		}
		return Integer.toString(token);
	}
	
	private void connectionDB() {

	}
	
}
