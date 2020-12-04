import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class Calculator {
    public static final String CRITERIA_FOR_SPLIT = " ";
    public static final String PATTERN_OPERATOR_PREFIX = "[1-9](\\+|-|\\*|/).";
    public static final String PATTERN_OPERATOR_SUFFIX = ".(\\+|-|\\*|/)[1-9]";

    Pattern patternPre = Pattern.compile(PATTERN_OPERATOR_PREFIX);
    Pattern patternEnd = Pattern.compile(PATTERN_OPERATOR_SUFFIX);

    List<Integer> numbers;
    List<String> operators;


    public Calculator(String input) {
        if (isNotContainsEmptyStringOnBothSides(input)) {
            throw new IllegalArgumentException("입력 문자열의 숫자와 사칙 연산 사이에는 빈 공백 문자열이 있습니다.");
        }

        String[] splits = input.split(CRITERIA_FOR_SPLIT);

        numbers = extractNumbers(splits);
        operators = extractOperators(splits);
    }

    public int run() {
        int result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            int number = numbers.get(i + 1);
            String operator = operators.get(i);

            result = Operator.run(result, number, operator);
        }
        return result;
    }

    private List<Integer> extractNumbers(String[] splits) {
        return Arrays.stream(splits)
            .filter(s -> !Operator.isOperator(s))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }

    private List<String> extractOperators(String[] splits) {
        return Arrays.stream(splits)
            .filter(Operator::isOperator)
            .collect(Collectors.toList());
    }

    private boolean isNotContainsEmptyStringOnBothSides(String input) {
        return patternPre.matcher(input).find() || patternEnd.matcher(input).find();
    }

    enum Operator {
        PLUS("+", (first, second) -> first + second),
        MINUS("-", (first, second) -> first - second),
        MULTIPLE("*", (first, second) -> first * second),
        DIVISION("/", (first, second) -> first / second);

        String symbol;
        BiFunction<Integer, Integer, Integer> calculate;

        Operator(String symbol, BinaryOperator<Integer> calculate) {
            this.symbol = symbol;
            this.calculate = calculate;
        }

        static int run(int first, int second, String operator) {
            return (int) find(operator).calculate.apply(first, second);
        }

        private static Operator find(String operator) {
            return Arrays.stream(values())
                .filter(o -> o.symbol.equals(operator))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당하는 연산자가 없습니다."));
        }

        static boolean isOperator(String input) {
            return input.equals(PLUS.symbol)
                || input.equals(MINUS.symbol)
                || input.equals(MULTIPLE.symbol)
                || input.equals(DIVISION.symbol);
        }
    }
}
