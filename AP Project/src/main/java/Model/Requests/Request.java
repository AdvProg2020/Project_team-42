package Model.Requests;

import Model.Accounts.ManagerAccount;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Request {
    private static ArrayList<Request> unAnsweredRequests;
    private static ArrayList<Request> answeredRequests;
    private int requestId;
    private GregorianCalendar requestDate;
    private GregorianCalendar answerDate;
    private ManagerAccount responsiveManager;
    private RequestState requestState;

    public Request () {
        this.requestId = Request.unAnsweredRequests.size() + 1;
        this.requestDate = new GregorianCalendar();
        this.requestState = RequestState.IS_ANSWERING;
        Request.unAnsweredRequests.add(this);
    }
}
