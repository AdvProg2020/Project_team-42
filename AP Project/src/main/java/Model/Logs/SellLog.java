package Model.Logs;

import Model.Product;

import java.util.GregorianCalendar;

public class SellLog {
    private long sellLogId;
    private GregorianCalendar date;
    private int receivedMoney;
    private double offPercent;
    private Product soldProduct;
    private int count;
    private String buyerUsername;
    private String state;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverAddress;


    @Override
    public String toString() {
        return "SellLog{" +
                "sellLogId=" + sellLogId +
                ", date=" + date +
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
        this.soldProduct = soldProduct;
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

    public Product getSoldProduct() {
        return soldProduct;
    }

    public int getCount() {
        return count;
    }
}
