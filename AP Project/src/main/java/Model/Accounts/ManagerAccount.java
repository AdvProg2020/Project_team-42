package Model.Accounts;

import java.util.ArrayList;

public class ManagerAccount extends Account {
   private static ArrayList<ManagerAccount> allManagerAccounts = new ArrayList<ManagerAccount>();
    private boolean isMain;

    public void deleteManagerUser(String username){
        int allAccountsSize = getAllAccounts().size();
        int managerAccountsSize = allManagerAccounts.size();
        for (int i=0;i<managerAccountsSize;i++) {


            if (allManagerAccounts.get(i).getUserName() . equals(username)){
                if (isMain) {
                    allManagerAccounts.get(i).setPassword("23");
                    for (int j=0 ;j<allAccountsSize;j++){
                        if (getAllAccounts().get(i).getUserName() . equals(username)){
                           getAllAccounts().get(i).setPassword("23");
                            break;
                        }

                    }
                }
            }


        }
    }
}
