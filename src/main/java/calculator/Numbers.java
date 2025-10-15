package calculator;

import java.util.List;

public class Numbers {

    private static final int POSITIVE_NUMBER_THRESHOLD = 1;

    private final List<Long> numbers;

    public Numbers(List<Long> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Long> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 숫자가 존재하지 않습니다.");
        }
    }

    private void validateRange(List<Long> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException("[ERROR] 숫자는 양수만 가능합니다.");
        }
    }

    private boolean isOutOfRange(List<Long> numbers) {
        return numbers.stream()
                .anyMatch(this::hasNonPositiveNumber);
    }

    private boolean hasNonPositiveNumber(Long number) {
        return number < POSITIVE_NUMBER_THRESHOLD;
    }

    public long sum() {
        try {
            return numbers.stream()
                    .reduce(0L, Math::addExact);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("[ERROR] 덧셈 가능한 범위를 초과하였습니다");
        }
    }
}
