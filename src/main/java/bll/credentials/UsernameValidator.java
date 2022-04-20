package bll.credentials;

import exceptions.ValidationException;

import java.util.regex.Pattern;

public class UsernameValidator {

    /** Contains only letters and underscores, but an underscore cannot follow another underscore and cannot be
     * at the beginning or the end of the username */
    private static final String USERNAME_PATTERN = "^[a-zA-Z]((([a-zA-Z])*[_]?(?!_))*[a-zA-Z])*$";

    public static void validate(String username) throws ValidationException {
        if (username.length() > 50) {
            throw new ValidationException("Username is too long");
        }
        else if (username.length() < 4) {
            throw new ValidationException("Username is too short");
        }
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        if (!pattern.matcher(username).matches()) {
            throw new ValidationException("Invalid username");
        }
    }
}
