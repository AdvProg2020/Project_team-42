package Model.Requests;

import Model.Accounts.ManagerAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReferenceArray;

public abstract class Request {
    private static ArrayList<Request> unAnsweredRequests = new ArrayList<>();
    private static ArrayList<Request> answeredRequests = new ArrayList<>();
    protected int requestId;
    protected GregorianCalendar requestDate;
    protected GregorianCalendar answerDate;
    protected String responsiveManager;
    protected RequestState requestState;

    public Request () {
        this.requestId = Request.unAnsweredRequests.size() + Request.answeredRequests.size() + 1;
        this.requestDate = new GregorianCalendar();
        this.requestState = RequestState.IS_ANSWERING;
        Request.unAnsweredRequests.add(this);

        try {
            updateResources();
        } catch (IOException ignored) {}
    }
    
     public static StringBuilder showAllRequestsMoudel(){
        StringBuilder allRequests = new StringBuilder();
        int size = unAnsweredRequests.size();
        if (size==0)
            allRequests.append("there is no unanswered request"+"\n");
        else {
            for (int i = 0; i < size; i++) {
                allRequests.append(unAnsweredRequests.get(i).requestState + "   " + unAnsweredRequests.get(i).requestId + "   " + unAnsweredRequests.get(i).requestDate.getTime() + "not answered\n");
            }
        }
        size = answeredRequests.size();
         if (size==0)
             allRequests.append("there is no answered request"+"\n");
         else {
             for (int i = 0; i < size; i++) {
                 allRequests.append(answeredRequests.get(i).requestState + "   " + answeredRequests.get(i).requestId + "   " + answeredRequests.get(i).requestDate.getTime() + "answered\n");
             }
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
        try {
            return ManagerAccount.getManagerByUsername(responsiveManager);
        } catch (Exception e) {
            return null;
        }
    }

    public void setResponsiveManager(ManagerAccount responsiveManager) {
        this.responsiveManager = responsiveManager.getUserName();
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;

        try {
            updateResources();
        } catch (IOException ignored) {}
    }

    public abstract void updateResources() throws IOException;
}
