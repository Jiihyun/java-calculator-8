package calculator.domain;

public class DelimiterExtractor {

    public String extract(String expression) {
        int endIndex = Delimiter.CUSTOM_SUFFIX.getIndexIn(expression);
        String delimiter = expression.substring(Delimiter.CUSTOM_PREFIX.getLength(), endIndex);
        validate(delimiter);
        return delimiter;
    }

    private void validate(String delimiter) {
        if (delimiter.strip().isBlank()) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 빈 문자열일 수 없습니다.");
        }
        if (hasNumber(delimiter)) {
            throw new IllegalArgumentException("[ERROR] 커스텀 구분자는 숫자를 포함할 수 없습니다.");
        }
    }

    private boolean hasNumber(String delimiter) {
        return delimiter.chars()
                .anyMatch(Character::isDigit);
    }
}
