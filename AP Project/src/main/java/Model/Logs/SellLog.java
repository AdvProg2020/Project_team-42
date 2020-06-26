package Model.Logs;

import Controller.Exceptions;
import Model.Product;
import Model.Shop;

import java.util.GregorianCalendar;

public class SellLog {
    private long sellLogId;
    private GregorianCalendar date;
    private int receivedMoney;
    private double offPercent;
    private int soldProduct;
    private int count;
    private String buyerUsername;
    private String state;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverAddress;

    public String getBuyerUsername() {
        return buyerUsername;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "sellLogId=" + sellLogId +
                ", date=" + date.getTime() +
                ", receivedMoney=" + receivedMoney +
                ", offPercent=" + offPercent +
                ", soldProduct=" + soldProduct +
                ", buyerUsername='" + buyerUsername + '\'' +
                ", state='" + state + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                '}';
    }

    public SellLog(long sellLogId, int receivedMoney, double offPercent, Product soldProduct, int count, String buyerUsername,
                   String receiverName, String receiverPhoneNumber, String receiverAddress) {
        this.sellLogId = sellLogId;
        this.receivedMoney = receivedMoney;
        this.offPercent = offPercent;
        this.soldProduct = (int) soldProduct.getProductId();
        this.count = count;
        this.buyerUsername = buyerUsername;
        this.state = "preparing";
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverAddress = receiverAddress;
        this.date = new GregorianCalendar();
    }

    public int getReceivedMoney() {
        return receivedMoney;
    }

    public Product getSoldProductId() {
        try {
            return Shop.getInstance().getProductById(soldProduct);
        } catch (Exceptions.NoProductByThisIdException e) {
            return null;
        }
    }

    public int getCount() {
        return count;
    }
}
