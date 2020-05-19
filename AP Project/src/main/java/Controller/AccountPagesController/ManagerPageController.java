package Controller.AccountPagesController;

public class ManagerPageController extends AccountPageController {
    boolean hasManagerPageRegistered;

    public StringBuilder getAllAccountsController() {
    return user.getAllAccountsMoudel();
    }

    public String getAccountByUserNameController(String userName){
        return user.getAccountByUserNameMoudel(userName);
    }

    public StringBuilder getAllEmailsController(String userName){
        return user.getAllEmails();
    }

    public StringBuilder getAllPhoneNumbersController(String userName){
        return user.getAllPhoneNumbers();
    }

    public void deleteUserController(String userName){

            user.deleteUserMoudel(userName);
            deleteManagerUser

    }
}
