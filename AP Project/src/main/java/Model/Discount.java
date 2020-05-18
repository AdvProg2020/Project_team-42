package Model;

import Model.Accounts.CustomerAccount;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Discount {
    private String discountCode;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private double discountPercent;
    private int discountAmountLimit;
    private int repeatCountForEachCustomer;
    private HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount;

    public String getDiscountCode() {
        return discountCode;
    }

    public GregorianCalendar getBegin() {
        return begin;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public int getDiscountAmountLimit() {
        return discountAmountLimit;
    }

    public int getRepeatCountForEachCustomer() {
        return repeatCountForEachCustomer;
    }

    public boolean isUsable () {
        return this.begin.getTime().before(new Date()) && this.end.getTime().after(new Date());
    }
}
