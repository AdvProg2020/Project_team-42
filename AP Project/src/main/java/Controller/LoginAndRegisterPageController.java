package Controller;

import Model.*;
import Model.Accounts.Account;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import Model.Requests.*;
import View.Pages.LoginRegisterPage;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Model.Accounts.Account.getAllAccounts;

public class LoginAndRegisterPageController {
    private static LoginAndRegisterPageController controller = new LoginAndRegisterPageController();
    //private Shop Shop.getInstance() = Shop.getInstance();

    public static LoginAndRegisterPageController getInstance() {
        return controller;
    }

    private LoginAndRegisterPageController() {
    }

    public void createCustomerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType) throws Exception {
        Shop.getInstance().createCustomerAccountMoudel(userName, firstName, lastName, email, phoneNumber, password, accountType);
    }

    public void createRegisterRequestSellerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password, String accountType, String companyOrWorkShopName) throws Exception {
        for (int i = 0; i < getAllAccounts().size(); i++) {
            if (getAllAccounts().get(i).getUserName().equals(userName))
                throw new Exception("username is used");
        }
        AddSellerAccountRequest request = new AddSellerAccountRequest(userName, firstName, lastName, email, phoneNumber, password, accountType, companyOrWorkShopName);
        try {
            request.updateResources();
        } catch (IOException ignored) {
        }
    }

    public CustomerAccount loginCustomerAccountController(String username, String password) throws Exception {
        return Shop.getInstance().loginCustomerMoudel(username, password);
    }

    public SellerAccount loginSellerAccountController(String username, String password) throws Exception {
        return Shop.getInstance().loginSellerMoudel(username, password);
    }

    public ManagerAccount loginManagerAccountController(String username, String password) throws Exception {
        return Shop.getInstance().loginManagerMoudel(username, password);
    }

    public void CreateFirstManagerAccountController(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                                                    String accountType) throws Exception {
        Shop.getInstance().createFirstManagerAccountMoudel(userName, firstName, lastName, email, phoneNumber, password, "manager");
    }

    public void parseResources() throws FileNotFoundException {
        HashMap<Product, Integer> allProductsAndCount = new HashMap<>();
        HashMap<Product, Integer> allProductsOnOffAndCount = new HashMap<>();
        ArrayList<Category> categories = new ArrayList<>();

        Gson gson = new Gson();
        File file = new File("src\\main\\resources\\Products");
        String[] files;
        if ((files = file.list()) != null) {
            for (String productSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Products\\" + productSource));

                Shop.getInstance().getAllProducts().add(gson.fromJson(reader, Product.class));
            }
        }

        file = new File("src\\main\\resources\\Categories");
        if ((files = file.list()) != null) {
            for (String categorySource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Categories\\" + categorySource));


                categories.add(gson.fromJson(reader, Category.class));
            }
        }

        file = new File("src\\main\\resources\\Discounts");
        if ((files = file.list()) != null) {
            for (String discountSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Discounts\\" + discountSource));

                Shop.getInstance().getAllDiscounts().add(gson.fromJson(reader, Discount.class));
            }
        }

        file = new File("src\\main\\resources\\Offs");
        if ((files = file.list()) != null) {
            for (String offSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Offs\\" + offSource));

                Shop.getInstance().getAllOffs().add(gson.fromJson(reader, Off.class));
            }
            for (Off off : Shop.getInstance().getAllOffs()) {
                for (Product product : Shop.getInstance().getAllProducts()) {
                    if (off.hasProductById((int) product.getProductId()))
                        allProductsOnOffAndCount.put(product, 0);
                }
            }
        }

        file = new File("src\\main\\resources\\Requests\\AddProductRequests");
        if ((files = file.list()) != null) {
            for (String requestSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Requests\\AddProductRequests\\" + requestSource));

                Request request = gson.fromJson(reader, AddProductRequest.class);
                if (request.getRequestState().equals(RequestState.IS_ANSWERING))
                    Request.getUnAnsweredRequests().add(request);
                else
                    Request.getAnsweredRequests().add(request);
            }
        }

        file = new File("src\\main\\resources\\Requests\\AddSellerAccountRequests");
        if ((files = file.list()) != null) {
            for (String requestSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Requests\\AddSellerAccountRequests\\" + requestSource));

                Request request = gson.fromJson(reader, AddSellerAccountRequest.class);
                if (request.getRequestState().equals(RequestState.IS_ANSWERING))
                    Request.getUnAnsweredRequests().add(request);
                else
                    Request.getAnsweredRequests().add(request);
            }
        }

        file = new File("src\\main\\resources\\Requests\\CommentRequests");
        if ((files = file.list()) != null) {
            for (String requestSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Requests\\CommentRequests\\" + requestSource));

                Request request = gson.fromJson(reader, CommentRequest.class);
                if (request.getRequestState().equals(RequestState.IS_ANSWERING))
                    Request.getUnAnsweredRequests().add(request);
                else
                    Request.getAnsweredRequests().add(request);
            }
        }

        file = new File("src\\main\\resources\\Requests\\CreateOffRequests");
        if ((files = file.list()) != null) {
            for (String requestSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Requests\\CreateOffRequests\\" + requestSource));

                Request request = gson.fromJson(reader, CommentRequest.class);
                if (request.getRequestState().equals(RequestState.IS_ANSWERING))
                    Request.getUnAnsweredRequests().add(request);
                else
                    Request.getAnsweredRequests().add(request);
            }
        }

        file = new File("src\\main\\resources\\Accounts\\CustomerAccounts");
        if ((files = file.list()) != null) {
            for (String accountSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Accounts\\CustomerAccounts\\" + accountSource));

                CustomerAccount account;

                CustomerAccount.getAllCustomerAccounts().add(account = gson.fromJson(reader, CustomerAccount.class));
                Account.getAllAccounts().add(account);
                Shop.getInstance().getAllBuyLogs().addAll(account.getBuyLogs());
            }
        }

        file = new File("src\\main\\resources\\Accounts\\SellerAccounts");
        if ((files = file.list()) != null) {
            for (String accountSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Accounts\\SellerAccounts\\" + accountSource));

                SellerAccount account;

                SellerAccount.getAllSellerAccounts().add(account = gson.fromJson(reader, SellerAccount.class));
                Account.getAllAccounts().add(account);
                if (account.getThisSellerAllSellLogs() == null)
                    account.newThisSellerAllSellLogs();
                if (account.getSellableProductAndCounts() == null)
                    account.newSellableProductAndCount();
                if (account.getRequests() == null)
                    account.newRequests();
                if (account.getOffs() == null)
                    account.newOff();

                /*for (Product product : account.getSellableProductAndCounts().keySet()) {
                    allProductsAndCount.replace(product, allProductsAndCount.get(product) + account.getCountOfProduct(product));
                    if (allProductsOnOffAndCount.containsKey(product))
                        allProductsOnOffAndCount.replace(product, allProductsOnOffAndCount.get(product) + account.getCountOfProduct(product));
                }*/
            }
        }

        file = new File("src\\main\\resources\\Accounts\\ManagerAccounts");
        if ((files = file.list()) != null) {
            LoginRegisterPage.isMainManagerJoined = files.length != 0;
            for (String accountSource : files) {
                Reader reader = new BufferedReader(new FileReader("src\\main\\resources\\Accounts\\ManagerAccounts\\" + accountSource));

                ManagerAccount account;

                ManagerAccount.getAllManagerAccounts().add(account = gson.fromJson(reader, ManagerAccount.class));
                Account.getAllAccounts().add(account);
            }
        }

        Shop.getInstance().setAllCategories(categories);
        Shop.getInstance().setAllProductOnOffsAndCount(allProductsOnOffAndCount);
    }
}
 