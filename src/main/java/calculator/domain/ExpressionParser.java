package calculator.domain;

public class ExpressionParser {

    public String parse(String expression) {
        if (Delimiter.isCustom(expression)) {
            return parseCustomFormat(expression);
        }
        return expression;
    }

    private String parseCustomFormat(String expression) {
        int indexOfSuffix = Delimiter.CUSTOM_SUFFIX.getIndexIn(expression);
        return expression.substring(indexOfSuffix + Delimiter.CUSTOM_SUFFIX.getLength());
    }
}
