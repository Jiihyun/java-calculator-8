package calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumbersTest {

    @Test
    void 숫자들을_덧셈할_수_있다() {
        // given
        Numbers numbers = new Numbers(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L));
        // when
        long sum = numbers.sum();
        // then
        assertThat(sum).isEqualTo(55);
    }

    @Test
    void 숫자가_없을_경우_예외가_발생한다() {
        // given
        List<Long> numbers = List.of();
        // when & then
        assertThatThrownBy(() -> new Numbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L, -999999L})
    void 양수가_아닌_값이_포함된_경우_예외가_발생한다(long number) {
        // given
        List<Long> numbers = List.of(number);
        // when & then
        assertThatThrownBy(() -> new Numbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자는 양수만 가능합니다.");
    }

    @Test
    void 덧셈_가능_범위를_초과하면_예외가_발생한다() {
        // given
        Numbers numbers = new Numbers(List.of(Long.MAX_VALUE, 1L));
        // when & then
        assertThatThrownBy(numbers::sum)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 덧셈 가능한 범위를 초과하였습니다");
    }
}
