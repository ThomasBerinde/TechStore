package bll.product;

import dao.FieldsException;
import model.Product;

import java.util.regex.Pattern;

public class NameValidator {

    private static final String NAME_PATTERN = "^([A-Za-z0-9]+[\\s-]*){2,50}$";

    public static void validate(String name) throws FieldsException {
        if(name.length() > 50) {
            throw  new FieldsException("Name is too long!");
        }
        else if (name.length() < 2) {
            throw new FieldsException("Address is too short");
        }
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(name).matches()) {
            throw new FieldsException("Name is not valid");
        }
    }
}