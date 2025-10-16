package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {

    private final DelimiterExtractor delimiterExtractor;

    public NumberExtractor(DelimiterExtractor delimiterExtractor) {
        this.delimiterExtractor = delimiterExtractor;
    }

    public Numbers extract(String value) {
        if (Delimiter.isCustom(value)) {
            String delimiter = delimiterExtractor.extract(value);
            return extractWith(delimiter, value);
        }
        String regex = Delimiter.COMMA + "|" + Delimiter.COLON;
        return createNumbers(value, regex);
    }

    private Numbers extractWith(String customDelimiter, String value) {
        int indexOfSuffix = Delimiter.CUSTOM_SUFFIX.getIndexIn(value);
        String parsedValue = value.substring(indexOfSuffix + Delimiter.CUSTOM_SUFFIX.getLength());
        String regex = Delimiter.COMMA + "|" + Delimiter.COLON + "|" + Pattern.quote(customDelimiter);
        return createNumbers(parsedValue, regex);
    }

    private Numbers createNumbers(String value, String regex) {
        try {
            List<Long> numbers = Arrays.stream(value.split(regex))
                    .map(Long::parseLong)
                    .toList();
            return new Numbers(numbers);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("[ERROR] 사용할 수 없는 문자 또는 숫자가 포함되어 있습니다.");
        }
    }
}
