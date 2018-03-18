package lotto;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

	private ArrayList<Lotto> lottoTickets;

	public Buyer(ArrayList<Lotto> lottoTickets) {
		this.lottoTickets = lottoTickets;
	}

	public ArrayList<Lotto> buyingLottoTickets(int countLotto) {
		autoCreateMultipleLotto(countLotto);
		return lottoTickets;
	}

	public ArrayList<Lotto> buyingLottoTickets(int countLotto, String[] buyerManualLottoTickets) {
		manualCreateMultipleLotto(countLotto, buyerManualLottoTickets);
		return lottoTickets;
	}

	void autoCreateMultipleLotto(int countOfLotto) {
		for (int i = 0; i < countOfLotto; i++) {
			List lottoNumbers = new ArrayList();
			Lotto lotto = new Lotto(lottoNumbers);
			lotto.autoCreateLotto();
			lottoTickets.add(lotto);
		}
	}

	void manualCreateMultipleLotto(int countOfLotto, String[] buyerManualLottoTickets) {
		for (int i = 0; i < countOfLotto; i++) {
			List lottoNumbers = new ArrayList();
			Lotto lotto = new Lotto(lottoNumbers);
			lotto.manualCreateLotto(buyerManualLottoTickets[i]);
			lottoTickets.add(lotto);
		}
	}

	
}
