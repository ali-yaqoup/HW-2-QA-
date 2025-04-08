package main.najah.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeTest {

    private Recipe recipe;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting Recipe tests.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Recipe tests complete.");
    }

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        System.out.println("Setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test finished.\n");
    }

    @Test
    @DisplayName("Valid-Chocolate-Amount-Test")
    void testSetValidChocolateAmount() throws RecipeException {
        recipe.setAmtChocolate("5");
        assertEquals(5, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Invalid-Chocolate-Amount-(non-integer)")
    void testSetInvalidChocolateAmount_NonInteger() {
        RecipeException exception = assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("abc");
        });
        assertEquals("Units of chocolate must be a positive integer", exception.getMessage());
    }

    @Test
    @DisplayName("Invalid-Chocolate-Amount-(negative)")
    void testSetInvalidChocolateAmount_Negative() {
        RecipeException exception = assertThrows(RecipeException.class, () -> {
            recipe.setAmtChocolate("-3");
        });
        assertEquals("Units of chocolate must be a positive integer", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "10", "100"})
    @DisplayName("Parameterized-Coffee-Amount-Test")
    void testSetAmtCoffeeWithValidValues(String value) throws RecipeException {
        recipe.setAmtCoffee(value);
        assertEquals(Integer.parseInt(value), recipe.getAmtCoffee());
    }

    @Test
    @DisplayName("Multiple-Assertions-for-Milk")
    void testSetAmtMilkMultipleAssertions() throws RecipeException {
        recipe.setAmtMilk("4");
        assertAll(
            () -> assertEquals(4, recipe.getAmtMilk()),
            () -> assertNotEquals(5, recipe.getAmtMilk()),
            () -> assertTrue(recipe.getAmtMilk() >= 0)
        );
    }

    @Test
    @DisplayName("Timeout Test - toString()")
    void testToStringWithinTime() {
        assertTimeout(Duration.ofMillis(100), () -> {
            recipe.setName("Latte");
            assertEquals("Latte", recipe.toString());
        });
    }

    @Test
    @Disabled("This-test-is-intentionally-failing. To fix: set same name before comparing.")
    @DisplayName("Disabled Failing Test - equals()")
    void testFailingNameComparison() {
        Recipe other = new Recipe();
        other.setName("Mocha");
        assertEquals(recipe, other); // this should fail
    }

    @Test
    @DisplayName("Equals-and-HashCode-Same-Name")
    void testEqualsAndHashCode() {
        recipe.setName("Espresso");
        Recipe other = new Recipe();
        other.setName("Espresso");

        assertAll(
            () -> assertTrue(recipe.equals(other)),
            () -> assertEquals(recipe.hashCode(), other.hashCode())
        );
    }

    @Test
    @DisplayName("Get-and-Set-All-Ingredients")
    void testSetAndGetAllIngredients() throws RecipeException {
        recipe.setAmtChocolate("2");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("4");
        recipe.setAmtSugar("1");

        assertAll(
            () -> assertEquals(2, recipe.getAmtChocolate()),
            () -> assertEquals(3, recipe.getAmtCoffee()),
            () -> assertEquals(4, recipe.getAmtMilk()),
            () -> assertEquals(1, recipe.getAmtSugar())
        );
    }

    @Test
    @DisplayName("Set Price - Valid")
    void testSetValidPrice() throws RecipeException {
        recipe.setPrice("50");
        assertEquals(50, recipe.getPrice());
    }

    @Test
    @DisplayName("Set Price - Non Integer")
    void testSetInvalidPriceNonInteger() {
        RecipeException ex = assertThrows(RecipeException.class, () -> {
            recipe.setPrice("abc");
        });
        assertEquals("Price must be a positive integer", ex.getMessage());
    }

    @Test
    @DisplayName("Set Price - Negative")
    void testSetInvalidPriceNegative() {
        RecipeException ex = assertThrows(RecipeException.class, () -> {
            recipe.setPrice("-10");
        });
        assertEquals("Price must be a positive integer", ex.getMessage());
    }

    @Test
    @DisplayName("Set-name-to-null-should-not-override-current-name")
    void testSetNameWithNull() {
        recipe.setName("Original");
        recipe.setName(null);
        assertEquals("Original", recipe.getName());
    }

    @Test
    @DisplayName("Equals same object")
    void testEqualsSameObject() {
        assertTrue(recipe.equals(recipe));
    }

    @Test
    @DisplayName("Equals with null")
    void testEqualsWithNull() {
        assertFalse(recipe.equals(null));
    }

    @Test
    @DisplayName("Equals with different class")
    void testEqualsDifferentClass() {
        assertFalse(recipe.equals("Not a Recipe"));
    }

    @Test
    @DisplayName("Equals with different name")
    void testEqualsDifferentName() {
        recipe.setName("Recipe1");
        Recipe other = new Recipe();
        other.setName("Recipe2");
        assertFalse(recipe.equals(other));
    }

    @Test
    @DisplayName("Equals with null and non-null name")
    void testEqualsNullAndNonNullName() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r2.setName("Test");
        assertFalse(r1.equals(r2));
    }
}