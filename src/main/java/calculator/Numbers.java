package calculator;

import java.util.List;

public class Numbers {

    private static final int POSITIVE_NUMBER_THRESHOLD = 1;

    private final List<Integer> numbers;

    public Numbers(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 숫자가 존재하지 않습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException("[ERROR] 숫자는 양수만 가능합니다.");
        }
    }

    private boolean isOutOfRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(this::hasNonPositiveNumber);
    }

    private boolean hasNonPositiveNumber(Integer number) {
        return number < POSITIVE_NUMBER_THRESHOLD;
    }
}
