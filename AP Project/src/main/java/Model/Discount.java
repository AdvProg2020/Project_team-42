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
    
     public HashMap<CustomerAccount, Integer> getEffectingCustomersAndUsageCount() {
        return effectingCustomersAndUsageCount;
    }

    public Discount(String discountCode, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer , HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount) {
        this.discountCode = discountCode;
        this.begin = begin;
        this.end = end;
        this.discountPercent = discountPercent;
        this.discountAmountLimit = discountAmountLimit;
        this.repeatCountForEachCustomer = repeatCountForEachCustomer;
        this.effectingCustomersAndUsageCount = effectingCustomersAndUsageCount;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setBegin(GregorianCalendar begin) {
        this.begin = begin;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setDiscountAmountLimit(int discountAmountLimit) {
        this.discountAmountLimit = discountAmountLimit;
    }

    public void setRepeatCountForEachCustomer(int repeatCountForEachCustomer) {
        this.repeatCountForEachCustomer = repeatCountForEachCustomer;
    }
}
