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
}
