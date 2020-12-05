public class Game {
    int count;
    int round;

    public Game(int count, int round) {
        if (count <= 0 || round <= 0) {
            throw new IllegalArgumentException("자동차 댓수와 이동 횟수는 1 이상의 값입니다.");
        }

        this.count = count;
        this.round = round;
    }
}
