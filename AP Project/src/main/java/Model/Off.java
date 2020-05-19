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
}
