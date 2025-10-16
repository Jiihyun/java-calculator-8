package calculator;

import java.util.regex.Pattern;

public class ExpressionParser {

    private final DelimiterExtractor delimiterExtractor;

    public ExpressionParser(DelimiterExtractor delimiterExtractor) {
        this.delimiterExtractor = delimiterExtractor;
    }

    public ExpressionInfo parse(String value) {
        if (Delimiter.isCustom(value)) {
            String delimiter = delimiterExtractor.extract(value);
            String delimiterPattern = Delimiter.COMMA + "|" + Delimiter.COLON + "|" + Pattern.quote(delimiter);
            String parsedValue = parseCustomFormat(value);
            return new ExpressionInfo(parsedValue, delimiterPattern);
        }
        String delimiterPattern = Delimiter.COMMA + "|" + Delimiter.COLON;
        return new ExpressionInfo(value, delimiterPattern);
    }

    private String parseCustomFormat(String value) {
        int indexOfSuffix = Delimiter.CUSTOM_SUFFIX.getIndexIn(value);
        return value.substring(indexOfSuffix + Delimiter.CUSTOM_SUFFIX.getLength());
    }
}
