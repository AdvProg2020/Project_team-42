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

    public boolean isUsable () {
        return (this.state == OffOrProductState.ACCEPTED) && this.begin.getTime().before(new Date()) && this.end.getTime().after(new Date());
    }

    public double getOffPercentage() {
        return offPercentage;
    }
}
