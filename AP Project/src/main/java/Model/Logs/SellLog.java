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
}
