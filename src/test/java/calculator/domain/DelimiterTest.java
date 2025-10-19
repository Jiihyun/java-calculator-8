package calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DelimiterTest {

    @ParameterizedTest
    @CsvSource({
            "//$\\n1$2$3, TRUE",
            "/$\\n1$2$3, FALSE",
            "//$\1$2$3, FALSE",
            "1:2:3, FALSE"
    })
    void 커스텀구분자_여부를_파악할_수_있다(String expression, boolean expectedResult) {
        // when
        boolean result = Delimiter.isCustom(expression);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void 표현식_내_구분자의_인덱스를_구할_수_있다() {
        //given
        String expression = "//@\\n1@2";
        // when
        int prefixIndex = Delimiter.CUSTOM_PREFIX.getIndexIn(expression);
        int suffixIndex = Delimiter.CUSTOM_SUFFIX.getIndexIn(expression);
        // then
        assertAll(
                () -> assertThat(prefixIndex).isZero(),
                () -> assertThat(suffixIndex).isEqualTo(3)
        );
    }
}
