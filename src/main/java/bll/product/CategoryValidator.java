package bll.product;

import dao.FieldsException;
import model.Product;

import java.util.regex.Pattern;

public class CategoryValidator {

    private static final String CATEGORY_PATTERN = "^([A-Za-z]+[\\s\\-]*)+$";

    public static void validate(String category) throws FieldsException {
        Pattern pattern = Pattern.compile(CATEGORY_PATTERN);
        if (!pattern.matcher(category).matches()) {
            throw new FieldsException("Category is not valid");
        }
    }
}
