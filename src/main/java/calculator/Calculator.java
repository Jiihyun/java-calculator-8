package calculator;

public class Calculator {

    private final NumberExtractor numberExtractor;
    private final ExpressionParser expressionParser;

    public Calculator(NumberExtractor numberExtractor, ExpressionParser expressionParser) {
        this.numberExtractor = numberExtractor;
        this.expressionParser = expressionParser;
    }

    public long sum(String value) {
        ExpressionInfo expressionInfo = expressionParser.parse(value);
        Numbers numbers = numberExtractor.createNumbers(expressionInfo.expression(), expressionInfo.delimiterPattern());
        return numbers.sum();
    }
}
