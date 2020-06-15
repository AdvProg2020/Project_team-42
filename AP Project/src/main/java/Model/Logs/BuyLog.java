package Model.Logs;

import Model.Accounts.SellerAccount;
import Model.Product;
import Model.Shop;

import java.util.*;

public class BuyLog {
    private long buyLogId;
    private GregorianCalendar date;
    private int payedMoney;
    private int discountAmount;
    private HashMap<Integer, HashMap<String, Integer>> boughtProducts;
    private String state;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverAddress;

    public BuyLog(long buyLogId, int payedMoney, int discountAmount, HashMap<Integer, HashMap<String, Integer>> boughtProducts,
                  String receiverName, String receiverPhoneNumber, String receiverAddress) {
        this.buyLogId = buyLogId;
        this.payedMoney = payedMoney;
        this.discountAmount = discountAmount;
        this.boughtProducts = boughtProducts;
        this.state = "preparing";
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverAddress = receiverAddress;
        this.date = new GregorianCalendar();
    }

    public int getPayedMoney() {
        return payedMoney;
    }

    public long getBuyLogId() {
        return buyLogId;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Product> getBoughtProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (Integer productId : boughtProducts.keySet()) {
            try {
                products.add(Shop.getInstance().getProductByIdd(productId));
            } catch (Exception e) {}
        }
        return products;
    }

    @Override
    public String toString() {
        return "buyLogId : " + buyLogId +
                "\ndate and time : " + date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + ":" + date.get(Calendar.DAY_OF_MONTH) +
                " " + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND) +
                "\npayedMoney : " + payedMoney +
                "\ndiscountAmount : " + discountAmount +
                "\nboughtProducts : " + boughtProducts +
                "\nstate : " + state +
                "\nreceiverName : " + receiverName +
                "\nreceiverPhoneNumber : " + receiverPhoneNumber +
                "\nreceiverAddress : " + receiverAddress;
    }
}
