package lotto;

import java.util.ArrayList;

import lotto.Lotto;
import lotto.Money;

public class Result {
	private static int ranking2ndLottoCount = 5;

	private int count = 0;

	private int prize = 0;

	private int bonusCountOfMatch = 0;

	private int countOfMatch3 = 0;

	private int countOfMatch4 = 0;

	private int countOfMatch5 = 0;

	private int countOfMatch6 = 0;

	public float reportLotto(Lotto numberOfPrize, ArrayList<Lotto> buyerLottoTickets, Money money) {
		matchWinningWithBuyerLottoTickets(numberOfPrize, buyerLottoTickets);
		return ((float) prize / (float) money.getMoney()) * 100;
	}

	/**
	 * 유저 로또 당첨 여부 확인
	 */
	void matchWinningWithBuyerLottoTickets(Lotto numberOfWinning, ArrayList<Lotto> buyerLottoTicekts) {
		for (Lotto buyerLottoTicket : buyerLottoTicekts) {
			matchWinningWithBuyerLottoTicket(numberOfWinning, buyerLottoTicket);
			confirmBuyerLottoRanking();
			this.count = 0;
		}
		this.prize = (countOfMatch3 * 5000 + countOfMatch4 * 50000 + countOfMatch5 * 1500000
				+ bonusCountOfMatch * 2000000000 + countOfMatch6 * 2000000000);
	}

	private void matchWinningWithBuyerLottoTicket(Lotto numberOfWinning, Lotto buyerLottoTicket) {
		for (Integer lottoNumber : buyerLottoTicket.getLottoNumbers()) {
			if (numberOfWinning.getLottoNumbers().contains(lottoNumber)) {
				this.count++;
			}
		}
		matchBonusWith2ndBuyerLottoTicket(numberOfWinning, buyerLottoTicket);
	}

	/**
	 * 유저의 로또 당첨된 번호 개수 확인
	 */
	void confirmBuyerLottoRanking() {
		switch (count) {
		case 3:
			this.countOfMatch3++;
			break;
		case 4:
			this.countOfMatch4++;
			break;
		case 5:
			this.countOfMatch5++;
			break;
		case 6:
			this.countOfMatch6++;
			break;
		}
	}

	/**
	 * 5개 맞춘 유저의 틀린 번호가 보너스 번호와 매칭되는지 여부 (2등인지 여부 확인)
	 */
	private void matchBonusWith2ndBuyerLottoTicket(Lotto numberOfWinning, Lotto buyerLottoTicket) {
		if (count == ranking2ndLottoCount) {
			for (Integer lottoNumber : buyerLottoTicket.getLottoNumbers()) {
				if (lottoNumber == numberOfWinning.bonusLottoNumber) {
					this.bonusCountOfMatch++;
				}
			}
		}
	}

	public int getCountOfMatch3() {
		return countOfMatch3;
	}

	public int getCountOfMatch4() {
		return countOfMatch4;
	}

	public int getCountOfMatch5() {
		return countOfMatch5;
	}

	public int getCountOfMatch6() {
		return countOfMatch6;
	}

	public int getBonusCountOfMatch() {
		return bonusCountOfMatch;
	}

	public int getPrize() {
		return prize;
	}

}