import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LottoAssembler {
    public static List<Lotto> toLottos(List<String> inputs) {
        return inputs.stream()
            .map(SplitUtils::split)
            .map(LottoAssembler::toLotto)
            .collect(toList());
    }
    public static Lotto toLotto(String[] split) {
        List<Integer> numbers = toInteger(split);
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
}
