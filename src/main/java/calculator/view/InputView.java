package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private static final String DEFAULT_VALUE = "0";

    public String readInput() {
        System.out.println(INPUT_MESSAGE);

        String input = readLine();
        if (input.isBlank()) {
            return DEFAULT_VALUE;
        }
        return input;
    }

    private String readLine() {
        return Console.readLine().strip();
    }
}
