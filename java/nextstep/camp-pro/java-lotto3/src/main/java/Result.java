import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<Score, Integer> scores = new EnumMap<>(Score.class);

    public Result() {
        scores.put(Score.FIFTH, 0);
        scores.put(Score.FOURTH, 0);
        scores.put(Score.THIRD, 0);
        scores.put(Score.SECOND, 0);
        scores.put(Score.FIRST, 0);
    }

    public void input(List<Score> values) {
        for (Score value : values) {
            scores.put(value, scores.get(value) + 1);
        }
    }

    public Integer get(Score score) {
        return this.scores.get(score);
    }

    public double calculate(int count) {
        double result = calculateWinning(count);
        return format(result);
    }

    private double calculateWinning(int count) {
        return BigDecimal.valueOf(
            sum() / (LottoGame.LOTTO_PRICE * count)
        ).doubleValue();
    }

    private double sum() {
        double result = 0.00;
        for (Map.Entry<Score, Integer> entry : scores.entrySet()) {
            result += Score.calculate(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private double format(double result) {
        return Double.parseDouble(String.format("%.2f", result));
    }


}
