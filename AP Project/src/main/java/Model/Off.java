package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Off {
    private long offId;
    private ArrayList<Product> effectingProducts;
    private OffOrProductState state;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private double offPercentage;

    public Off(long offId, ArrayList<Product> effectingProducts, OffOrProductState state, GregorianCalendar begin, GregorianCalendar end, int offPercentage) {
        this.offId = offId;
        this.effectingProducts = effectingProducts;
        this.state = state;
        this.begin = begin;
        this.end = end;
        this.offPercentage = offPercentage;
    }

    @Override
    public String toString() {
        return "Off{" +
                "offId=" + offId +
                ", effectingProducts=" + effectingProducts +
                ", state=" + state +
                ", begin=" + begin +
                ", end=" + end +
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
        return effectingProducts;
    }

    public void setEffectingProducts(ArrayList<Product> effectingProducts) {
        this.effectingProducts = effectingProducts;
    }

    public OffOrProductState getState() {
        return state;
    }

    public void setState(OffOrProductState state) {
        this.state = state;
    }

    public GregorianCalendar getBegin() {
        return begin;
    }

    public void setBegin(GregorianCalendar begin) {
        this.begin = begin;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public void setOffPercentage(double offPercentage) {
        this.offPercentage = offPercentage;
    }
}
