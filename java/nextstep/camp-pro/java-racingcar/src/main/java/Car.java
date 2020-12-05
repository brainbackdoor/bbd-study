public class Car {
    public static final int CRITERIA_FOR_MOVING = 4;
    int position = 0;

    public Car() {
    }

    public int racing(int score) {
        if(isNotZeroToTen(score)) {
            throw new IllegalArgumentException("전진하는 조건은 0에서 9 사이에서 값이어야 합니다.");
        }

        if (score >= CRITERIA_FOR_MOVING) {
            position++;
        }
        return position;
    }

    private boolean isNotZeroToTen(int score) {
        return score < 0 || score > 9;
    }
}
