package bll.client;

import dao.FieldsException;

import java.util.regex.Pattern;

public class AgeValidator {

    /** Matches number of type:
     * 2
     * 45
     * 999
     * */
    private static final String AGE_PATTERN = "^[1-9]\\d{0,2}$";

    public static void validate(String age) throws FieldsException {
        Pattern pattern = Pattern.compile(AGE_PATTERN);
        if (!pattern.matcher(age).matches()) {
            throw new FieldsException("Age is not valid!");
        }
        if (Integer.parseInt(age) > 120) {
            throw new FieldsException("Age is too big!");
        }
    }
}
