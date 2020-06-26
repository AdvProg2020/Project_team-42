package Model;

import Controller.Exceptions;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Off {
    private long offId;
    private ArrayList<Integer> effectingProducts;
    private OffOrProductState state;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private double offPercentage;

    public Off(long offId, ArrayList<Product> effectingProducts, OffOrProductState state, GregorianCalendar begin, GregorianCalendar end, int offPercentage) {
        this.offId = offId;
        this.effectingProducts = new ArrayList<>();
        for (Product product : effectingProducts) {
            this.effectingProducts.add((int) product.getProductId());
        }
        this.state = state;
        this.begin = begin;
        this.end = end;
        this.offPercentage = offPercentage;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    @Override
    public String toString() {
        return "Off{" +
                "offId=" + offId +
                ", effectingProducts=" + effectingProducts +
                ", state=" + state +
                ", begin=" + begin.getTime() +
                ", end=" + end.getTime() +
                ", offPercentage=" + offPercentage +
                '}';
    }

    public boolean isUsable () {
        return (this.state == OffOrProductState.ACCEPTED) && this.begin.getTime().before(new Date()) && this.end.getTime().after(new Date());
    }

    public double getOffPercentage() {
        return offPercentage;
    }
    
     public long getOffId() {
        return offId;
    }

    public void setOffId(long offId) {
        this.offId = offId;
    }

    public ArrayList<Product> getEffectingProducts() {
        ArrayList<Product> effectingProducts = new ArrayList<>();
        for (Integer productId : this.effectingProducts) {
            try {
                effectingProducts.add(Shop.getInstance().getProductById(productId));
            } catch (Exceptions.NoProductByThisIdException e) {}
        }

        return effectingProducts;
    }

    public void setEffectingProducts(ArrayList<Product> effectingProducts) {
        this.effectingProducts = new ArrayList<>();
        for (Product product : effectingProducts) {
            this.effectingProducts.add((int) product.getProductId());
        }

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public OffOrProductState getState() {
        return state;
    }

    public void setState(OffOrProductState state) {
        this.state = state;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public GregorianCalendar getBegin() {
        return begin;
    }

    public void setBegin(GregorianCalendar begin) {
        this.begin = begin;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void setOffPercentage(double offPercentage) {
        this.offPercentage = offPercentage;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public void updateResources () throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Offs\\" + this.offId + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }

    public boolean hasProductById (int id) {
        return this.effectingProducts.contains(id);
    }

    public void changeWaitingState(){
        this.state = OffOrProductState.WAITING_FOR_EDIT_ANSWER;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }
}
