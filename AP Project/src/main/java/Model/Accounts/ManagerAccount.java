package Model.Accounts;

import Model.*;
import Model.Requests.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ManagerAccount extends Account {
    private static ArrayList<ManagerAccount> allManagerAccounts = new ArrayList<>();
    private boolean isMain;

    public ManagerAccount(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                          String accountType, boolean isMain) {
        super(userName, firstName, lastName, email, phoneNumber, password, accountType);
    }

    private Shop shop = Shop.getInstance();

    public void checkUsernameIsUsedMoudel(String username) throws Exception {
        for (int i = 0; i < allAccounts.size() - 1; i++) {
            if (allAccounts.get(i).getUserName().equals(username))
                throw new Exception("username is used please enter another username");
        }
    }

    public static ManagerAccount getManagerByUsername(String username) throws Exception {
        for (ManagerAccount managerAccount : allManagerAccounts) {
            if (managerAccount.getUserName().equalsIgnoreCase(username))
                return managerAccount;
        }
        throw new Exception();
    }

    public void deleteManagerUser(String username) {
        int allAccountsSize = getAllAccounts().size();
        for (int i = 0; i < allAccountsSize; i++) {
            if (getAllAccounts().get(i).getUserName().equals(username)) {
                getAllAccounts().get(i).setPassword("23");
                break;
            }
        }
        int managerAccountsSize = allManagerAccounts.size();
        for (int i = 0; i < managerAccountsSize; i++) {


            if (allManagerAccounts.get(i).getUserName().equals(username)) {
                if (isMain) {
                    allManagerAccounts.get(i).setPassword("23");
                    break;
                }
            }
        }
        int customerAccountsSize = CustomerAccount.getAllCustomerAccounts().size();
        for (int i = 0; i < customerAccountsSize; i++) {

            if (CustomerAccount.getAllCustomerAccounts().get(i).getUserName().equals(username)) {
                CustomerAccount.getAllCustomerAccounts().get(i).setPassword("23");
                break;
            }
        }

        int sellerAccountsSize = SellerAccount.getAllSellerAccounts().size();
        for (int i = 0; i < sellerAccountsSize; i++) {

            if (SellerAccount.getAllSellerAccounts().get(i).getUserName().equals(username)) {
                SellerAccount.getAllSellerAccounts().get(i).setPassword("23");
                break;
            }
        }
    }

    public void answerRequest(int id, String state) throws Exception {
        int i;
        for (i = 0; i < Request.getUnAnsweredRequests().size(); i++) {
            if (Request.getUnAnsweredRequests().get(i).getRequestId() == id)
                break;
            if (i == Request.getUnAnsweredRequests().size() - 1)
                throw new Exception("request not found");

        }
        if (state.equals("decline")) {
            Request.getUnAnsweredRequests().get(i).setAnswerDate(new GregorianCalendar());
            Request.getUnAnsweredRequests().get(i).setRequestState(RequestState.DECLINED);
            Request.getUnAnsweredRequests().add(Request.getUnAnsweredRequests().get(i));
            Request.getUnAnsweredRequests().remove(Request.getUnAnsweredRequests().get(i));
            return;
        }
        if (state.equals("no answer for now"))
            return;
        if (state.equals("accept")) {
            if (Request.getUnAnsweredRequests().get(i).getClass().equals(AddSellerAccountRequest.class)) {
                AddSellerAccountRequest request = (AddSellerAccountRequest) Request.getUnAnsweredRequests().get(i);
                SellerAccount sellerAccount = new SellerAccount(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), request.getPassword(), request.getAccountType(), request.getCompanyOrWorkshopName());
                SellerAccount.getAllSellerAccounts().add(sellerAccount);
                Account.getAllAccounts().add(sellerAccount);
                Request.getUnAnsweredRequests().get(i).setAnswerDate(new GregorianCalendar());
                Request.getUnAnsweredRequests().get(i).setRequestState(RequestState.ACCEPTED);
                Request.getAnsweredRequests().add(Request.getUnAnsweredRequests().get(i));
                Request.getUnAnsweredRequests().remove(Request.getUnAnsweredRequests().get(i));
            } else if (Request.getUnAnsweredRequests().get(i).getClass().equals(AddProductRequest.class)) {
                AddProductRequest request = (AddProductRequest) Request.getUnAnsweredRequests().get(i);
                if (!request.isForEdit()) {
                    if (request.isNewProduct()) {
                        Product product = new Product(shop.returnNewId(), request.getName(), request.getBrand(), request.getPrice(), request.getCategory(), request.getAttribute(), request.getDescription(), request.getSeller());
                        shop.getAllProductAndCount().put(product , request.getCount());
                        request.getSeller().addToSellableProducts((int) product.getProductId(), request.getCount());
                        request.setAnswerDate(new GregorianCalendar());
                        request.setRequestState(RequestState.ACCEPTED);
                        Request.getAnsweredRequests().add(Request.getUnAnsweredRequests().get(i));
                        Request.getUnAnsweredRequests().remove(Request.getUnAnsweredRequests().get(i));
                    } else {
                        Product existProduct = null;
                        long existId;
                        for (Product product : shop.getAllProductAndCount().keySet()) {
                            if (product.getName().equals(request.getName())) {
                                existId = product.getProductId();
                                existProduct = product;
                            }
                        }
                        int count = shop.getAllProductAndCount().get(existProduct);
                        count = count + request.getCount();
                        shop.getAllProductAndCount().replace(existProduct, count);
                        ((AddProductRequest) Request.getUnAnsweredRequests().get(i)).getSeller().addToSellableProducts((int) existProduct.getProductId(), request.getCount());
                        Request.getUnAnsweredRequests().get(i).setAnswerDate(new GregorianCalendar());
                        Request.getUnAnsweredRequests().get(i).setRequestState(RequestState.ACCEPTED);
                        Request.getAnsweredRequests().add(Request.getUnAnsweredRequests().get(i));
                        Request.getUnAnsweredRequests().remove(Request.getUnAnsweredRequests().get(i));
                    }
                } else {
                    Product product = shop.getProductByIdd(request.getProductId());
                    product.setName(request.getName());
                    product.setCategory(request.getCategory());
                    product.setAttribute(request.getAttribute());
                    product.setBrand(request.getBrand());
                    product.setDescription(request.getDescription());
                    product.setPrice(request.getPrice());
                    shop.getAllProductOnOffsAndCount().replace(product, shop.getAllProductAndCount().get(product), request.getCount());

                }
            } else if (Request.getUnAnsweredRequests().get(i).getClass().equals(CommentRequest.class)) {
                CommentRequest request = (CommentRequest) Request.getUnAnsweredRequests().get(i);
                request.getProduct().getComments().add(new Comment(request.getUser(), request.getProduct(), request.getTitle(), request.getContent(), request.isProductBoughtByUser()));
                Request.getUnAnsweredRequests().get(i).setAnswerDate(new GregorianCalendar());
                Request.getUnAnsweredRequests().get(i).setRequestState(RequestState.ACCEPTED);
                Request.getAnsweredRequests().add(Request.getUnAnsweredRequests().get(i));
                Request.getUnAnsweredRequests().remove(Request.getUnAnsweredRequests().get(i));
            } else if (Request.getUnAnsweredRequests().get(i).getClass().equals(CreateOffRequest.class)) {
                CreateOffRequest request = (CreateOffRequest) Request.getUnAnsweredRequests().get(i);
                if (request.isForEdit()) {
                    request.getPrevious().setBegin(request.getBegin());
                    request.getPrevious().setEnd(request.getEnd());
                    request.getPrevious().setEffectingProducts(request.getEffectingProducts());
                    request.getPrevious().setOffPercentage(request.getOffPercentage());
                    request.getPrevious().setState(OffOrProductState.ACCEPTED);
                } else {
                    Off off;
                    shop.getAllOffs().add(off = new Off(shop.getAllOffs().size(), request.getEffectingProducts(), OffOrProductState.ACCEPTED, request.getBegin(), request.getEnd(), (int) request.getOffPercentage()));
                    request.getSeller().getOffs().add((int) off.getOffId());
                }
            }
        }
    }

    public void createManagerAccountMouddel(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                                            String accountType, boolean isMain) {
        ManagerAccount account;
        allAccounts.add(account = new ManagerAccount(userName, firstName, lastName, email, phoneNumber, password, accountType, isMain));
        allManagerAccounts.add(account);
        try {
            account.updateResources();
        } catch (IOException ignored) {
        }
    }

    public void createDiscountCodeMoudel(String discountCode, GregorianCalendar begin, GregorianCalendar end, double discountPercent, int discountAmountLimit, int repeatCountForEachCustomer, HashMap<CustomerAccount, Integer> effectingCustomersAndUsageCount) {
        shop.getAllDiscounts().add(new Discount(discountCode, begin, end, discountPercent, discountAmountLimit, repeatCountForEachCustomer, effectingCustomersAndUsageCount));
    }

    public void checkValidationOfDiscountCodeMoudel(String code) throws Exception {
        for (int i = 0; i < shop.getAllDiscounts().size(); i++) {
            if (shop.getAllDiscounts().get(i).getDiscountCode().equals(code))
                throw new Exception("code is used");
        }


    }

    public CustomerAccount getCustomerAccountAccountTypeByUserNameMoudel(String username) throws Exception {
        int asize = CustomerAccount.getAllCustomerAccounts().size();
        int i = 0;
        for (; i < asize; i++) {
            if (CustomerAccount.getAllCustomerAccounts().get(i).userName.equals(username)) {
                return CustomerAccount.getAllCustomerAccounts().get(i);

            }
        }
        throw new Exception("not found");
    }

    public String requestDetail(int id) throws Exception {
        for (int i = 0; i < Request.getUnAnsweredRequests().size(); i++) {
            if (Request.getUnAnsweredRequests().get(i).getRequestId() == id) {
                if (Request.getUnAnsweredRequests().get(i).getClass().equals(AddSellerAccountRequest.class))
                    return String.valueOf(Request.getUnAnsweredRequests().get(i));

                if (Request.getUnAnsweredRequests().get(i).getClass().equals(AddProductRequest.class))
                    return String.valueOf(Request.getUnAnsweredRequests().get(i));

                if (Request.getUnAnsweredRequests().get(i).getClass().equals(CommentRequest.class))
                    return String.valueOf(Request.getUnAnsweredRequests().get(i));

                if (Request.getUnAnsweredRequests().get(i).getClass().equals(CreateOffRequest.class))
                    return String.valueOf(Request.getUnAnsweredRequests().get(i));
            }
        }

        for (int i = 0; i < Request.getAnsweredRequests().size(); i++) {
            if (Request.getAnsweredRequests().get(i).getRequestId() == id) {
                if (Request.getAnsweredRequests().get(i).getClass().equals(AddSellerAccountRequest.class))
                    return String.valueOf(Request.getAnsweredRequests().get(i));

                if (Request.getAnsweredRequests().get(i).getClass().equals(AddProductRequest.class))
                    return String.valueOf(Request.getAnsweredRequests().get(i));

                if (Request.getAnsweredRequests().get(i).getClass().equals(CommentRequest.class))
                    return String.valueOf(Request.getAnsweredRequests().get(i));

                if (Request.getAnsweredRequests().get(i).getClass().equals(CreateOffRequest.class))
                    return String.valueOf(Request.getAnsweredRequests().get(i));
            }
        }

        throw new Exception("not found");

    }

    public static ArrayList<ManagerAccount> getAllManagerAccounts() {
        return allManagerAccounts;
    }

    public void updateResources() throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Accounts\\ManagerAccounts\\" + this.userName + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}
       
       
