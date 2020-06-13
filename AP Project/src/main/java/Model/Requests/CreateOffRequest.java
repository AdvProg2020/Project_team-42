package Model.Requests;

import Model.Off;
import Model.Product;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CreateOffRequest extends Request {
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
}
