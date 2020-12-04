import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StringCalculatorTest {

    @DisplayName("입력 문자열의 숫자와 사칙 연산 사이에는 빈 공백 문자열이 있다.")
    @ValueSource(strings = {"1+1", "1+ 1", "1 +1", "1 + 1/2", "1 + 2 * 3 - 2 / 5+1"})
    @ParameterizedTest
    void space(String input) {
        assertThatThrownBy(() ->
            new Calculator(input)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("입력 문자열의 숫자와 사칙 연산 사이에는 빈 공백 문자열이 있습니다.");

    }

    @DisplayName("더하기를 한다")
    @MethodSource("providePlus")
    @ParameterizedTest
    void plus(String input, int expected) {
        Calculator calculator = new Calculator(input);
        int actual = calculator.run();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePlus() {
        return Stream.of(
            Arguments.of("1 + 2", 3),
            Arguments.of("2 + 3 + 2", 7),
            Arguments.of("4 + 3 + 8 + 8 + 0", 23),
            Arguments.of("9 + 4 + 9 + 1", 23)
        );
    }

    @DisplayName("뺼셈을 한다")
    @MethodSource("provideMinus")
    @ParameterizedTest
    void minus(String input, int expected) {
        Calculator calculator = new Calculator(input);
        int actual = calculator.run();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMinus() {
        return Stream.of(
            Arguments.of("1 - 2", -1),
            Arguments.of("3 - 2 - 1", 0),
            Arguments.of("4 + 3 - 8 + 8 - 2", 5),
            Arguments.of("9 - 4 - 9 + 1", -3)
        );
    }

    @DisplayName("나눗셈의 경우 결과 값을 정수로 떨어지는 값으로 한정한다.")
    @MethodSource("provideRound")
    @ParameterizedTest
    void round(String input, int expected) {
        Calculator calculator = new Calculator(input);
        int actual = calculator.run();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideRound() {
        return Stream.of(
            Arguments.of("1 / 2", 0),
            Arguments.of("2 / 3", 0),
            Arguments.of("4 / 3", 1),
            Arguments.of("9 / 4", 2)
        );
    }

    @DisplayName("사칙연산을 계산한다.")
    @Test
    void calculate() {
        Calculator calculator = new Calculator("2 + 3 * 4 / 2");
        int actual = calculator.run();

        assertThat(actual).isEqualTo(10);
    }
}
