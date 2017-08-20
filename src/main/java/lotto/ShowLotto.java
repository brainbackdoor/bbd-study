package lotto;

import java.util.ArrayList;

public class ShowLotto {
	public static int automatic = 1;

	public static void promptPay() {
		System.out.println("구매금액을 입력해주세요");
	}

	public static void promptMeans() {
		System.out.println("로또 발금 수단을 정해주세요 (1.자동 / 2.수동)");
	}

	public static void answerPay(int count, int means) {
		if (means == automatic) {
			System.out.printf("자동 %d 개를 발행을 시작합니다.\n", count);
		} else {
			System.out.printf("수동 %d 개를 발행을 준비중입니다.\n로또 번호를 입력해 주세요\n", count);
		}
	}

	public static void printLottoNumbers(ArrayList<Lotto> lottoTickets) {
		for (Lotto lottoTicket : lottoTickets) {
			System.out.println(lottoTicket.getLottoNumbers());
		}
	}

	public static void printLottoNumbers(Lotto lotto, int bonusLottoNumber) {
		System.out.println(lotto.getLottoNumbers() + " 보너스 숫자 : " + bonusLottoNumber);
	}

	public static void promptWinning() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요");
	}

	public static void printLottoReport(Money money, Result result) {
		System.out.println("3개 일치 (5000원) -  " + result.getCountOfMatch3() + "개");
		System.out.println("4개 일치 (50000원) -  " + result.getCountOfMatch4() + "개");
		System.out.println("5개 일치 (1500000원) -  " + result.getCountOfMatch5() + "개");
		System.out.println("5개 일치, 보너스 볼 일치(30000000원)  -  " + result.getBonusCountOfMatch() + "개");
		System.out.println("6개 일치 (2000000000원) -  " + result.getCountOfMatch6() + "개");
		int prize = ((result.getCountOfMatch3() * 5000 + result.getCountOfMatch4() * 50000
				+ result.getCountOfMatch5() * 1500000 + +result.getBonusCountOfMatch() * 2000000000
				+ result.getCountOfMatch6() * 2000000000));
		float report = ((float) prize / (float) money.getMoney()) * 100;
		System.out.println("총 수익률은 " + report + "%");
	}

}
