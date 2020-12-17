import java.util.List;

import static java.util.stream.Collectors.toList;

public class Winning {
    private final Lotto lotto;
    private final LottoNumber bonus;

    public Winning(String input, int bonus) {
        this.bonus = new LottoNumber(bonus);
        this.lotto = LottoAssembler.toLotto(SplitUtils.split(input));
    }

    public List<Score> compare(List<Lotto> lottos) {
        return lottos.stream()
            .map(v -> Score.find(v.compare(lotto), v.contain(bonus)))
            .collect(toList());
    }
}
