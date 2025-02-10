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
public class PasswordCheckerTestPrivate {
    ArrayList<String> invalidPasswordsArray;
    ArrayList<String> validPasswordsArray;
    String password = "Hello";
    String passwordConfirm = "hello";
    String allCaps = "HELLO";
    String withDigit = "Hello6";
    String withSpecialChar = "Hello!";
    String withNoDuplicate = "GoodBye!";
    String between6And9Chars = "JavaisFun";
    String longPassword = "JavaisFunveryfun";

    @BeforeEach
    void setUp() throws Exception {
        String[] containsInvalidPwd = { "334455BB", "Im2cool4U", "george2ZZZ", "4sale", "bertha22", "4wardMarch",
                "august30", "Applesxx", "aa11b", "pilotProject", "myPassword", "myPassword2" };
        invalidPasswordsArray = new ArrayList<>();
        invalidPasswordsArray.addAll(Arrays.asList(containsInvalidPwd));

        String[] allValidPasswords = { "a1A#b1Bc1Cd1D", "Hello@123", "4heB#rex7", "4saleHe!", "myPassword2%" };
        validPasswordsArray = new ArrayList<>();
        validPasswordsArray.addAll(Arrays.asList(allValidPasswords));
    }

    @AfterEach
    void tearDown() throws Exception {
        invalidPasswordsArray = null;
        validPasswordsArray = null;
    }

    /**
     * Tests if two passwords match.
     * Should throw an UnmatchedException when they are different.
     */
    @Test
    void testComparePasswords() {
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
    void testComparePasswordsWithReturn() {
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
    }

    /**
     * Tests if the password length is valid.
     */
    @Test
    void testValidLengthValid() {
        try {
            assertTrue(PasswordCheckerUtility.isValidLength("Beautiful"));
        } catch (LengthException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password is too short (less than 6 characters).
     * Should throw a LengthException.
     */
    @Test
    void testInValidLength() {
        Throwable exception = assertThrows(LengthException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.isValidLength(password);
            }
        });
        assertEquals("The password must be at least 6 characters long", exception.getMessage());
    }

    /**
     * Tests if the password contains at least one uppercase letter.
     */
    @Test
    void testHasUpperAlphaValid() {
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
        } catch (NoUpperAlphaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password lacks uppercase letters.
     * Should throw a NoUpperAlphaException.
     */
    @Test
    void testDoesNotHaveUpperAlpha() {
        Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasUpperAlpha(passwordConfirm);
            }
        });
        assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
    }

    /**
     * Tests if the password contains at least one lowercase letter.
     */
    @Test
    public void testHasLowerAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
        } catch (NoLowerAlphaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password lacks lowercase letters.
     * Should throw a NoLowerAlphaException.
     */
    @Test
    public void testDoesNotHaveLowerAlpha() {
        Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasLowerAlpha(allCaps);
            }
        });
        assertEquals("The password must contain at least one lowercase alphabetic character", exception.getMessage());
    }

    /**
     * Tests if the password contains at least one digit.
     */
    @Test
    public void testHasDigit() {
        try {
            assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
        } catch (NoDigitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password lacks digits.
     * Should throw a NoDigitException.
     */
    @Test
    public void testDoesNotHaveDigit() {
        Throwable exception = assertThrows(NoDigitException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasDigit(password);
            }
        });
        assertEquals("The password must contain at least one digit", exception.getMessage());
    }

    /**
     * Tests if the password contains at least one special character.
     */
    @Test
    public void testHasSpecialChar() {
        try {
            assertTrue(PasswordCheckerUtility.hasSpecialChar(withSpecialChar));
        } catch (NoSpecialCharacterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if the password lacks special characters.
     * Should throw a NoSpecialCharacterException.
     */
    @Test
    public void testDoesNotHaveSpecialChar() {
        Throwable exception = assertThrows(NoSpecialCharacterException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasSpecialChar(password);
            }
        });
        assertEquals("The password must contain at least one special character", exception.getMessage());
    }

    /**
     * Tests if the password contains repeated characters in sequence.
     * Should throw an InvalidSequenceException.
     */
    @Test
    public void testHasSameCharInSequence() {
        Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.hasSameCharInSequence("AAAbb@123");
            }
        });
        assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
    }

    /**
     * Tests if the password does not have repeated characters in sequence.
     */
    @Test
    public void testDoesNotHaveSameCharInSequence() {
        try {
            assertTrue(PasswordCheckerUtility.hasSpecialChar(withNoDuplicate));
        } catch (NoSpecialCharacterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if a password has between 6 and 9 characters.
     */
    @Test
    public void testHasBetweenSixAndNineChars() {
        assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(between6And9Chars));
        assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(withSpecialChar));
        assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars(longPassword));
    }
}
