package Model.Logs;

import Model.Product;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BuyLog {
    private long buyLogId;
    private GregorianCalendar date;
    private int payedMoney;
    private int discountPercent;
    private ArrayList<Product> boughtProducts;
    private String sellerUsername;
    private String state;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverAddress;

    public long getBuyLogId() {
        return buyLogId;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }
}
