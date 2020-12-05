import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class GameTest {
    //TODO: 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
    //TODO: 사용자는 몇대의 자동차로 몇번의 이동을 할 것인지 입력할 수 있어야 한다.
    //TODO: 자동차의 상태를 화면에 출력한다.

    @DisplayName("자동차 댓수와 이동 횟수는 1 이상의 값이다.")
    @CsvSource(value = {"-1,-1", "0,0", "-1,0", "0,-1"})
    @ParameterizedTest
    void set(Integer count, Integer round) {
        assertThatThrownBy(() ->
            new Game(count, round)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차 댓수와 이동 횟수는 1 이상의 값입니다.");

    }

    @DisplayName("전진하는 조건은 0에서 9 사이에서 random 값을 구한 후 random 값이 4이상일 경우이다.")
    void random() {
        try (final MockedStatic mock = mockStatic(RandomUtils.class)) {
            mock.when(() -> RandomUtils.nextInt(anyInt(), anyInt())).thenReturn(4, 5, 9);

        }
    }
}
