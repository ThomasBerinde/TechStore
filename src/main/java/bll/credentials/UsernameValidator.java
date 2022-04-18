package bll.credentials;

import dao.FieldsException;

import java.util.regex.Pattern;

public class UsernameValidator {

    private static final String USERNAME_PATTERN = "";

    public static void validate(String username) throws FieldsException {
        if (username.length() > 50) {
            throw new FieldsException("Username is too long");
        }
        else if (username.length() < 4) {
            throw new FieldsException("Username is too short");
        }
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (!pattern.matcher(username).matches()) {
            throw new FieldsException("Invalid username");
        }
    }
}
