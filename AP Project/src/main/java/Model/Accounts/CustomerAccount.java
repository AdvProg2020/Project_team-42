package Model.Accounts;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccount extends Account {

    private static ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<CustomerAccount>();
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> thisCustomerBuyLogs;
    private HashMap<Integer , String>discountCodeAndUserNumber;

}
