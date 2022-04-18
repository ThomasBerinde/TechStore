package bll.client;

import dao.FieldsException;
import model.Client;

import java.util.regex.Pattern;

public class NameValidator {

    /** Matches names of type
     * Marius Parlab
     * M. Paduretiu
     * Ionut-Armando Parecho-Lincelo Barbu
     * */
    private static final String NAME_PATTERN = "^([a-zA-Z][\\.\\s\\-]{0,1})*$";

    public static void validate(String name) throws FieldsException {
        if (name.length() > 50) {
            throw new FieldsException("Name is too long!");
        }
        else if (name.length() < 6) {
            throw new FieldsException("Name is too short");
        }
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(name).matches()) {
            throw new FieldsException("Name is not valid!");
        }
    }
}
