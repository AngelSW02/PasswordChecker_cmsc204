import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * JUnit tests for PasswordChecker methods.
 * @author Angel Abrigo
 */
public class PasswordCheckerSTUDENT_Test {
    ArrayList<String> passwords;

    @Before
    public void setUp() throws Exception {
        passwords = new ArrayList<String>();
    }

    @After
    public void tearDown() throws Exception {
        passwords = null;
    }

    /**
     * Tests if a password is too short (less than 6 characters).
     * The second case should throw a LengthException.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        // Valid case
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Wonderful"));
        } catch (LengthException e) {
            assertTrue("Successfully threw a LengthException", false);
        } catch (Exception e) {
            assertTrue("Threw an unexpected exception", true);
        }

        // Invalid case (too short)
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Gam3?"));
        } catch (LengthException e) {
            assertTrue("Successfully threw a LengthException", true);
        } catch (Exception e) {
            assertTrue("Threw an unexpected exception", false);
        }
    }

    /**
     * Tests if a password contains at least one uppercase letter.
     * The second case should throw a NoUpperAlphaException.
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Bored"));
        } catch (NoUpperAlphaException e) {
            e.printStackTrace();
        }

        try {
            assertFalse(PasswordCheckerUtility.hasUpperAlpha("bored"));
        } catch (NoUpperAlphaException e) {
            assertTrue("Successfully threw a NoUpperAlphaException", true);
        }
    }

    /**
     * Tests if a password contains at least one lowercase letter.
     * The second case should throw a NoLowerAlphaException.
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha("Bored"));
        } catch (NoLowerAlphaException e) {
            e.printStackTrace();
        }

        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha("BORED"));
        } catch (NoLowerAlphaException e) {
            assertTrue("Successfully threw a NoLowerAlphaException", true);
        }
    }

    /**
     * Tests if a password is weak (between 6 and 9 characters).
     * The second case should throw a WeakPasswordException.
     */
    @Test
    public void testIsWeakPassword() {
        try {
            assertFalse(PasswordCheckerUtility.isWeakPassword("oneTwoThree"));
        } catch (WeakPasswordException e) {
            e.printStackTrace();
        }

        try {
            assertTrue(PasswordCheckerUtility.isWeakPassword("oneTwo"));
        } catch (WeakPasswordException e) {
            assertTrue("Successfully threw a WeakPasswordException", true);
        }
    }

    /**
     * Tests if a password contains more than two of the same character in sequence.
     * The second case should throw an InvalidSequenceException.
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            assertFalse(PasswordCheckerUtility.hasSameCharInSequence("nononono"));
        } catch (InvalidSequenceException e) {
            e.printStackTrace();
        }

        Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasSameCharInSequence("LLLiz");
            }
        });
        assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
    }

    /**
     * Tests if a password contains at least one digit.
     * The second case should throw a NoDigitException.
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            assertTrue(PasswordCheckerUtility.hasDigit("wow4fun"));
        } catch (NoDigitException e) {
            e.printStackTrace();
        }

        Throwable exception = assertThrows(NoDigitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasDigit("notFound");
            }
        });
        assertEquals("The password must contain at least one digit", exception.getMessage());
    }

    /**
     * Tests valid passwords to ensure they do not throw an exception.
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Wow4!real"));
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Night9L#"));
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    /**
     * Tests the getInvalidPasswords method.
     * Ensures the correct invalid passwords and messages are returned.
     */
    @Test
    public void testInvalidPasswords() {
        passwords.add("Wow4fun");
        passwords.add("Night9L#");
        passwords.add("LLL!77z");

        ArrayList<String> results;
        results = PasswordCheckerUtility.getInvalidPasswords(passwords);
        assertEquals(results.size(), 2);
        assertEquals(results.get(0), "Wow4fun -> The password must contain at least one special character");
        assertEquals(results.get(1), "LLL!77z -> The password cannot contain more than two of the same character in sequence");
    }
}
