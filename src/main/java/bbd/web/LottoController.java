package bbd.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bbd.domain.Statistics;
import bbd.domain.StatisticsRepository;
import bbd.domain.User;
import bbd.domain.UserRepository;
import lotto.Money;
import lotto.Result;
import lotto.Lotto;
import lotto.Buyer;
import lotto.LottoMachine;

@Controller
public class LottoController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StatisticsRepository statisticsRepository;
	
	@GetMapping("/encharge/lotto")
	public ModelAndView encharge(int inputMoney, String userId) {
		User dbUser = userRepository.findByUserId(userId);
		dbUser.setMoney(dbUser.getMoney()+inputMoney);
		userRepository.save(dbUser);
		ModelAndView mav = new ModelAndView("redirect:/users/"+userId);
		return mav;
	}
	
	@PostMapping("/input/lotto")
	public ModelAndView input(int inputPay) {
		ModelAndView mav = new ModelAndView("/pages/lotto");
		mav.addObject("inputPay", inputPay);
		return mav;
	}

	@PostMapping("/lotto/game")
	public ModelAndView game(int inputPay, String means, String inputManual, String userId) {
		if (means.equals("unset")) {
			ModelAndView mav = new ModelAndView("/pages/lotto");
			mav.addObject("means", "manual");
			mav.addObject("inputPay", inputPay);
			return mav;
		}
		ModelAndView mav = new ModelAndView("/pages/lotto");
		mav.addObject("inputPay", inputPay);

		// 유저가 돈을 지불
		Money money = new Money(inputPay);
		User dbUser = userRepository.findByUserId(userId);
		if (dbUser.getMoney()<inputPay) {
			return mav;
		}
		
		dbUser.setMoney(dbUser.getMoney()-inputPay);
		dbUser.setPay(dbUser.getPay()+inputPay);
		userRepository.save(dbUser);
		
		// 로또 발행 (1.자동 / 2.수동)
		ArrayList<Lotto> buyerLottoTickets = new ArrayList<Lotto>();
		buyerLottoTickets = paymentLotto(money, buyerLottoTickets, means, inputManual);
		mav.addObject("buyerLottoTickets", buyerLottoTickets);
		// 당첨번호 생성
		LottoMachine machine = new LottoMachine("lottery");
		Lotto WinningLotto = machine.createNumberOfWinning();
		mav.addObject("WinningLotto", WinningLotto);
		mav.addObject("bonusLottoNumber", WinningLotto.bonusLottoNumber);

		// 결과 도출
		Result result = new Result();
		float profit = result.reportLotto(WinningLotto, buyerLottoTickets, money);

		mav.addObject("result", result);
		mav.addObject("report", profit);
		
		Statistics statistics = new Statistics();
		statistics.setPayment(inputPay);
		statistics.setProfit(profit);
		statistics.setByer(dbUser);
		statisticsRepository.save(statistics);
		return mav;
	}

	private static ArrayList<Lotto> paymentLotto(Money money, ArrayList<Lotto> buyerLottoTickets, String means,
			String inputManual) {
		Buyer buyer = new Buyer(buyerLottoTickets);
		if (means.equals("automatic")) {
			return buyer.buyingLottoTickets(money.paymentOfLotto());
		}
		return buyer.buyingLottoTickets(money.paymentOfLotto(), inputManual.split(System.getProperty("line.separator")));
	}
}
