package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {

    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_COLON = ":";

    public Numbers extract(String value) {
        try {
            List<Integer> numbers = Arrays.stream(value.split(DELIMITER_COMMA + "|" + DELIMITER_COLON))
                    .map(Integer::parseInt)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 구분자와 양수외의 문자가 포함되어 있습니다.");
        }
    }

    public Numbers extractWith(String customDelimiter, String value) {
        try {
            int endIndex = value.indexOf("\\n");
            String parsedValue = value.substring(endIndex + 2);
            String regex = DELIMITER_COMMA + "|" + DELIMITER_COLON + "|" + Pattern.quote(customDelimiter);
            String[] split = parsedValue.split(regex);
            List<Integer> numbers = Arrays.stream(split)
                    .map(Integer::parseInt)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 구분자와 양수외의 문자가 포함되어 있습니다.");
        }
    }
}
