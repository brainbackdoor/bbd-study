package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoMain {
	public static int automatic = 1;

	public static void main(String[] args) {
		Input input = new Input();
		// 유저가 돈을 지불
		Money money = new Money(input.getBuyingMoney());

		// 로또 발행 (1.자동 / 2.수동)
		ArrayList<Lotto> buyerLottoTickets = new ArrayList<Lotto>();
		int means = input.getMeansCommand();
		buyerLottoTickets = paymentLotto(money, buyerLottoTickets, means);
		ShowLotto.printLottoNumbers(buyerLottoTickets);

		// 당첨번호 생성
		LottoMachine machine = new LottoMachine(input.getWinningCommand());
		Lotto WinningLotto = machine.createNumberOfWinning();
		ShowLotto.printLottoNumbers(WinningLotto, WinningLotto.bonusLottoNumber);

		// 결과 도출
		Result result = new Result();
		result.reportLotto(WinningLotto, buyerLottoTickets);
		ShowLotto.printLottoReport(money, result);

	}

	private static ArrayList<Lotto> paymentLotto(Money money, ArrayList<Lotto> buyerLottoTickets, int means) {
		Input input = new Input();
		if (means == automatic) {
			Buyer buyer = new Buyer(buyerLottoTickets);
			return buyer.buyingLottoTickets(money.paymentOfLotto(means));
		}
		Buyer buyer = new Buyer(buyerLottoTickets);
		String[] buyerManualLottoTickets = input.getManualLottoTickets(money.paymentOfLotto(means));
		buyerLottoTickets = buyer.buyingLottoTickets(money.paymentOfLotto(means), buyerManualLottoTickets);
		return buyerLottoTickets;
	}

}