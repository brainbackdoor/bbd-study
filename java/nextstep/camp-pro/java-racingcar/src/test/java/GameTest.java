import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;

public class GameTest {
    @DisplayName("자동차 댓수와 이동 횟수는 1 이상의 값이다.")
    @CsvSource(value = {"-1,-1", "0,0", "-1,0", "0,-1"})
    @ParameterizedTest
    void set(Integer count, Integer round) {
        assertThatThrownBy(() ->
            new Game(count, round)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차 댓수와 이동 횟수는 1 이상의 값입니다.");
    }

    @DisplayName("n대의 자동차는 전진할 수 있다.")
    @Test
    void run() {
        RoundResult expected = new RoundResult(List.of(1, 1, 1));

        try (final MockedStatic mock = mockStatic(RandomUtils.class)) {
            mock.when(() -> RandomUtils.run()).thenReturn(4, 5, 6, 7, 8, 9);
            Cars cars = new Cars(List.of(new Car(), new Car(), new Car()));
            RoundResult actual = cars.racing();
            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("n대의 자동차는 멈출 수 있다.")
    @Test
    void stop() {
        RoundResult expected = new RoundResult(List.of(0, 0, 0));

        try (final MockedStatic mock = mockStatic(RandomUtils.class)) {
            mock.when(() -> RandomUtils.run()).thenReturn(1, 2, 3);
            Cars cars = new Cars(List.of(new Car(), new Car(), new Car()));
            RoundResult actual = cars.racing();
            assertThat(actual).isEqualTo(expected);
        }
    }
}
