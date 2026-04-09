package fr.efrei.tp1devops.calculator.service;

import fr.efrei.tp1devops.calculator.dto.CalculationResponse;
import fr.efrei.tp1devops.calculator.exception.DivisionByZeroException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

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
}
