package calculator.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {

    public Numbers createNumbers(String expression, String delimiter) {
        String regex = createRegex(delimiter);
        try {
            List<Long> numbers = Arrays.stream(expression.split(regex))
                    .map(Long::parseLong)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 문자 또는 숫자가 포함되어 있습니다.");
        }
    }

    private String createRegex(String delimiter) {
        if (delimiter.isBlank()) {
            return createDefaultDelimiterRegex();
        }
        return createCustomDelimiterRegex(delimiter);
    }

    private String createDefaultDelimiterRegex() {
        return Delimiter.COMMA.getValue() + "|" + Delimiter.COLON.getValue();
    }

    private String createCustomDelimiterRegex(String delimiter) {
        return Delimiter.COMMA.getValue() + "|" + Delimiter.COLON.getValue() + "|" + Pattern.quote(delimiter);
    }
}
