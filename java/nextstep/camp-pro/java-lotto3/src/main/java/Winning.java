import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Winning {
    private final Lotto lotto;
    private final LottoNumber bonus;

    public Winning(String input, int bonus) {
        this.bonus = new LottoNumber(bonus);
        this.lotto = toLotto(toInteger(SplitUtils.split(input)));
    }

    private Lotto toLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
            .map(LottoNumber::new)
            .collect(toList()));
    }

    private static List<Integer> toInteger(String[] split) {
        return Arrays.stream(split)
            .map(String::trim)
            .map(Integer::valueOf)
            .collect(toList());
    }

    public List<Score> compare(List<Lotto> lottos) {
        return lottos.stream()
            .map(v -> Score.find(v.compare(lotto), v.contain(bonus)))
            .collect(toList());
    }
}
