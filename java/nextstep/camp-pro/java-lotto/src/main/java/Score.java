import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Score {
    FIRST(6, false, 2000000000.0),
    SECOND(5, true, 30000000.0),
    THIRD(5, false, 1500000.0),
    FOURTH(4, false, 50000.0),
    FIFTH(3, false, 5000.0);


    private static final Map<Integer, Score> SCORE_MAP =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(Score::getMatch, Function.identity())));

    private final int match;
    private final boolean bonus;
    private final double winning;

    Score(int match, boolean bonus, double winning) {
        this.match = match;
        this.bonus = bonus;
        this.winning = winning;
    }

    public static double calculate(Score score, int count) {
        return score.winning * count;
    }

    public static Score find(int match, boolean bonus) {
        if (match == SECOND.match && bonus) {
            return SECOND;
        }

        if(SCORE_MAP.containsKey(match)) {
            return SCORE_MAP.get(match);
        }

        throw new IllegalArgumentException("당첨이 아닙니다.");
    }

    private int getMatch() {
        return match;
    }
}
