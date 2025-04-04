package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("👤 UserService Tests")
@Execution(ExecutionMode.CONCURRENT)
public class UserServiceSimpleTest {

    private UserService userService;

    @BeforeAll
    static void initAll() {
        System.out.println("[BeforeAll] Starting UserService tests...");
    }

    @AfterAll
    static void cleanAll() {
        System.out.println("[AfterAll] Finished UserService tests.");
    }

    @BeforeEach
    void setup() {
        System.out.println("[BeforeEach] Init UserService");
        userService = new UserService();
    }

    @AfterEach
    void cleanup() {
        System.out.println("[AfterEach] Done.");
    }

    @Test
    @Order(1)
    @DisplayName("Valid email formats")
    void testValidEmails() {
        assertAll(
            () -> assertTrue(userService.isValidEmail("user@example.com")),
            () -> assertTrue(userService.isValidEmail("test@domain.org")),
            () -> assertTrue(userService.isValidEmail("me@co.uk"))
        );
    }

    @Test
    @Order(2)
    @DisplayName("Invalid email formats")
    void testInvalidEmails() {
        assertAll(
            () -> assertFalse(userService.isValidEmail("userexample.com")),
            () -> assertFalse(userService.isValidEmail("user@com")),
            () -> assertFalse(userService.isValidEmail(null)),
            () -> assertFalse(userService.isValidEmail("plainaddress"))
        );
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {
        "admin:1234",    
        "admin:wrong",   
        "user:1234",     
        "test:test"      
    })
    @DisplayName("🔐 Parameterized auth test")
    void testAuth(String input) {
        String[] parts = input.split(":");
        String username = parts[0];
        String password = parts[1];

        if ("admin".equals(username) && "1234".equals(password)) {
            assertTrue(userService.authenticate(username, password));
        } else {
            assertFalse(userService.authenticate(username, password));
        }
    }

    @Test
    @Order(4)
    @Timeout(1)
    @DisplayName("Timeout test (auth)")
    void testAuthTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @Order(5)
    @Disabled("This test fails intentionally for demonstration")
    @DisplayName("Failing test (intentionally wrong expectation)")
    void testFailingAuth() {
        assertTrue(userService.authenticate("admin", "wrong")); // should be false
    }
}
