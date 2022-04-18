package bll.product;

import dao.FieldsException;

import java.util.regex.Pattern;

public class PriceValidator {

    private static final String PRICE_PATTERN = "^[1-9]\\d*$";

    public static void validate(String price) throws FieldsException {
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        if(!pattern.matcher(price).matches()) {
            throw new FieldsException("Price is not valid");
        }
        if (Long.parseLong(price) > Integer.MAX_VALUE) {
            throw new FieldsException("Price is too big!");
        }
    }
}
