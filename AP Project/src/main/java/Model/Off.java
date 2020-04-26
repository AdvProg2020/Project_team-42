package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Off {
    private long offId;
    private ArrayList<Product> effectingProducts;
    private OffOrProductState state;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private int offPercentage;
}
