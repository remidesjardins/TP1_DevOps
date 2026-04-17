package fr.efrei.tp1devops.calculator.service;

import fr.efrei.tp1devops.calculator.dto.CalculationResponse;
import fr.efrei.tp1devops.calculator.exception.DivisionByZeroException;
import fr.efrei.tp1devops.calculator.exception.InvalidOperandException;
import fr.efrei.tp1devops.calculator.validation.OperandValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService(new OperandValidator());
    }

    @Test
    void add_returnsSum() {
        CalculationResponse r = calculatorService.add(1, 2);
        assertThat(r.operation()).isEqualTo("add");
        assertThat(r.a()).isEqualTo(1);
        assertThat(r.b()).isEqualTo(2);
        assertThat(r.result()).isEqualTo(3);
    }

    @Test
    void subtract_returnsDifference() {
        CalculationResponse r = calculatorService.subtract(5, 3);
        assertThat(r.operation()).isEqualTo("sub");
        assertThat(r.result()).isEqualTo(2);
    }

    @Test
    void multiply_returnsProduct() {
        CalculationResponse r = calculatorService.multiply(2, 4);
        assertThat(r.operation()).isEqualTo("mul");
        assertThat(r.result()).isEqualTo(8);
    }

    @Test
    void divide_returnsQuotient() {
        CalculationResponse r = calculatorService.divide(10, 2);
        assertThat(r.operation()).isEqualTo("div");
        assertThat(r.result()).isEqualTo(5);
    }

    @Test
    void divide_byZero_throws() {
        assertThatThrownBy(() -> calculatorService.divide(10, 0))
                .isInstanceOf(DivisionByZeroException.class)
                .hasMessageContaining("Division by zero");
    }

    @Test
    void rejects_nanOperand() {
        assertThatThrownBy(() -> calculatorService.add(Double.NaN, 1))
                .isInstanceOf(InvalidOperandException.class);
    }
}
