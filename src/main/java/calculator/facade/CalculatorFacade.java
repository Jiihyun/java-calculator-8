package calculator.facade;

import calculator.domain.DelimiterExtractor;
import calculator.domain.ExpressionParser;
import calculator.domain.NumberExtractor;
import calculator.domain.Numbers;

public class CalculatorFacade {

    private final DelimiterExtractor delimiterExtractor;
    private final ExpressionParser expressionParser;
    private final NumberExtractor numberExtractor;

    public CalculatorFacade(DelimiterExtractor delimiterExtractor, ExpressionParser expressionParser,
                            NumberExtractor numberExtractor) {
        this.delimiterExtractor = delimiterExtractor;
        this.expressionParser = expressionParser;
        this.numberExtractor = numberExtractor;
    }

    public long sum(String value) {
        String delimiter = delimiterExtractor.extract(value);
        String expression = expressionParser.parse(value);
        Numbers numbers = numberExtractor.createNumbers(expression, delimiter);
        return numbers.sum();
    }
}
