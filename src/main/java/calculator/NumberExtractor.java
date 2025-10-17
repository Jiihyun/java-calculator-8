package calculator;

import java.util.Arrays;
import java.util.List;

public class NumberExtractor {

    public Numbers createNumbers(String expression, String delimiterPattern) {
        try {
            List<Long> numbers = Arrays.stream(expression.split(delimiterPattern))
                    .map(Long::parseLong)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 문자 또는 숫자가 포함되어 있습니다.");
        }
    }
}
