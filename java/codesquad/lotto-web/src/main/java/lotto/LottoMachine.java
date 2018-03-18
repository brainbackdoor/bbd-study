package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
	String numberOfWinning;
	public LottoMachine(String numberOfWinning){
		this.numberOfWinning = numberOfWinning;
	}
	
	public Lotto createNumberOfWinning() {
		if (numberOfWinning.equals("lottery")) {
			return setNumberOfWinning();
		}
		return setNumberOfWinning(numberOfWinning);
	}

	Lotto setNumberOfWinning() {
		List lottoNumbers = new ArrayList();
		Lotto lotto = new Lotto(lottoNumbers);
		lotto.autoCreateLotto();
		return lotto;
	}

	Lotto setNumberOfWinning(String NumberOfWinning) {
		List lottoNumbers = new ArrayList();
		String[] NumbersOfWinning = NumberOfWinning.split(", ");
		for (String number : NumbersOfWinning) {
			lottoNumbers.add((Integer.parseInt(number)));
		}
		Lotto winningLotto = new Lotto(lottoNumbers);
		return winningLotto;
	}

}
