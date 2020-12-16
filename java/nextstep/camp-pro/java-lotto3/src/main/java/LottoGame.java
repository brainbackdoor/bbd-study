import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class LottoGame {
    public static final int LOTTO_PRICE = 1_000;
    Result result = new Result();
    List<Lotto> lottos;

    public LottoGame(int money) {
        int lottoCount = bill(money);

        this.lottos = publish(lottoCount);
    }

    private List<Lotto> publish(int lottoCount) {
        return IntStream.rangeClosed(1, lottoCount)
            .mapToObj(v -> new Lotto(LottoNumbers.select()))
            .collect(toList());
    }

    private int bill(int money) {
        if (money >= LOTTO_PRICE) {
            return money / LOTTO_PRICE;
        }
        throw new IllegalArgumentException("로또 구입 금액이 1000원 미만입니다.");
    }

    public int count() {
        return lottos.size();
    }

    public Result compare(String number, int bonus) {
        Winning winning = new Winning(number, bonus);
        result.input(calculate(winning));
        return result;
    }

    private List<Score> calculate(Winning winning) {
        return winning.compare(lottos);
    }

}
