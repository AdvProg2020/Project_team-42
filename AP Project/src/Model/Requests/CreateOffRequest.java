package Model.Requests;

import Controller.Exceptions;
import Model.Accounts.SellerAccount;
import Model.Off;
import Model.Product;
import Model.Shop;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CreateOffRequest extends Request {
    private String seller;
    private boolean forEdit;
    private Off previous;
    private ArrayList<Integer> effectingProducts;
    private GregorianCalendar start;
    private GregorianCalendar end;
    private double offPercentage;
    
     public CreateOffRequest(boolean forEdit, Off previous, ArrayList<Product> effectingProducts, GregorianCalendar start, GregorianCalendar end, double offPercentage) {
        this.forEdit = forEdit;
        this.previous = previous;
        this.effectingProducts = new ArrayList<>();
         for (Product product : effectingProducts) {
             this.effectingProducts.add((int) product.getProductId());
         }
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
        ArrayList<Product> effectingProducts = new ArrayList<>();
        for (Integer productId : this.effectingProducts) {
            try {
                effectingProducts.add(Shop.getInstance().getProductById(productId));
            } catch (Exceptions.NoProductByThisIdException ignored) {}
        }
        return effectingProducts;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public double getOffPercentage() {
        return offPercentage;
    }

    public GregorianCalendar getBegin() {
        return begin;
    }

    public SellerAccount getSeller() {
        try {
            return SellerAccount.getSellerAccountByUsername(this.seller);
        } catch (Exception e) {
            return null;
        }
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

    /*public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Requests\\CreateOffRequests\\" + this.requestId + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }*/
}
