package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterExtractorTest {

    private DelimiterExtractor delimiterExtractor;

    @BeforeEach
    void setUp() {
        delimiterExtractor = new DelimiterExtractor();
    }

    @Test
    void 커스텀_구분자를_추출할_수_있다() {
        // given
        String value = "//;\n1;2;3";
        String expectDelimiter = ";";
        // when
        String resultDelimiter = delimiterExtractor.extract(value);
        // then
        assertThat(resultDelimiter).isEqualTo(expectDelimiter);
    }

    @Test
    void 커스텀_구분자가_빈문자열이면_예외를_반환한다() {
        // given
        String value = "// \n1;2;3";
        // when & then
        assertThatThrownBy(() -> delimiterExtractor.extract(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 커스텀 구분자는 빈 문자열일 수 없습니다.");
    }

    @Test
    void 커스텀_구분자에_숫자가_포함되면_예외를_반환한다() {
        // given
        String value = "//^1^\n1;2;3";
        // when & then
        assertThatThrownBy(() -> delimiterExtractor.extract(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 커스텀 구분자는 숫자를 포함할 수 없습니다.");
    }
}
