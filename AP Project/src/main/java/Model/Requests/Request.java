package Model.Requests;

import java.util.ArrayList;
import java.util.Date;

public class Request {
    ArrayList<Request> unAnsweredRequests;
    ArrayList<Request> answeredRequests;
    int requestId;
    Date requestDate;
    Date answerDate;
    Manager responsiveManager;
    RequestState requestState;

    public Request(ArrayList<Request> unAnsweredRequests, ArrayList<Request> answeredRequests, int requestId, Date requestDate, Date answerDate, Manager responsiveManager, RequestState requestState) {
        this.unAnsweredRequests = unAnsweredRequests;
        this.answeredRequests = answeredRequests;
        this.requestId = requestId;
        this.requestDate = requestDate;
        this.answerDate = answerDate;
        this.responsiveManager = responsiveManager;
        this.requestState = requestState;
    }

    public ArrayList<Request> getUnAnsweredRequests() {
        return unAnsweredRequests;
    }

    public void setUnAnsweredRequests(ArrayList<Request> unAnsweredRequests) {
        this.unAnsweredRequests = unAnsweredRequests;
    }

    public ArrayList<Request> getAnsweredRequests() {
        return answeredRequests;
    }

    public void setAnsweredRequests(ArrayList<Request> answeredRequests) {
        this.answeredRequests = answeredRequests;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Manager getResponsiveManager() {
        return responsiveManager;
    }

    public void setResponsiveManager(Manager responsiveManager) {
        this.responsiveManager = responsiveManager;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }
}
