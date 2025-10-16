package calculator;

public class Calculator {

    private final NumberExtractor numberExtractor;

    public Calculator(NumberExtractor numberExtractor) {
        this.numberExtractor = numberExtractor;
    }

    public long sum(String value) {
        Numbers numbers = numberExtractor.extract(value);
        return numbers.sum();
    }
}
