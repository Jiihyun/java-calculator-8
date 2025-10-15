package calculator;

public class DelimiterExtractor {

    private static final String CUSTOM_DELIMITER_END_CONDITION = "\\n";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;

    public String extract(String value) {
        int endIndex = value.indexOf(CUSTOM_DELIMITER_END_CONDITION);
        String delimiter = value.substring(CUSTOM_DELIMITER_START_INDEX, endIndex);
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
