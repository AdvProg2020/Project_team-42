package Model.Requests;
import java.util.ArrayList;
import java.util.Date;

public class CrerateOffRequest extends Request {

    public CrerateOffRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
    }

    boolean forEdit;
    Off privious;
    ArrayList<Product> effectingProducts;
    Date end;
    int offPercentage;

    public CrerateOffRequest(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState, boolean forEdit, Off privious, ArrayList<Product> effectingProducts, Date end, int offPercentage) {
        super(unAnsweredRequests, answeredRequests, requestId, requestDate, answerDate, responsiveManager, requestState);
        this.forEdit = forEdit;
        this.privious = privious;
        this.effectingProducts = effectingProducts;
        this.end = end;
        this.offPercentage = offPercentage;
    }

    public boolean isForEdit() {
        return forEdit;
    }

    public void setForEdit(boolean forEdit) {
        this.forEdit = forEdit;
    }

    public Off getPrivious() {
        return privious;
    }

    public void setPrivious(Off privious) {
        this.privious = privious;
    }

    public ArrayList<Product> getEffectingProducts() {
        return effectingProducts;
    }

    public void setEffectingProducts(ArrayList<Product> effectingProducts) {
        this.effectingProducts = effectingProducts;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getOffPercentage() {
        return offPercentage;
    }

    public void setOffPercentage(int offPercentage) {
        this.offPercentage = offPercentage;
    }
}
