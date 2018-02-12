package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;
@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String userId = req.getParameter("userId");
    	String password = req.getParameter("password");
    	User authUser = DataBase.findUserById(userId);
    	if(authUser.getPassword().equals(password)) {
    		HttpSession session = req.getSession();
    		session.setAttribute("user", authUser);
    		resp.sendRedirect("/user/list");
    	}
    	else {
    		resp.sendRedirect("/user/login.html");
    	}
    }
}
