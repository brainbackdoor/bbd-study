import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호가 6개가 아닙니다.");
        }
        this.numbers = numbers;
    }

    public int compare(Lotto secondLotto) {
        return secondLotto.contains(numbers);
    }

    private int contains(List<LottoNumber> numbers) {
        return (int) this.numbers.stream().
            filter(numbers::contains)
            .count();
    }

    public boolean contain(LottoNumber number) {
        return this.numbers.contains(number);
    }
}
