package View.Pages;

import Controller.AccountPagesController.AccountPageController;
import Controller.AccountPagesController.CustomerPageController;
import Controller.AccountPagesController.ManagerPageController;
import Controller.AccountPagesController.SellerPageController;
import Controller.AllProductsPageController;
import Controller.Exceptions;
import Model.Accounts.CustomerAccount;
import Model.Accounts.ManagerAccount;
import Model.Accounts.SellerAccount;
import Model.Shop;
import View.Commands;
import View.Page;
import View.Pages.AccountsPage.CustomerPage;
import View.Pages.AccountsPage.ManagerPage;
import View.Pages.AccountsPage.SellerPage;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class OffsPage extends Page {
    private static OffsPage offsPage = new OffsPage();
    private Shop shop = Shop.getInstance();
    private AllProductsPageController controller = AllProductsPageController.getInstance();
    private ArrayList<String> allCurrentFiltters= new ArrayList<>();
    private ArrayList<String> allCurrentSorts= new ArrayList<>();

    private OffsPage () {}

    public static OffsPage getInstance() {
        return offsPage;
    }

    public Page run() {
        Page.pagesHistory.add(this);

        System.out.println("offs page");

        Matcher matcher;
        String input;
        while (!Commands.EXIT.getMatcher(input = scanner.nextLine().trim()).matches()) {
            if (Commands.HELP.getMatcher(input).matches()) {
                help();
            } else if (Commands.SHOW_CATEGORIES.getMatcher(input).matches()) {
                showAllCaregories();
            } else if (Commands.FILTERING.getMatcher(input).matches()) {
                filteringProsses();
            } else if (Commands.SORTING.getMatcher(input).matches()) {
                sortingProses();
            } else if (Commands.SHOW_CATEGORIES.getMatcher(input).matches()) {
                showOffsProducts();
            } else if ((matcher =Commands.SHOW_PRODUCT_BY_ID.getMatcher(input)).matches()) {
                showProductByProductId(Integer.parseInt(matcher.group(1)));
            } else if (Commands.BACK.getMatcher(input).matches()) {
                Page.pagesHistory.remove(Page.pagesHistory.size() - 1);
                return Page.pagesHistory.get(Page.pagesHistory.size() - 1);
            } else if (Commands.ACCOUNT_PAGE.getMatcher(input).matches()) {
                if (AccountPageController.getUser() == null)
                    return LoginRegisterPage.getInstance();
                if (AccountPageController.getUser().getClass() == CustomerAccount.class)
                    return CustomerPage.getInstance();
                if (AccountPageController.getUser().getClass() == SellerAccount.class)
                    return SellerPage.getInstance();
                if (AccountPageController.getUser().getClass() == ManagerAccount.class)
                    return ManagerPage.getInstance();
            } else if (Commands.LOGIN_PAGE.getMatcher(input).matches()) {
                return LoginRegisterPage.getInstance();
            } else if (Commands.LOG_OUT.getMatcher(input).matches()) {
                AccountPageController.setUser(null);
                SellerPageController.getInstance().setUser(null);
                ManagerPageController.getInstance().setUser(null);
                CustomerPageController.getInstance().setUser(null);
                Page.pagesHistory.clear();
                return LoginRegisterPage.getInstance();
            } else if (Commands.ALL_PRODUCTS_PAGE.getMatcher(input).matches()) {
                return AllProductsPage.getInstance();
            } else if (Commands.CART_PAGE.getMatcher(input).matches()) {
                return CartPage.getInstance();
            } else {
            printInvalidCommandMessage();
            }
        }
        return null;
    }

    public void help() {
        System.out.println("products\n" +
                "view categories\n" +
                "filtering\n" +
                "sorting\n" +
                "show products\n" +
                "show product [productId]\n" +
                "help\n" +
                "account page\n" +
                "login page\n" +
                "log out\n" +
                "products page\n" +
                "cart page"
        );
    }

    public void showAllCaregories() {
        controller.showCategories();
    }

    public void filteringProsses() {
        Matcher matcher;
        String input;
        while (Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
            if(Commands.SHOW_AVALABLE_FILTERS.getMatcher(input).matches()){
                controller.showAvailableFilters();
            }if((matcher = Commands.FILTER_ANAVALAIBLE_FILTER.getMatcher(input)).matches()){
                if(matcher.group(1).equalsIgnoreCase("name")){
                    allCurrentFiltters.add("name");
                    controller.setNameForFilterOnOff(String.valueOf(matcher.group(1)));
                }if(matcher.group(1).equalsIgnoreCase("brand")){
                    allCurrentFiltters.add("brand");
                    controller.setBrandForFilterOnOff(String.valueOf(matcher.group(1)));
                }if(matcher.group(1).equalsIgnoreCase("category")){
                    allCurrentFiltters.add("category");
                    try {
                        controller.setCategoryForFilterOnOff(shop.getCategoryByName(matcher.group(1)));
                    } catch (Exceptions.NoCategoryException e) {
                        System.out.println(e.getMessage());
                    }
                }if((matcher = Commands.FILTERING_PRICE.getMatcher(matcher.group(1))).matches()){
                    allCurrentFiltters.add("price");
                    controller.setMaxPriceOnOff(Integer.valueOf(matcher.group(1)));
                    controller.setMinPriceOnOff(Integer.valueOf(matcher.group(2)));
                }if((matcher = Commands.FILTERING_RATE.getMatcher(matcher.group(1))).matches()){
                    allCurrentFiltters.add("rate");
                    controller.setRateForFilterOnOff(Integer.valueOf(matcher.group(1)));
                }
            }if(Commands.CURRENST_FILTERS.getMatcher(input).matches()){
                System.out.println(allCurrentFiltters);
            }if((matcher =Commands.DISABLE_FILTERS.getMatcher(input)).matches()){
                if(allCurrentFiltters.contains(String.valueOf(matcher.group(1)))){
                    allCurrentFiltters.remove(String.valueOf(matcher.group(1)));
                    if(String.valueOf((matcher.group(1))).equals("name")){
                        controller.setNameZiroForOff();
                    }if(String.valueOf((matcher.group(1))).equals("brand")){
                        controller.setBrandZiroForOff();
                    }if(String.valueOf((matcher.group(1))).equals("category")){
                        controller.setCategoryZiroForOff();
                    }if(String.valueOf((matcher.group(1))).equals("price")){
                        controller.setMaxPriceZiroForOff();
                    }if(String.valueOf((matcher.group(1))).equals("rate")){
                        controller.setRateZiroForOff();
                    }
                }else if(Commands.HELP.getMatcher(input).matches()){
                    System.out.println("back\n" +
                            "show available filters\n" +
                            "filter [an available filter]\n" +
                            "current filters\n" +
                            "disable filters\n" );
                }else{
                    System.out.println("there is no that filter");
                }

            }
        }
    }

    public void sortingProses() {
        Matcher matcher;
        String input;
        while (Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
            if(Commands.SHOW_AVALABLE_SORT.getMatcher(input).matches()){
                controller.showAvailableFilters();
            }else if((matcher = Commands.SORT_ANAVALAIBLE_SORT.getMatcher(input)).matches()){
                if(matcher.group(1).equalsIgnoreCase("visit")){
                    allCurrentSorts.add( "visit");
                    controller.sortByVisit(controller.getAllFilteredProductOnOff());
                }if(matcher.group(1).equalsIgnoreCase("max price")){
                    allCurrentFiltters.add("max");
                    controller.sortByMaxPrice(controller.getAllFilteredProductOnOff());
                }if(matcher.group(1).equalsIgnoreCase("min price")){
                    allCurrentFiltters .add("min");
                    controller.sortByMinPrice(controller.getAllFilteredProductOnOff());
                }if(matcher.group(1).equalsIgnoreCase("time")){
                    allCurrentFiltters.add("time");
                    controller.sortByTime(controller.getAllFilteredProductOnOff());
                }if(matcher.group(1).equalsIgnoreCase("rate")){
                    allCurrentFiltters.add("rate");
                    controller.sortByRate(controller.getAllFilteredProductOnOff());
                }
            }else if(Commands.CURRENST_SORT.getMatcher(input).matches()){
                System.out.println(allCurrentFiltters);
            }else if((Commands.DISABLE_SORT.getMatcher(input)).matches()){
                controller.sortByVisit(controller.getAllFilteredProductOnOff());
            }else if(Commands.HELP.getMatcher(input).matches()){
                System.out.println("show available sorts\n" +
                        "sort [an available sort]\n" +
                        "current sort\n" +
                        "disable sort\n");
            }else{
                System.out.println("invalid command");
            }
        }
    }

    public void showProducts() {
        controller.showProducts();
    }

    public void showProductByProductId(int id) {
        showProductByProductId(id);
    }

    public void showOffsProducts(){
        controller.showOFFProducts();
    }
}
