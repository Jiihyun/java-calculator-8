package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {

    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_COLON = ":";

    public Numbers extract(String value) {
        String regex = DELIMITER_COMMA + "|" + DELIMITER_COLON;
        return createNumbers(value, regex);
    }

    public Numbers extractWith(String customDelimiter, String value) {
        int endIndex = value.indexOf("\\n");
        String parsedValue = value.substring(endIndex + 2);
        String regex = DELIMITER_COMMA + "|" + DELIMITER_COLON + "|" + Pattern.quote(customDelimiter);
        return createNumbers(parsedValue, regex);
    }

    private static Numbers createNumbers(String value, String regex) {
        try {
            List<Long> numbers = Arrays.stream(value.split(regex))
                    .map(Long::parseLong)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 인정되지 않는 문자 또는 숫자가 포함되어 있습니다.");
        }
    }
}
