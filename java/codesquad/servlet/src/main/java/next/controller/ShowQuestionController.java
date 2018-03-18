package next.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class ShowQuestionController implements Controller  {
	  private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

	  @Override
	  public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	      QuestionDao QuestionDao = new QuestionDao();
	      List<Question> savedQuestion = QuestionDao.findAll();
	      ObjectMapper mapper = new ObjectMapper();
	      resp.setContentType("application/json;charset=UTF-8");
	      PrintWriter out = resp.getWriter();
	      out.print(mapper.writeValueAsString(savedQuestion));
	      return null;
	  }
}
