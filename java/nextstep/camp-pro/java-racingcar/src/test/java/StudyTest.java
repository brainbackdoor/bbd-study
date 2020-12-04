import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StudyTest {

    @DisplayName("'1,2'를 ,를 기준으로 값을 분리한다")
    @MethodSource("provideSplitSource")
    @ParameterizedTest
    void split(String input, String... expected) {
        String[] actual = input.split(",");
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSplitSource() {
        return Stream.of(
            Arguments.of("1,2", new String[]{"1", "2"}),
            Arguments.of("1,2,3,4,5", new String[]{"1", "2", "3", "4", "5"}),
            Arguments.of("1,2222", new String[]{"1", "2222"}),
            Arguments.of("332#!@$,22,1", new String[]{"332#!@$", "22", "1"})
        );
    }

    @DisplayName("'(1,2)'에서 ()를 제거하고 1,2를 반환한다.")
    @MethodSource("provideRemoveSource")
    @ParameterizedTest
    void remove(String input, String expected) {
        String actual = input.substring(1, input.length() - 1);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideRemoveSource() {
        return Stream.of(
            Arguments.of("(1,2)", "1,2"),
            Arguments.of("(1,2,3,4,5)", "1,2,3,4,5"),
            Arguments.of("(1,2222)", "1,2222"),
            Arguments.of("(332#!@$,22,1)", "332#!@$,22,1")
        );
    }

    @DisplayName("'abc' 값이 주어졌을 때 특정 위치의 문자를 가져온다.")
    @MethodSource("provideSelect")
    @ParameterizedTest
    void select(String input, Integer index, char expected) {
        char actual = input.charAt(index);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSelect() {
        return Stream.of(
            Arguments.of("abc", 0, 'a'),
            Arguments.of("abc", 1, 'b'),
            Arguments.of("abc", 2, 'c')
        );
    }

    @DisplayName("위치 값을 벗어나면 StringIndexOutOfBoundsException 발생한다.")
    @MethodSource("provideException")
    @ParameterizedTest
    void indexOfBoundsException(String input, Integer index) {
        assertThatThrownBy(() ->
            input.charAt(index)
        ).isInstanceOf(IndexOutOfBoundsException.class)
            .hasMessage("String index out of range: " + index);

    }

    private static Stream<Arguments> provideException() {
        return Stream.of(
            Arguments.of("abc", 4),
            Arguments.of("abc", 5)
        );
    }
}
