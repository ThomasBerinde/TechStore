package bll.client;

import exceptions.ValidationException;

import java.util.regex.Pattern;

public class AddressValidator {

    /** Matches addresses of type:
     *  Marcu Jao 45
     *  str. Ion Luca Caragiale nr. 83
     * */
    private static final String ADDRESS_PATTERN =
            "^(((?i)(str){0,1})\\.{0,1}\\s+){0,1}(\\b\\w*\\b\\s+){1,3}((?i)((nr){0,1})\\.{0,1}\\s+)*\\d{1,5}$";

    public static void validate(String address) throws ValidationException {
        if (address.length() > 100) {
            throw new ValidationException("Address is too long!");
        }
        else if (address.length() < 4) {
            throw new ValidationException("Address is too short");
        }
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        if (!pattern.matcher(address).matches()) {
            throw new ValidationException("Address is not valid!");
        }
    }
}
