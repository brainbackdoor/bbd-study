import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;

class LottoGameTest {

    @Nested
    class 로또_구입_금액을_입력하면_구입_금액에_해당하는_로또를_발급한다 {

        @CsvSource(value = {"1000,1", "3300,3", "3900,3"})
        @ParameterizedTest
        void 로또_1장의_가격은_1000원이다(int input, int expected) {
            LottoGame game = new LottoGame(input);
            int actual = game.count();

            assertThat(actual).isEqualTo(expected);
        }

        @ValueSource(ints = {-1000, 0, 950})
        @ParameterizedTest
        void 로또_구입_금액이_1000원_미만일_경우_RuntimeException을_발생시킨다(int input) {
            assertThatThrownBy(() ->
                new LottoGame(input)
            ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구입 금액이 1000원 미만입니다.");
        }
    }

    @MethodSource("provideManual")
    @ParameterizedTest
    void 수동으로_구매할_로또를_제외하고_자동으로_로또를_구입한다(List<String> input, int money) {
        LottoGame game = new LottoGame(money, LottoAssembler.toLottos(input));
        int expected = money / LottoGame.LOTTO_PRICE;

        assertThat(game.count()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideManual() {
        return Stream.of(
            Arguments.of(List.of("1, 2, 3, 4, 5, 6"), 4000),
            Arguments.of(List.of("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 7"), 2000),
            Arguments.of(List.of("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 7", "1, 15, 36, 27, 46, 33"), 5000)
        );
    }

    @MethodSource("provideCompare")
    @ParameterizedTest
    void 당첨번호를_비교해본다(int[] first, String second, int bonus, Score score, int count) {
        try (final MockedStatic mock = mockStatic(LottoNumbers.class)) {
            mock.when(() -> LottoNumbers.select()).thenReturn(generateLottoNumberOf(first));
            LottoGame game = new LottoGame(1000);
            Result actual = game.compare(second, bonus);
            assertThat(actual.get(score)).isEqualTo(count);
        }
    }

    private static Stream<Arguments> provideCompare() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, "1, 2, 3, 4, 5, 6", 7, Score.FIRST, 1),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, "1, 2, 3, 4, 5, 6", 7, Score.SECOND, 0),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 7}, "1, 2, 3, 4, 5, 6", 7, Score.FIRST, 0),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 7}, "1, 2, 3, 4, 5, 6", 7, Score.SECOND, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideProfit")
    void 수익률을_계산한다(List<Score> data, int count, double expected) {
        Result result = new Result();
        result.input(data);
        double actual = result.calculate(count);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideProfit() {
        return Stream.of(
            Arguments.of(List.of(Score.FIFTH), 14, 0.36),
            Arguments.of(List.of(Score.FIRST), 100, 20000.00),
            Arguments.of(List.of(Score.FIRST, Score.THIRD), 100, 20015.00),
            Arguments.of(List.of(Score.SECOND), 1000, 30.0),
            Arguments.of(List.of(Score.FIRST, Score.THIRD, Score.FOURTH, Score.FIFTH), 100, 20015.55)
        );
    }

    private List<LottoNumber> generateLottoNumberOf(int[] numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::new)
            .collect(toList());
    }
}
