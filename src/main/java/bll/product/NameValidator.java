package bll.product;

import exceptions.ValidationException;

import java.util.regex.Pattern;

public class NameValidator {

    private static final String NAME_PATTERN = "^([A-Za-z0-9]+[\\s-]*){2,50}$";

    public static void validate(String name) throws ValidationException {
        if(name.length() > 50) {
            throw  new ValidationException("Name is too long!");
        }
        else if (name.length() < 2) {
            throw new ValidationException("Address is too short");
        }
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(name).matches()) {
            throw new ValidationException("Name is not valid");
        }
    }
}
