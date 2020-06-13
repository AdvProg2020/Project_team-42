package View.Pages.AccountsPage;

import View.Page;
import Controller.AccountPagesController.ManagerPageController;
import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Category;
import Model.Product;
import View.Commands;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ManagerPage extends Page {
    private static ManagerPage managerPage = new ManagerPage();

    private ManagerPage(){

    }
    ManagerPageController mController = ManagerPageController.getInstance();
    public static ManagerPage getInstance() {
        return managerPage;
    }

    public Page run() {
        
        return null;

        while(true)
        {
            Matcher matcher;
            String command = scanner.nextLine();
            if (Commands.MANAGE_USERS.getMatcher(command).matches())
            {
                System.out.println(mController.getAllAccountsController());
                while(true)
                {

                    command = scanner.nextLine();
                    if ((matcher=Commands.VIEW.getMatcher(command)).matches())
                    {
                        String username = matcher.group(1);
                        System.out.println(mController.getAccountByUserNameController(username));
                    }
                    if ((matcher=Commands.DELETE_USER.getMatcher(command)).matches())
                    {
                        String username = matcher.group(1);
                        mController.deleteUserController(username);
                    }
                    if ((matcher=Commands.CREATE_MANAGER_PROFILE.getMatcher(command)).matches())
                    {
                        System.out.println("please enter your username");
                        String useru = scanner.nextLine();
                        try {
                            mController.checkUsernameIsUsedController(useru);
                        } catch (Exception e) {
                            System.out.println("username is used please enter another one");
                             useru = scanner.nextLine();
                            try {
                                mController.checkUsernameIsUsedController(useru);
                            } catch (Exception exception) {
                                System.out.println("username is used please enter another one");
                                useru = scanner.nextLine();
                                try {
                                    mController.checkUsernameIsUsedController(useru);
                                } catch (Exception ex) {
                                    System.out.println("username is used please try again");
                                    break;
                                }
                            }
                        }
                        System.out.println("please enter your firstname");
                        String userfirstname = scanner.nextLine();
                        System.out.println("please enter your lastname");
                        String userlastname = scanner.nextLine();
                        System.out.println("please enter your email");
                        String useremail = scanner.nextLine();
                        System.out.println("please enter your phone number");
                        String userphoneNumber = scanner.nextLine();
                        System.out.println("please enter your password it should be over 4 characters");
                        String userpassword = scanner.nextLine();
                        while (userpassword.length()<5)
                        {
                            System.out.println("please enter another password it should be over 4 characters");
                            userpassword = scanner.nextLine();
                        }
                        mController.createManagerAccountController(useru,userfirstname,userlastname,useremail,userphoneNumber,userpassword,"manager",false);
                    }
                    if (Commands.BACK.getMatcher(command).matches())
                        break;
                }
                if(Commands.MANAGE_ALL_PRODUCTS.getMatcher(command).matches())
                {
                    while (true)
                    {
                        System.out.println(mController.showAllProductsController());
                        command=scanner.nextLine();
                        if ((matcher=Commands.REMOVE_PRODUCT.getMatcher(command)).matches()) {
                            try {
                                mController.deleteProductController(Long.parseLong(matcher.group(1)));
                            } catch (Exceptions.NoProductByThisIdException e) {
                                System.out.println("invalid id");
                            }
                        }
                        if (Commands.BACK.getMatcher(command).matches())
                            break;

                    }
                }
                if (Commands.CREATE_DISCOUNT_CODE.getMatcher(command).matches())
                {
                    System.out.println(mController.showAllDiscountsController());
                    while (true)
                    {
                        System.out.println("please enter discount code");
                        String discountCode=scanner.nextLine();
                        try {
                            mController.checkValidationDiscountCodeController(discountCode);
                        } catch (Exception e) {
                            System.out.println("code is used please try again");
                            command="back";
                        }
                        if (Commands.BACK.getMatcher(command).matches())
                            break;
                        System.out.println("please enter start year");
                        int year= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter start month");
                        int month= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter start day");
                        int day= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter start hour");
                        int hour= Integer.parseInt(scanner.nextLine());
                        GregorianCalendar begin = new GregorianCalendar(year,month-1,day,hour,0,0);
                        System.out.println("please enter end year");
                         year= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter end month");
                         month= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter end day");
                         day= Integer.parseInt(scanner.nextLine());
                        System.out.println("please enter end hour");
                         hour= Integer.parseInt(scanner.nextLine());
                        GregorianCalendar end = new GregorianCalendar(year,month-1,day,hour,0,0);
                        System.out.println("enter discount percentage");
                        double percent = Double.parseDouble(scanner.nextLine());
                        while(percent>100.00 || percent<=0)
                        {
                            System.out.println("enter a vilid discount percentage");
                            percent = Double.parseDouble(scanner.nextLine());
                        }
                        System.out.println("enter discount amount limit");
                        int amountLimit = Integer.parseInt(scanner.nextLine());
                        System.out.println("enter discount reapeat for each customer");
                        int reapeatLimit = Integer.parseInt(scanner.nextLine());
                        System.out.println("enter  customers then type done");
                        HashMap<CustomerAccount,Integer> customers =new HashMap<CustomerAccount,Integer>();
                        String customer="";
                        while(!customer.equals("done"))
                        {
                            customer=scanner.nextLine();
                            if (customer.equals("done"))
                                break;
                            try {
                                customers.put(mController.getCustomerAccountAccountTypeByUsernameController(customer),0);
                            } catch (Exception e) {
                                System.out.println("customer not found");
                            }
                        }
                        mController.createDiscountCodeController(discountCode,begin,end,percent,amountLimit,reapeatLimit,customers);
                    }
                }
                if (Commands.VIEW_DISCOUNT_CODES.getMatcher(command).matches())
                {
                    mController.showAllDiscountsController();
                    while(true) {
                        command = scanner.nextLine();
                        if ((matcher = Commands.VIEW_DISCOUNT_CODE.getMatcher(command)).matches()) {
                            String discountCode = matcher.group(1);
                            mController.viewDiscount(discountCode);
                        }
                        if ((matcher = Commands.REMOVE_DISCOUNT_CODE.getMatcher(command)).matches())
                        {
                            String discountCode = matcher.group(1);
                            mController.deleteDiscountController(discountCode);
                        }
                        if ((matcher = Commands.EDIT_DISCOUNT_CODE.getMatcher(command)).matches())
                        {
                            System.out.println("please enter discount code");
                            String discountCode=scanner.nextLine();
                            System.out.println("please enter start year");
                            int year= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start month");
                            int month= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start day");
                            int day= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter start hour");
                            int hour= Integer.parseInt(scanner.nextLine());
                            GregorianCalendar begin = new GregorianCalendar(year,month-1,day,hour,0,0);
                            System.out.println("please enter end year");
                            year= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end month");
                            month= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end day");
                            day= Integer.parseInt(scanner.nextLine());
                            System.out.println("please enter end hour");
                            hour= Integer.parseInt(scanner.nextLine());
                            GregorianCalendar end = new GregorianCalendar(year,month-1,day,hour,0,0);
                            System.out.println("enter discount percentage");
                            double percent = Double.parseDouble(scanner.nextLine());
                            while(percent>100.00 || percent<=0)
                            {
                                System.out.println("enter a vilid discount percentage");
                                percent = Double.parseDouble(scanner.nextLine());
                            }
                            System.out.println("enter discount amount limit");
                            int amountLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter discount reapeat for each customer");
                            int reapeatLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("enter  customers then type done");
                            HashMap<CustomerAccount,Integer> customers =new HashMap<CustomerAccount,Integer>();
                            String customer="";
                            while(!customer.equals("done")) {
                                customer = scanner.nextLine();
                                if (customer.equals("done"))
                                    break;
                                try {
                                    customers.put(mController.getCustomerAccountAccountTypeByUsernameController(customer), 0);
                                } catch (Exception e) {
                                    System.out.println("customer not found");
                                }
                            }
                            mController.editDiscountController(discountCode,begin,end,percent,amountLimit,reapeatLimit,customers);
                        }
                    }
                }
            }
            if (Commands.MANAGE_REQUEST.getMatcher(command).matches())
            {
                while(true)
                {
                    mController.showAllRequestsController();
                    command=scanner.nextLine();
                    if (Commands.BACK.getMatcher(command).matches())
                        break;
                    if ((matcher = Commands.DETAILS.getMatcher(command)).matches())
                    {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            mController.requestDetailController(id);
                        } catch (Exception e) {
                            System.out.println("request not found");
                        }
                    }
                    if ((matcher = Commands.ACCEPT.getMatcher(command)).matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            mController.acceptRequest(id);
                        } catch (Exception e) {
                            System.out.println("unanswered request not found");
                        }
                    }
                    if ((matcher = Commands.DECLINE.getMatcher(command)).matches())
                    {
                        int id = Integer.parseInt(matcher.group(1));
                        try {
                            mController.declineRequest(id);
                        } catch (Exception e) {
                            System.out.println("unanswered request not found");
                        }
                    }
                }
            }
            if (Commands.MANAGE_CATEGORIES.getMatcher(command).matches())
            {
                while (true)
                {
                    mController.getAllCategories();
                command=scanner.nextLine();
                if (Commands.BACK.getMatcher(command).matches())
                    break;
                if ((matcher=Commands.EDIT_CATEGORY.getMatcher(command)).matches())
                {
                    String categoryName = matcher.group(1);
                    try {
                        Category a =mController.getCategoryBynameCategoryTypeMoudel(categoryName);
                    } catch (Exception e) {
                        System.out.println("category not found please try again");
                        break;
                    }
                    System.out.println("please enter the new name");
                    String newName = scanner.nextLine();
                    System.out.println("enter new artibute");
                    String newarrtibute=scanner.nextLine();
                    System.out.println("enter new parent category name");
                    String newparentCategoryName=scanner.nextLine();
                    try {
                        mController.getCategoryBynameCategoryTypeMoudel(newparentCategoryName);
                    } catch (Exception e) {
                        System.out.println("new parent category not found please try again");
                        break;
                    }
                    try {
                        mController.editCategory(mController.getCategoryBynameCategoryTypeMoudel(categoryName), newName, newarrtibute, mController.getCategoryBynameCategoryTypeMoudel(newparentCategoryName));
                    }
                    catch (Exception e) {
                    }
                }
                if ((matcher=Commands.ADD_CATEGORY.getMatcher(command)).matches())
                    {
                        String name = matcher.group(1);
                        System.out.println("enter  artibute");
                        String arrtibute=scanner.nextLine();
                        String parentCategoryName=scanner.nextLine();
                        try {
                            mController.getCategoryBynameCategoryTypeMoudel(parentCategoryName);
                        } catch (Exception e) {
                            System.out.println("new parent category not found please try again");
                            break;
                        }
                        System.out.println("pleas enter sub categories then type done");
                        String sub="";
                        ArrayList<Category> subs = null;
                        while (!sub.equals("done"))
                        {
                            sub=scanner.nextLine();
                            try {
                                subs.add(mController.getCategoryBynameCategoryTypeMoudel(sub));
                            } catch (Exception e) {
                                System.out.println("invalid sub");                            }
                            sub="back;";
                            break;
                        }
                        if (Commands.BACK.getMatcher(sub).matches())
                            break;
                        HashMap<Product,Integer> productIntegerHashMap = null;
                        System.out.println("enter categorys products then done");
                        int productId;
                        while (true)
                        {
                            String id=scanner.nextLine();

                            if (id.equals("done"))
                                break;
                            productId= Integer.parseInt(scanner.nextLine());
                            try {
                                productIntegerHashMap.put(mController.productByName(productId),mController.productCount(mController.productByName(productId)));
                            } catch (Exception e) {
                                System.out.println("product not found please try again");
                                productId=-10;
                                break;
                            }
                        }

                    }
                }
                if ((matcher=Commands.REMOVE_CATEGORY.getMatcher(command)).matches())
                {


                        String name=matcher.group(1);
                        try {
                            Category category=mController.getCategoryBynameCategoryTypeMoudel(name);
                        } catch (Exception e) {
                            System.out.println("category not found please try again");
                            break;
                        }
                        mController.deleteCategoryController(name);

                }
            }
        }
        Page page = pagesHistory.get(pagesHistory.size()-1);
        pagesHistory.remove(pagesHistory.get(pagesHistory.size()-1));
        return page;
    }
}
