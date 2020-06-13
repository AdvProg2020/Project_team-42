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
    
     public static StringBuilder showAllRequestsMoudel(){
        StringBuilder allRequests = new StringBuilder();
        int size = unAnsweredRequests.size();
        for (int i=0 ; i < size ; i++)
        {
            allRequests.append(unAnsweredRequests.get(i).requestState + "   " + unAnsweredRequests.get(i).requestId + "   " + unAnsweredRequests.get(i).requestDate + "not answered\n");
        }
        size = answeredRequests.size();
        for (int i =0 ; i < size ; i++)
        {
            allRequests.append(answeredRequests.get(i).requestState + "   " + answeredRequests.get(i).requestId + "   " + answeredRequests.get(i).requestDate + "answered\n");
        }
        return allRequests;
    }

    public static ArrayList<Request> getUnAnsweredRequests() {
        return unAnsweredRequests;
    }

    public static void setUnAnsweredRequests(ArrayList<Request> unAnsweredRequests) {
        Request.unAnsweredRequests = unAnsweredRequests;
    }

    public static ArrayList<Request> getAnsweredRequests() {
        return answeredRequests;
    }

    public static void setAnsweredRequests(ArrayList<Request> answeredRequests) {
        Request.answeredRequests = answeredRequests;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public GregorianCalendar getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(GregorianCalendar requestDate) {
        this.requestDate = requestDate;
    }

    public GregorianCalendar getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(GregorianCalendar answerDate) {
        this.answerDate = answerDate;
    }

    public ManagerAccount getResponsiveManager() {
        return responsiveManager;
    }

    public void setResponsiveManager(ManagerAccount responsiveManager) {
        this.responsiveManager = responsiveManager;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }
}
