package bll.client;

import exceptions.ValidationException;

import java.util.regex.Pattern;

public class AgeValidator {

    /** Matches number of type:
     * 2
     * 45
     * 999
     * */
    private static final String AGE_PATTERN = "^[1-9]\\d{0,2}$";

    public static void validate(String age) throws ValidationException {
        Pattern pattern = Pattern.compile(AGE_PATTERN);
        if (!pattern.matcher(age).matches()) {
            throw new ValidationException("Age is not valid!");
        }
        if (Integer.parseInt(age) > 120) {
            throw new ValidationException("Age is too big!");
        }
    }
}
