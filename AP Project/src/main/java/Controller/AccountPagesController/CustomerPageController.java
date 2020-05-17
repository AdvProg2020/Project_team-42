package Controller.AccountPagesController;

import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Discount;
import Model.Logs.BuyLog;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerPageController extends AccountPageController {
    private static CustomerPageController customerPageController = new CustomerPageController();
    private CustomerAccount user;

    public void setUser(CustomerAccount user) {
        this.user = user;
        AccountPageController.user = user;
    }

    private CustomerPageController () {}

    public static CustomerPageController getInstance() {
        return customerPageController;
    }

    public ArrayList<BuyLog> getOrders () throws Exceptions.NoOrderException {
        ArrayList<BuyLog> buyLogs;
        if ((buyLogs = this.user.getBuyLogs()).isEmpty())
            throw new Exceptions.NoOrderException();
        return buyLogs;
    }

    public double getBalance () {
        return this.user.getCredit();
    }

    public HashMap<Discount, Integer> getDiscountCodesAndUseCounts () throws Exceptions.NoDiscountCodeException {
        HashMap<Discount, Integer> discountsAndUseCount;
        if ((discountsAndUseCount = this.user.getDiscountCodeAndUseCount()).isEmpty())
            throw new Exceptions.NoDiscountCodeException();
        return discountsAndUseCount;
    }
}
