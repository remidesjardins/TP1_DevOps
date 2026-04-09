package fr.efrei.tp1devops.calculator.controller;

import fr.efrei.tp1devops.calculator.exception.GlobalExceptionHandler;
import fr.efrei.tp1devops.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CalculatorController.class)
@Import({CalculatorService.class, GlobalExceptionHandler.class})
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void add_returnsJson() throws Exception {
        mockMvc.perform(get("/api/calc/add").param("a", "1").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("add"))
                .andExpect(jsonPath("$.a").value(1))
                .andExpect(jsonPath("$.b").value(2))
                .andExpect(jsonPath("$.result").value(3));
    }

    @Test
    void sub_returnsJson() throws Exception {
        mockMvc.perform(get("/api/calc/sub").param("a", "5").param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("sub"))
                .andExpect(jsonPath("$.result").value(2));
    }

    @Test
    void mul_returnsJson() throws Exception {
        mockMvc.perform(get("/api/calc/mul").param("a", "2").param("b", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("mul"))
                .andExpect(jsonPath("$.result").value(8));
    }

    @Test
    void div_returnsJson() throws Exception {
        mockMvc.perform(get("/api/calc/div").param("a", "10").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation").value("div"))
                .andExpect(jsonPath("$.result").value(5));
    }

    @Test
    void div_byZero_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/calc/div").param("a", "10").param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Division by zero is not allowed"));
    }

    @Test
    void missingParam_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/calc/add").param("a", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(containsString("b")));
    }
}
