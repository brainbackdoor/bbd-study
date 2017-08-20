package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lotto implements Comparator<Integer> {
	static final int lottoSize = 7;
	static final int lottoBonusSize = 6;
	static final int min = 1;
	static final int max = 45;
	public int bonusLottoNumber;
	private List<Integer> lottoNumbers;

	public Lotto(List<Integer> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
	}

	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}

	/**
	 * 자동 로또 번호 생성
	 */
	void autoCreateLotto() {
		createRandNumbers();
		ascendingOrderLottoNumbers();
	}

	private void createRandNumbers() {
		int i = 0;
		while (i < lottoSize) {
			int rand = createRandNumber();
			if (!dupleCheck(rand)) {
				appendLottoNumber(i, rand);
				i++;
			}
		}
	}

	private int createRandNumber() {
		ArrayList<Integer> rand = new ArrayList<>();
		for (int lottoNumberCount = min; lottoNumberCount < max; lottoNumberCount++) {
			rand.add(lottoNumberCount);
		}
		Collections.shuffle(rand);
		return rand.get(0);
	}

	private boolean dupleCheck(int rand) {
		return lottoNumbers.contains(rand);
	}

	private void appendLottoNumber(int lottoNumberCount, int lottoNumber) {
		if (lottoNumberCount != lottoBonusSize) {
			lottoNumbers.add(lottoNumber);
		} else {
			bonusLottoNumber = lottoNumber;
		}
	}

	private void ascendingOrderLottoNumbers() {
		Lotto ascending = new Lotto(lottoNumbers);
		Collections.sort(lottoNumbers, ascending);
	}

	public void manualCreateLotto(String buyerManualLottoTicket) {
		String[] buyerManualLottoNumbers = buyerManualLottoTicket.split(", ");
		for (int j = 0; j < buyerManualLottoNumbers.length; j++) {
			appendLottoNumber(j, Integer.parseInt(buyerManualLottoNumbers[j]));
		}
		ascendingOrderLottoNumbers();
	}

	public List<Integer> getLottoNumbers() {
		return lottoNumbers = Collections.unmodifiableList(lottoNumbers);
	}

}