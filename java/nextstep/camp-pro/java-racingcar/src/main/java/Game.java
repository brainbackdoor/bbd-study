import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int count;
    private final int round;

    public Game(int count, int round) {
        if (count <= 0 || round <= 0) {
            throw new IllegalArgumentException("자동차 댓수와 이동 횟수는 1 이상의 값입니다.");
        }

        this.count = count;
        this.round = round;
    }

    public GameResult run() {
        Cars cars = new Cars(count);
        List<RoundResult> results = new ArrayList();

        for(int i = 0; i < round; i++) {
            results.add(cars.racing());
        }

        return new GameResult(results);
    }
}
