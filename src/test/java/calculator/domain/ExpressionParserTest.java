package calculator.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ExpressionParserTest {

    private ExpressionParser expressionParser;

    @BeforeEach
    void setUp() {
        DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
        expressionParser = new ExpressionParser(delimiterExtractor);
    }

    @Test
    void 커스텀구분자가_없는_표현식을_파싱할_수_있다() {
        // given
        String expression = "1:2,3,4";
        // when
        ExpressionInfo expressionInfo = expressionParser.parse(expression);
        // then
        assertAll(
                () -> assertThat(expressionInfo.expression()).isEqualTo("1:2,3,4"),
                () -> assertThat(expressionInfo.delimiterPattern()).isEqualTo(",|:")
        );
    }

    @Test
    void 커스텀구분자가_있는_표현식을_파싱할_수_있다() {
        // given
        String expression = "//+\\n1+2+3+4";
        // when
        ExpressionInfo expressionInfo = expressionParser.parse(expression);
        // then
        assertAll(
                () -> assertThat(expressionInfo.expression()).isEqualTo("1+2+3+4"),
                () -> assertThat(expressionInfo.delimiterPattern()).isEqualTo(",|:|\\Q+\\E")
        );
    }

}
