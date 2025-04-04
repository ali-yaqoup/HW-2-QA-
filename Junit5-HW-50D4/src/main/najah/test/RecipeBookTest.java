package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("📘 RecipeBook Tests")
@Execution(ExecutionMode.CONCURRENT)
public class RecipeBookTest {

    private RecipeBook recipeBook;
    private Recipe recipe;

    @BeforeAll
    static void setupAll() {
        System.out.println("[BeforeAll] Starting RecipeBook tests...");
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("[AfterAll] Finished RecipeBook tests.");
    }

    @BeforeEach
    void setup() {
        recipeBook = new RecipeBook();
        recipe = new Recipe();
        recipe.setName("Mocha");
    }

    @AfterEach
    void cleanup() {
        System.out.println("[AfterEach] Test complete.");
    }

    @Test
    @Order(1)
    @DisplayName("Add valid recipe")
    void testAddRecipe() {
        assertAll("Add Recipe",
            () -> assertTrue(recipeBook.addRecipe(recipe)),
            () -> assertEquals("Mocha", recipeBook.getRecipes()[0].getName())
        );
    }

    @Test
    @Order(2)
    @DisplayName("Adding duplicate recipe should fail")
    void testDuplicateRecipe() {
        recipeBook.addRecipe(recipe);
        boolean addedAgain = recipeBook.addRecipe(recipe);
        assertFalse(addedAgain);
    }

    @Test
    @Order(3)
    @DisplayName("Delete recipe and return name")
    void testDeleteRecipe() {
        recipeBook.addRecipe(recipe);
        String deleted = recipeBook.deleteRecipe(0);
        assertEquals("Mocha", deleted);
    }

    @Test
    @Order(4)
    @DisplayName("Delete from empty index should return null")
    void testDeleteFromEmptySlot() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    @Order(5)
    @DisplayName("Edit recipe and replace it")
    void testEditRecipe() {
        recipeBook.addRecipe(recipe);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Latte");
        String originalName = recipeBook.editRecipe(0, newRecipe);

        assertAll("Edit Recipe",
            () -> assertEquals("Mocha", originalName),
            () -> assertEquals("", recipeBook.getRecipes()[0].getName())  // Because of setName("")
        );
    }

    @Test
    @Order(6)
    @DisplayName("Edit on empty slot should return null")
    void testEditEmptySlot() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Espresso");
        assertNull(recipeBook.editRecipe(0, newRecipe));
    }

    @ParameterizedTest
    @Order(7)
    @ValueSource(strings = { "Latte", "Cappuccino", "Americano", "Flat White" })
    @DisplayName(" Parameterized add recipes")
    void testMultipleRecipeAdditions(String name) {
        Recipe r = new Recipe();
        r.setName(name);
        assertTrue(recipeBook.addRecipe(r));
    }

    @Test
    @Order(8)
    @DisplayName("Timeout test for adding")
    @Timeout(1)
    void testTimeoutOnAdd() throws InterruptedException {
        Thread.sleep(300); // simulate delay
        assertTrue(recipeBook.addRecipe(recipe));
    }

    @Test
    @Disabled("This test fails intentionally. Fix the expected name.")
    @Order(9)
    @DisplayName("Intentionally failing test")
    void testFailing() {
        recipeBook.addRecipe(recipe);
        assertEquals("Latte", recipeBook.getRecipes()[0].getName()); // should be "Mocha"
    }
}
