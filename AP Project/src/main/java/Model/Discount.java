package Model;

import Model.Accounts.CustomerAccount;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
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
    private HashMap<String, Integer> effectingCustomersAndUsageCount;

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
        HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount = new HashMap<>();
         for (String customer : this.effectingCustomersAndUsageCount.keySet()) {
             try {
                 effectingCustomersAndUsageCount.put(CustomerAccount.getCustomerByUsername(customer),
                         this.effectingCustomersAndUsageCount.get(customer));
             } catch (Exception ignored) {}
         }
        return effectingCustomersAndUsageCount;
    }

    public Discount(String discountCode, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer , HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount) {
        this.discountCode = discountCode;
        this.begin = begin;
        this.end = end;
        this.discountPercent = discountPercent;
        this.discountAmountLimit = discountAmountLimit;
        this.repeatCountForEachCustomer = repeatCountForEachCustomer;
        this.effectingCustomersAndUsageCount = new HashMap<>();
        for (CustomerAccount customerAccount : effectingCustomersAndUsageCount.keySet()) {
            this.effectingCustomersAndUsageCount.put(customerAccount.getUserName(), effectingCustomersAndUsageCount.get(customerAccount));
        }

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setBegin(GregorianCalendar begin) {
        this.begin = begin;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setDiscountAmountLimit(int discountAmountLimit) {
        this.discountAmountLimit = discountAmountLimit;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setRepeatCountForEachCustomer(int repeatCountForEachCustomer) {
        this.repeatCountForEachCustomer = repeatCountForEachCustomer;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Discounts\\" + this.discountCode + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}
