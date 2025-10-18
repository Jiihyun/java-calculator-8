package calculator.controller;

import calculator.facade.CalculatorFacade;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorFacade calculatorFacade;

    public CalculatorController(InputView inputView, OutputView outputView, CalculatorFacade calculatorFacade) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculatorFacade = calculatorFacade;
    }

    public void run() {
        String expression = inputView.readInput();
        long sum = calculatorFacade.sum(expression);
        outputView.showResult(sum);
    }
}
