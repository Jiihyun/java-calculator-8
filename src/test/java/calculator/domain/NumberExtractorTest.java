package calculator.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberExtractorTest {

    private NumberExtractor numberExtractor;

    @BeforeEach
    void setUp() {
        numberExtractor = new NumberExtractor();
    }

    @ParameterizedTest
    @MethodSource("provideExpressionAndDelimiterPatternAndNumbers")
    void 표현식으로부터_숫자를_추출할_수_있다(String expression, String delimiterPattern, Numbers expectedNumbers) {
        // when
        Numbers numbers = numberExtractor.createNumbers(expression, delimiterPattern);
        // then
        assertThat(numbers).isEqualTo(expectedNumbers);
    }

    private static Stream<Arguments> provideExpressionAndDelimiterPatternAndNumbers() {
        return Stream.of(
                Arguments.of("1,2:3:4,5,6:7,8,9:10", ",|:", new Numbers(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L))),
                Arguments.of("40$50$60", ",|:|\\Q$\\E", new Numbers(List.of(40L, 50L, 60L))),
                Arguments.of("1.2,2:3", ",|:|\\Q.\\E", new Numbers(List.of(1L, 2L, 2L, 3L)))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"100000000000000000000000000,2:3", "/a\\n1a2a3"})
    void 표현식에_사용할_수_없는_문자_또는_숫자_포함시_예외가_발생한다(String expression) {
        //given
        String delimiterPattern = ",|:";
        // when & then
        assertThatThrownBy(() -> numberExtractor.createNumbers(expression, delimiterPattern))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 사용할 수 없는 문자 또는 숫자가 포함되어 있습니다.");
    }
}
