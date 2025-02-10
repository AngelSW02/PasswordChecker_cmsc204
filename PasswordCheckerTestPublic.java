import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * JUnit tests for PasswordChecker methods.
 * @author Angel Abrigo
 */
class PasswordCheckerTestPublic {
    ArrayList<String> passwordsArray;
    String password = "Hello";
    String passwordConfirm = "hello";
    String allCaps = "HELLO";
    String withDigit = "Hello6";

    @BeforeEach
    public void setUp() throws Exception {
        String[] p = { "334455BB", "Im2cool4U" };
        passwordsArray = new ArrayList<>();
        passwordsArray.addAll(Arrays.asList(p));
    }

    @AfterEach
    public void tearDown() throws Exception {
        passwordsArray = null;
    }

    /**
     * Tests if two passwords match.
     * Should throw an UnmatchedException when they are different.
     */
    @Test
    public void testComparePasswords() {
        Throwable exception = assertThrows(UnmatchedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.comparePasswords(password, passwordConfirm);
            }
        });

        assertEquals("The passwords do not match", exception.getMessage());
    }

    /**
     * Tests comparePasswordsWithReturn method.
     */
    @Test
    public void testComparePasswordsWithReturn() {
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
    }

    /**
     * Tests if the password contains at least one uppercase letter.
     */
    @Test
    public void testHasUpperAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
        } catch (NoUpperAlphaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password is too short (less than 6 characters).
     * Should throw a LengthException.
     */
    @Test
    public void testInvalidLength() {
        Throwable exception = assertThrows(LengthException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.isValidLength(password);
            }
        });
        assertEquals("The password must be at least 6 characters long", exception.getMessage());
    }

    /**
     * Tests the getInvalidPasswords method.
     * Ensures the correct invalid passwords and messages are returned.
     */
    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
        assertEquals(results.size(), 2);
        assertEquals(results.get(0),
                "334455BB -> The password must contain at least one lowercase alphabetic character");
        assertEquals(results.get(1), "Im2cool4U -> The password must contain at least one special character");
    }
}
