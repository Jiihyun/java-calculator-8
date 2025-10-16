package calculator;

public class DelimiterExtractor {

    public String extract(String value) {
        int startIndex = Delimiter.CUSTOM_PREFIX.getIndexIn(value);
        int endIndex = Delimiter.CUSTOM_SUFFIX.getIndexIn(value);
        String delimiter = value.substring(startIndex, endIndex);
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
