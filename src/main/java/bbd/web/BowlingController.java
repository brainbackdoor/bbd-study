package bbd.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bowling.Board;
import bowling.Game;
import bowling.Player;
import bowling.Result;
import bowling.Score;
import bowling.Turn;

@Controller
public class BowlingController {
	Game game;
	List<Player> players = new ArrayList<>();
	
	@PostMapping("/input/number")
	public ModelAndView number(int inputNumber) {
		ModelAndView mav = new ModelAndView("/bowling/init");
		mav.addObject("inputNumber", inputNumber);
		return mav;
	}
	
	@PostMapping("/input/init")
	public ModelAndView init(String inputName, String inputNumber) {
		ModelAndView mav = new ModelAndView("/bowling/init");
		
		game = new Game();
		game.init(Integer.parseInt(inputNumber),inputName);
		players = game.getPlayers();
		Score score = players.get(0).getScore();
		mav.addObject("inputNumber", inputNumber);
		mav.addObject("inputName", inputName);
		mav.addObject("frame", 0);
		return mav;
	}
	
	@PostMapping("/bowling/game")
	public ModelAndView game(int frame, String inputName, String inputNumber) {
		List<Player> playersFrame = new ArrayList<>();
		List<Map<String,String>> playersBoard = new ArrayList<>();
		ModelAndView mav = new ModelAndView("/bowling/init");
		for (Player player : players) {
			playersFrame.add(game.playGame(player, new Turn(frame * 2)));
			playersBoard.add(new Board().showBoard(player));
		}
		mav.addObject("inputNumber", inputNumber);
		mav.addObject("inputName", inputName);
		mav.addObject("playersFrame", playersFrame);
		mav.addObject("playersBoard", playersBoard);
		mav.addObject("frame", frame+1);
		if(frame+1 == 10) {
			mav.addObject("finish", 1);
		} else if(frame != 0) {
			mav.addObject("game", 1);
		}
		return mav;
	}
	

	
	

}
