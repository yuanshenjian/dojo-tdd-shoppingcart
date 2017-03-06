package common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonTools {
    public static BigDecimal setScale(BigDecimal bigDecimal) {

        return bigDecimal.setScale(2, RoundingMode.HALF_UP);

    }
    public static Date formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.parse(date);


    }

}
