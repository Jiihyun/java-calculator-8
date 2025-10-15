package calculator;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void calculate() {
        String input = inputView.readInput();
        Numbers numbers = extractNumbers(input);
        long sum = numbers.sum();
        outputView.showResult(sum);
    }

    private Numbers extractNumbers(String input) {
        NumberExtractor numberExtractor = new NumberExtractor();
        if (hasCustomDelimiter(input)) {
            DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
            String customDelimiter = delimiterExtractor.extract(input);
            return numberExtractor.extractWith(customDelimiter, input);
        }
        return numberExtractor.extract(input);
    }

    private boolean hasCustomDelimiter(String value) {
        return value.startsWith("//") && value.contains("\\n");
    }
}
