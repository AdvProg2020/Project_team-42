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
}
