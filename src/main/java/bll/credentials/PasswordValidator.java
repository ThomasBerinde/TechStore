package bll.credentials;

import dao.FieldsException;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN = "";

    public static void validate(String password) throws FieldsException {
        if (password.length() > 50) {
            throw new FieldsException("Password is too long");
        }
        else if (password.length() < 6) {
            throw new FieldsException("Password is too short");
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pattern.matcher(password).matches()) {
            throw new FieldsException("Invalid password");
        }
    }
}
