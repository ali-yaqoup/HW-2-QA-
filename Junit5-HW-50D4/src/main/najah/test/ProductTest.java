package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName(" Product Tests")
@Execution(ExecutionMode.CONCURRENT)
public class ProductTest {

    Product product;

    @BeforeAll
    static void initAll() {
        System.out.println("[BeforeAll] Starting Product tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("[AfterAll] Finished Product tests.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("[BeforeEach] Creating default product");
        product = new Product("Laptop", 1000);
    }

    @AfterEach
    void cleanUp() {
        System.out.println("[AfterEach] Test complete.");
    }

    @Test
    @Order(1)
    @DisplayName("Product initialized correctly")
    void testProductInitialization() {
        assertAll("Initial values",
            () -> assertEquals("Laptop", product.getName()),
            () -> assertEquals(1000, product.getPrice()),
            () -> assertEquals(0, product.getDiscount()),
            () -> assertEquals(1000, product.getFinalPrice())
        );
    }

    @Test
    @Order(2)
    @DisplayName("Apply valid discount")
    void testValidDiscount() {
        product.applyDiscount(25);
        assertEquals(750, product.getFinalPrice());
    }

    @Test
    @Order(3)
    @DisplayName("Invalid discount (>50%) throws exception")
    void testInvalidDiscountTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
    }

    @Test
    @Order(4)
    @DisplayName("Negative discount throws exception")
    void testInvalidDiscountNegative() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
    }

    @Test
    @Order(5)
    @DisplayName("Negative price throws exception")
    void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone", -300));
    }

    @ParameterizedTest
    @Order(6)
    @CsvSource({
        "0, 1000",
        "10, 900",
        "50, 500"
    })
    @DisplayName(" Parameterized discount test")
    void testDiscounts(double discount, double expectedPrice) {
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getFinalPrice());
    }

    @Test
    @Order(7)
    @DisplayName("Timeout test (under 1 sec)")
    void testTimeout() {
        assertTimeoutPreemptively(java.time.Duration.ofSeconds(1), () -> {
            product.applyDiscount(20);
            assertEquals(800, product.getFinalPrice());
        });
    }

    @Test
    @Disabled("Fails intentionally for demo purposes")
    @Order(8)
    @DisplayName("Failing test (intentional)")
    void failingTest() {
        product.applyDiscount(10);
        assertEquals(950, product.getFinalPrice()); 
    }

    @Test
    @Order(9)
    @DisplayName("Discount boundaries (0% and 50%)")
    void testBoundaryDiscounts() {
        product.applyDiscount(0);
        assertEquals(1000, product.getFinalPrice());

        product.applyDiscount(50);
        assertEquals(500, product.getFinalPrice());
    }

    @Test
    @Order(10)
    @DisplayName("Check getDiscount after applying")
    void testGetDiscount() {
        product.applyDiscount(20);
        assertEquals(20, product.getDiscount());
    }
}
