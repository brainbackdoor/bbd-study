import java.util.List;

public class GameResult {
    List<RoundResult> results;
    public GameResult(List<RoundResult> results) {
        this.results = results;
    }

    public List<RoundResult> getResults() {
        return results;
    }
}
