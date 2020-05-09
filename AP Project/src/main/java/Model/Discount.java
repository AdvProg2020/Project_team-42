package Model;

import Model.Accounts.CustomerAccount;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Discount {
    private String discountCode;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private int discountPercent;
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

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getDiscountAmountLimit() {
        return discountAmountLimit;
    }

    public int getRepeatCountForEachCustomer() {
        return repeatCountForEachCustomer;
    }
}
