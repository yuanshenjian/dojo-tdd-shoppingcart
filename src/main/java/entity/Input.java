package entity;

import constant.InputType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    public static InputType getInputType(String input) {
        if (null == input) return null;

        String couponRegEx = "[0-9]{4}\\.([1-9][0-2]|[1-9])\\.([1-9][0-9]|[1-9])\\W\\d{1,}\\W\\d{1,}";
        String balanceDateRegEx = "[0-9]{4}\\.([0][1-9]|[1][0-2])\\.([0-2][0-9]|3[0-1])";
        String discountRegEx = "\\|";
        String productRegEx = "\\*";

        if (isMatcher(input, discountRegEx)) return InputType.DISCOUNT;
        if (isMatcher(input, productRegEx)) return InputType.PRODUCT;
        if (isMatcher(input, balanceDateRegEx)) return InputType.BALANCE_DATE;
        if (isMatcher(input, couponRegEx)) return InputType.COUPON;
        return null;

    }

    private static boolean isMatcher(String input, String balanceDateRegEx) {
        Pattern pattern = Pattern.compile(balanceDateRegEx);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public List<String> loadInfo(String fileName) throws IOException {
        List<String> info = new ArrayList<>();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String result = null;
        while ((result = reader.readLine()) != null) {
            if ("".equals(result)) continue;
            info.add(result);
        }
        return info;
    }


}
