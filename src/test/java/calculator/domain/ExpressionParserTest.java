package calculator.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExpressionParserTest {

    private ExpressionParser expressionParser;

    @BeforeEach
    void setUp() {
        expressionParser = new ExpressionParser();
    }

    @Test
    void 커스텀구분자가_없는_표현식을_파싱할_수_있다() {
        // given
        String expression = "1:2,3,4";
        // when
        String parsedExpression = expressionParser.parse(expression);
        // then
        assertThat(parsedExpression).isEqualTo(expression);
    }

    @Test
    void 커스텀구분자가_있는_표현식을_파싱할_수_있다() {
        // given
        String expression = "//+\\n1+2+3+4";
        String expectedExpression = "1+2+3+4";
        // when
        String parsedExpression = expressionParser.parse(expression);
        // then
        assertThat(parsedExpression).isEqualTo(expectedExpression);
    }
}
