package bll;

import java.util.regex.Pattern;

public class IdValidator {

    /** Matches number of type:
     * 2
     * 45
     * 999
     * 214124421
     * */
    private static final String AGE_PATTERN = "^[1-9]\\d{1,18}$";

    public void validate(String id) {
        Pattern pattern = Pattern.compile(AGE_PATTERN);
        if (!pattern.matcher(id).matches()) {
            throw new IllegalArgumentException("ID is not valid!");
        }
        if (Long.parseLong(id) > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("ID is too big!");
        }
    }
}
