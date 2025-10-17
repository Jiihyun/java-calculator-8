package calculator.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        NumberExtractor numberExtractor = new NumberExtractor();
        ExpressionParser expressionParser = new ExpressionParser(new DelimiterExtractor());
        calculator = new Calculator(numberExtractor, expressionParser);
    }

    @ParameterizedTest
    @CsvSource({
            "1:2:3:4, 10",
            "//&\\n10&20&30, 60",
            "//%\\n10000%200000:3000000, 3210000"
    })
    void 값을_입력받아_덧셈을_수행한다(String input, int answer) {
        // when
        long result = calculator.sum(input);
        // then
        assertThat(result).isEqualTo(answer);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1:2:3:4", "0,2,30",
            "//%\\n100000000000000000000000000000000000000", "/&\\n4&5&6",
            "//5\\n595854", ":,:",
            "// \\n1 2 3", "1.0:2.3"})
    void 잘못된_값을_입력받으면_덧셈_수행시_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> calculator.sum(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
