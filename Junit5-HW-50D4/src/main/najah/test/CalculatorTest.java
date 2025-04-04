package main.najah.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.najah.code.Calculator;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName(" Calculator Tests")
public class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("[BeforeAll] Starting Calculator tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("[AfterAll] Finished Calculator tests.");
    }

    @BeforeEach
    void setup() {
        System.out.println("[BeforeEach] Initializing Calculator");
        calculator = new Calculator();
    }

    @AfterEach
    void cleanup() {
        System.out.println("[AfterEach] Calculator test complete.");
    }

    @Test
    @Order(1)
    @DisplayName("Add multiple and single numbers")
    void testAdd() {
        assertAll("Addition tests",
            () -> assertEquals(0, calculator.add()),
            () -> assertEquals(6, calculator.add(1, 2, 3)),
            () -> assertEquals(-10, calculator.add(-5, -5)),
            () -> assertEquals(7, calculator.add(7)) 
        );
    }

    @Test
    @Order(2)
    @DisplayName("Divide valid numbers")
    void testDivide() {
        assertAll("Division tests",
            () -> assertEquals(2, calculator.divide(10, 5)),
            () -> assertEquals(-2, calculator.divide(-10, 5)) 
        );
    }

    @Test
    @Order(3)
    @DisplayName("Divide by zero should throw exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @ParameterizedTest(name="factorial({0}) = {1}")
    @CsvSource({
        "0, 1",
        "1, 1",
        "3, 6",
        "5, 120",
        "6, 720",      
        "7, 5040"
    })
    @Order(4)
    @DisplayName("Parameterized factorial test")
    void testFactorialParameterized(int input, int expected) {
        assertEquals(expected, calculator.factorial(input));
    }

    @Test
    @Order(5)
    @DisplayName("Factorial with negative input throws exception")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }

    @Test
    @Disabled("This test fails intentionally. Fix by changing expected result.")
    @Order(6)
    @DisplayName("Failing test (intentional)")
    void failingTest() {
        assertEquals(100, calculator.add(50, 30)); // should be 80
    }
}
