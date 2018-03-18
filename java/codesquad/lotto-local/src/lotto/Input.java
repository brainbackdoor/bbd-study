package lotto;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	
	public static Scanner scannerString = new Scanner(System.in);
	public static Scanner scannerInt = new Scanner(System.in);

	public static int getMeansCommand() {
		ShowLotto.promptMeans();

		return scannerInt.nextInt();
	}

	public static int getBuyingMoney() {
		ShowLotto.promptPay();
		return scannerInt.nextInt();
	}

	public static String getWinningCommand() {
		ShowLotto.promptWinning();
		return scannerString.nextLine();
	}

	public String[] getManualLottoTickets(int countOfLotto) {
		String[] buyerManualLottoTickets = new String[countOfLotto];
		for (int i = 0; i < countOfLotto; i++) {
			buyerManualLottoTickets[i] = scannerString.nextLine();
		}
		return buyerManualLottoTickets;
	}
}
