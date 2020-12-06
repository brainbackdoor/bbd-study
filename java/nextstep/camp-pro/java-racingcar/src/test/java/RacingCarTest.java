import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class RacingCarTest {
    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @DisplayName("전진하는 조건은 값이 4이상일 경우이다.")
    @CsvSource(value = {"3,0", "4,1"})
    @ParameterizedTest
    void go(Integer score, Integer expected) {
        int actual = car.racing(score);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("전진하는 조건은 0에서 9 사이에서 값이어야 한다.")
    @ValueSource(ints = {-1, 10})
    @ParameterizedTest
    void valid(int score) {
        assertThatThrownBy(() ->
            car.racing(score)
        ).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("전진하는 조건은 0에서 9 사이에서 값이어야 합니다.")
        ;
    }
}
