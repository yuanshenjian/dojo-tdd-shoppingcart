package entity.cartContentEntity;

import common.CommonTools;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class Coupon extends CartContent {
    private Date effectiveDate;
    private BigDecimal quota = new BigDecimal(0);
    private BigDecimal abatement = new BigDecimal(0);

    //input format is 2014.3.2 1000 200
    public Coupon(String input) throws ParseException {
        if (input == null) return;
        input = input.replace(" ", ":");
        String[] info = input.split(":");
        effectiveDate = CommonTools.formatDate(info[0]);
        quota = new BigDecimal(info[1]);
        abatement = new BigDecimal(info[2]);
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public BigDecimal getAbatement() {
        return abatement;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
