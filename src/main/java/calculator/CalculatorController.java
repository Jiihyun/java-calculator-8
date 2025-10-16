package calculator;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Calculator calculator;

    public CalculatorController(InputView inputView, OutputView outputView, Calculator calculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculator = calculator;
    }

    public void calculate() {
        String input = inputView.readInput();
        long sum = calculator.sum(input);
        outputView.showResult(sum);
    }
}
