package bll.product;

import exceptions.ValidationException;

import java.util.regex.Pattern;

public class CategoryValidator {

    private static final String CATEGORY_PATTERN = "^([A-Za-z]+[\\s\\-]*)+$";

    public static void validate(String category) throws ValidationException {
        Pattern pattern = Pattern.compile(CATEGORY_PATTERN);
        if (!pattern.matcher(category).matches()) {
            throw new ValidationException("Category is not valid");
        }
    }
}
