package Model.Requests;

import Model.Off;
import Model.Product;
import Model.Accounts.SellerAccount;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CreateOffRequest extends Request {
        private SellerAccount seller;
    private boolean forEdit;
    private Off previous;
    private ArrayList<Product> effectingProducts;
    private GregorianCalendar end;
    private int offPercentage;
    
     public CreateOffRequest(boolean forEdit, Off previous, ArrayList<Product> effectingProducts, GregorianCalendar start, GregorianCalendar end, double offPercentage) {
        this.forEdit = forEdit;
        this.previous = previous;
        this.effectingProducts = effectingProducts;
        this.start = start;
        this.end = end;
        this.offPercentage = offPercentage;
    }
    private GregorianCalendar begin;

    public boolean isForEdit() {
        return forEdit;
    }

    public Off getPrevious() {
        return previous;
    }

    public ArrayList<Product> getEffectingProducts() {
        return effectingProducts;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public int getOffPercentage() {
        return offPercentage;
    }

    public GregorianCalendar getBegin() {
        return begin;
    }

    public SellerAccount getSeller() {
        return seller;
    }

    @Override
    public String toString() {
        return "CreateOffRequest{" +
                "seller=" + seller +
                ", forEdit=" + forEdit +
                ", previous=" + previous +
                ", effectingProducts=" + effectingProducts +
                ", end=" + end +
                ", offPercentage=" + offPercentage +
                ", begin=" + begin +
                '}';
    }
}
