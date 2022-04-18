package bll.credentials;

import bll.ValidationException;

import java.util.regex.Pattern;

public class PasswordValidator {

    /**
     * Password must have between 8-40 character and must contain:
     *  - at least one digit
     *  - at least one lowercase letter
     *  - at least one uppercase letter
     *  - at least one special character
     * */
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$*!()&;'\\]\\[`~<>?\\/,.\"%]).{8,40})";

    public static void validate(String password) throws ValidationException {
        if (password.length() > 50) {
            throw new ValidationException("Password is too long");
        }
        else if (password.length() < 6) {
            throw new ValidationException("Password is too short");
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pattern.matcher(password).matches()) {
            throw new ValidationException("Invalid password");
        }
    }
}
