package fr.efrei.tp1devops.calculator.validation;

import fr.efrei.tp1devops.calculator.exception.InvalidOperandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperandValidatorTest {

    private OperandValidator validator;

    @BeforeEach
    void setUp() {
        validator = new OperandValidator();
    }

    @Test
    void accepts_finiteOperandsWithinBound() {
        assertThatCode(() -> validator.validatePair(0, -1.5d)).doesNotThrowAnyException();
        assertThatCode(() -> validator.validatePair(OperandValidator.ABS_MAX_OPERAND, 0)).doesNotThrowAnyException();
    }

    @Test
    void rejects_nan() {
        assertThatThrownBy(() -> validator.validatePair(Double.NaN, 1))
                .isInstanceOf(InvalidOperandException.class)
                .hasMessageContaining("a");
        assertThatThrownBy(() -> validator.validatePair(1, Double.NaN))
                .isInstanceOf(InvalidOperandException.class)
                .hasMessageContaining("b");
    }

    @Test
    void rejects_infinity() {
        assertThatThrownBy(() -> validator.validatePair(Double.POSITIVE_INFINITY, 0))
                .isInstanceOf(InvalidOperandException.class)
                .hasMessageContaining("infinity");
    }

    @Test
    void rejects_outOfBounds() {
        assertThatThrownBy(() -> validator.validatePair(OperandValidator.ABS_MAX_OPERAND * 2, 0))
                .isInstanceOf(InvalidOperandException.class)
                .hasMessageContaining("out of bounds");
    }
}
