package Model.Logs;

import Model.Product;

import java.util.GregorianCalendar;

public class SellLog {
    private long sellLogId;
    private GregorianCalendar date;
    private int receivedMoney;
    private int offPercent;
    private Product soldProduct;
    private String buyerUsername;
    private String state;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverAddress;
}
