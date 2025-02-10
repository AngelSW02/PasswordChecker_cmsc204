import java.util.ArrayList;

/**
 * Utility class for checking password validity.
 * @author Angel Abrigo
 */
public class PasswordCheckerUtility {

    /**
     * Compares equality of two passwords.
     * @param password First password.
     * @param passwordConfirm Second password.
     * @throws UnmatchedException If the passwords do not match.
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!comparePasswordsWithReturn(password, passwordConfirm)) {
            throw new UnmatchedException();
        }
    }

    /**
     * Compares equality of two passwords.
     * @param password First password.
     * @param passwordConfirm Second password.
     * @return True if passwords match, false otherwise.
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    /**
     * Reads a list of passwords and returns invalid ones along with the reason.
     * @param passwords List of passwords.
     * @return List of invalid passwords with their respective error messages.
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " -> " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    /**
     * Checks if the password length is between 6 and 9 characters.
     * @param password Password to check.
     * @return True if the password length is between 6 and 9, false otherwise.
     */
    public static boolean hasBetweenSixAndNineChars(String password) {
        return password.length() >= 6 && password.length() <= 9;
    }

    /**
     * Checks if the password contains at least one digit.
     * @param password Password to check.
     * @return True if the password contains at least one digit.
     * @throws NoDigitException If no digit is found.
     */
    public static boolean hasDigit(String password) throws NoDigitException {
        if (password.chars().anyMatch(Character::isDigit)) {
            return true;
        }
        throw new NoDigitException();
    }

    /**
     * Checks if the password contains at least one lowercase letter.
     * @param password Password to check.
     * @return True if the password contains at least one lowercase letter.
     * @throws NoLowerAlphaException If no lowercase letter is found.
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        if (password.chars().anyMatch(Character::isLowerCase)) {
            return true;
        }
        throw new NoLowerAlphaException();
    }

    /**
     * Checks if the password contains more than two of the same character in sequence.
     * @param password Password to check.
     * @return True if the password has repeating characters.
     * @throws InvalidSequenceException If more than two consecutive identical characters are found.
     */
    public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                throw new InvalidSequenceException();
            }
        }
        return false;
    }

    /**
     * Checks if the password contains at least one special character.
     * @param password Password to check.
     * @return True if the password contains a special character.
     * @throws NoSpecialCharacterException If no special character is found.
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
        if (password.matches(".*[!@#$%^&*()-+=<>?/.,:;{}\\[\\]`~].*")) {
            return true;
        }
        throw new NoSpecialCharacterException();
    }

    /**
     * Checks if the password contains at least one uppercase letter.
     * @param password Password to check.
     * @return True if the password contains an uppercase letter.
     * @throws NoUpperAlphaException If no uppercase letter is found.
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        if (password.chars().anyMatch(Character::isUpperCase)) {
            return true;
        }
        throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
    }

    /**
     * Checks if the password meets the minimum length requirement (at least 6 characters).
     * @param password Password to check.
     * @return True if the password meets the length requirement.
     * @throws LengthException If the password is too short.
     */
    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() >= 6) {
            return true;
        }
        throw new LengthException();
    }

    /**
     * Checks if the password meets all validity requirements.
     * @param password Password to check.
     * @return True if the password is valid.
     * @throws LengthException If the password is too short.
     * @throws NoUpperAlphaException If the password lacks uppercase letters.
     * @throws NoLowerAlphaException If the password lacks lowercase letters.
     * @throws NoDigitException If the password lacks digits.
     * @throws NoSpecialCharacterException If the password lacks special characters.
     * @throws InvalidSequenceException If the password contains repeated sequences.
     */
    public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
            NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        if (password.length() < 6)
            throw new LengthException("The password must be at least 6 characters long");
        if (!hasUpperAlpha(password))
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        if (!hasLowerAlpha(password))
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        if (!hasDigit(password))
            throw new NoDigitException("The password must contain at least one digit");
        if (!hasSpecialChar(password))
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        if (hasSameCharInSequence(password))
            throw new InvalidSequenceException(
                    "The password cannot contain more than two of the same character in sequence.");
        return true;
    }

    /**
     * Checks if the password is valid but considered weak (6-9 characters long).
     * @param password Password to check.
     * @return True if the password is weak.
     * @throws WeakPasswordException If the password meets minimum criteria but is weak.
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        return hasBetweenSixAndNineChars(password);
    }
}
