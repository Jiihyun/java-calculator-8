package calculator.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        String expression = "//[plus]\\n1[plus]2[plus]3";
        String expectDelimiter = "[plus]";
        // when
        String resultDelimiter = delimiterExtractor.extract(expression);
        // then
        assertThat(resultDelimiter).isEqualTo(expectDelimiter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//\\n1;2;3", "// \\n1;2;3", "//    \\n1;2;3"})
    void 커스텀_구분자가_빈문자열이면_예외를_반환한다(String expression) {
        // when & then
        assertThatThrownBy(() -> delimiterExtractor.extract(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 커스텀 구분자는 빈 문자열일 수 없습니다.");
    }

    @Test
    void 커스텀_구분자에_숫자가_포함되면_예외를_반환한다() {
        // given
        String expression = "//^1^\\n1;2;3";
        // when & then
        assertThatThrownBy(() -> delimiterExtractor.extract(expression))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 커스텀 구분자는 숫자를 포함할 수 없습니다.");
    }

    @Test
    void 커스텀_구분자가_없으면_빈_문자열을_반환한다() {
        // given
        String expression = "1000:5000,2,3";
        String expectDelimiter = "";
        // when
        String resultDelimiter = delimiterExtractor.extract(expression);
        // then
        assertThat(resultDelimiter).isEqualTo(expectDelimiter);
    }
}
