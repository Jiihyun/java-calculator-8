package calculator;

import java.util.regex.Pattern;

public class ExpressionParser {

    private final DelimiterExtractor delimiterExtractor;

    public ExpressionParser(DelimiterExtractor delimiterExtractor) {
        this.delimiterExtractor = delimiterExtractor;
    }

    public ExpressionInfo parse(String expression) {
        if (Delimiter.isCustom(expression)) {
            String delimiter = delimiterExtractor.extract(expression);
            String delimiterPattern = Delimiter.COMMA + "|" + Delimiter.COLON + "|" + Pattern.quote(delimiter);
            String parsedExpression = parseCustomFormat(expression);
            return new ExpressionInfo(parsedExpression, delimiterPattern);
        }
        String delimiterPattern = Delimiter.COMMA + "|" + Delimiter.COLON;
        return new ExpressionInfo(expression, delimiterPattern);
    }

    private String parseCustomFormat(String expression) {
        int indexOfSuffix = Delimiter.CUSTOM_SUFFIX.getIndexIn(expression);
        return expression.substring(indexOfSuffix + Delimiter.CUSTOM_SUFFIX.getLength());
    }
}
