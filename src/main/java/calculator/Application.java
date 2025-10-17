package calculator;

import calculator.controller.CalculatorController;
import calculator.domain.Calculator;
import calculator.domain.DelimiterExtractor;
import calculator.domain.ExpressionParser;
import calculator.domain.NumberExtractor;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController(
                new InputView(), new OutputView(),
                new Calculator(new NumberExtractor(), new ExpressionParser(new DelimiterExtractor())));
        calculatorController.run();
    }
}
