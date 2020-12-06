import java.util.List;
import java.util.Objects;

public class RoundResult {
    private List<Integer> positions;

    public RoundResult(List<Integer> positions) {
        this.positions = positions;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundResult result = (RoundResult) o;
        return Objects.equals(positions, result.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
