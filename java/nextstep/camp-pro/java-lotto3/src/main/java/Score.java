import java.util.Arrays;

public enum Score {
    FIRST(6, false, 2000000000.0),
    SECOND(5, true, 30000000.0),
    THIRD(5, false, 1500000.0),
    FOURTH(4, false, 50000.0),
    FIFTH(3, false, 5000.0);


    private final int match;
    private final boolean bonus;
    private final double winning;

    Score(int match, boolean bonus, double winning) {
        this.match = match;
        this.bonus = bonus;
        this.winning = winning;
    }

    public static double calculate(Score score, int count) {
        return find(score).winning * count;
    }

    public static Score find(int match, boolean bonus) {
        if (bonus && match == SECOND.match) {
            return SECOND;
        }
        return Arrays.stream(Score.values())
            .filter(v -> v.match == match)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("당첨이 아닙니다."))
            ;
    }

    private static Score find(Score score) {
        return Arrays.stream(Score.values())
            .filter(v -> v.equals(score))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("당첨이 아닙니다."))
            ;
    }
}
