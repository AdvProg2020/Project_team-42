package View.Pages;

import Controller.AllProductsPageController;
import Controller.Exceptions;
import Model.Shop;
import View.Commands;
import View.Page;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllProductsPage extends Page {
    private static AllProductsPage allProductsPage = new AllProductsPage();
    private Shop shop = Shop.getInstance();
    private AllProductsPageController controller = AllProductsPageController.getInstance();
    private ArrayList<String> allCurrentFiltters = new ArrayList<>() ;
    private ArrayList<String> allCurrentSorts = new ArrayList<>();
    private AllProductsPage() {
    }

    public static AllProductsPage getInstance() {
        return allProductsPage;
    }

    public Page run() throws Exceptions.NoCategoryException {
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
                sortingProsses();
            } else if (Commands.SHOW_PRODUCTS.getMatcher(input).matches()) {
                showProducts();
            } else if ((matcher =Commands.SHOW_PRODUCT_BY_ID.getMatcher(input)).matches()) {
                showProductByProductId(Integer.parseInt(matcher.group(1)));
            }
        }
        return null;
    }

    public void help() {
        System.out.println("products" +
                "viewcategories" +
                "filtering" +
                "sorting" +
                "showproducts" +
                "showproduct[productId]"
        );
    }

    public void showAllCaregories() {
        controller.showCategories();
    }

    public void filteringProsses() throws Exceptions.NoCategoryException {
        Matcher matcher;
        String input;
        while ((matcher = Commands.BACK.getMatcher(input = scanner.nextLine())).matches()) {
            if(Commands.SHOW_AVALABLE_FILTERS.getMatcher(input).matches()){
                controller.showAvailableFilters();
            }if((matcher = Commands.FILTER_ANAVALAIBLE_FILTER.getMatcher(input)).matches()){
                if(matcher.group(1).equals("(?i)name")){
                    allCurrentFiltters.add("name");
                    controller.setNameForFilter(String.valueOf(matcher.group(1)));
                }if(matcher.group(1).equals("(?i)brand")){
                    allCurrentFiltters.add("brand");
                    controller.setBrandForFilter(String.valueOf(matcher.group(1)));
                }if(matcher.group(1).equals("(?i)category")){
                    allCurrentFiltters.add("category");
                    controller.setCategoryForFilter(shop.categoryByName(matcher.group(1)));
                }if(matcher.group(1).equals("(?i)price\\s+\\s*(\\d+)\\s+\\s*(\\d+)")){
                    allCurrentFiltters.add("price");
                    controller.setMaxPrice(Integer.valueOf(matcher.group(2)));
                    controller.setMinPrice(Integer.valueOf(matcher.group(3)));
                }if(matcher.group(1).equals("(?i)rate\\s+(\\d+)")){
                    allCurrentFiltters.add("rate");
                    controller.setRateForFilter(Integer.valueOf(matcher.group(2)));
                }
            }if(Commands.CURRENST_FILTERS.getMatcher(input).matches()){
                System.out.println(allCurrentFiltters);
            }if((matcher =Commands.DISABLE_FILTERS.getMatcher(input)).matches()){
                if(allCurrentFiltters.contains(String.valueOf(matcher.group(1)))){
                    allCurrentFiltters.remove(String.valueOf(matcher.group(1)));
                    if(String.valueOf((matcher.group(1))).equals("name")){
                        controller.setNameZiro();
                    }if(String.valueOf((matcher.group(1))).equals("brand")){
                        controller.setBrandZiro();
                    }if(String.valueOf((matcher.group(1))).equals("category")){
                        controller.setCategoryZiro();
                    }if(String.valueOf((matcher.group(1))).equals("price")){
                        controller.setMaxPriceZiro();
                    }if(String.valueOf((matcher.group(1))).equals("rate")){
                        controller.setRateZiro();
                    }
                }else{
                    System.out.println("there is no that filter");
                }

            }
        }
    }

    public void sortingProsses() throws Exceptions.NoCategoryException {
        Matcher matcher;
        String input;
        while ((matcher = Commands.BACK.getMatcher(input = scanner.nextLine())).matches()) {
            if(Commands.SHOW_AVALABLE_SORT.getMatcher(input).matches()){
                controller.showAvailableFilters();
            }if((matcher = Commands.SORT_ANAVALAIBLE_SORT.getMatcher(input)).matches()){
                if(matcher.group(1).equals("(?i)visit")){
                    allCurrentSorts.add( "visit");
                    controller.sortByVisit(controller.getAllFilteredProduct());
                }if(matcher.equals("(?i)max\\s+price")){
                    allCurrentFiltters.add("max");
                    controller.sortByMaxPrice(controller.getAllFilteredProduct());
                }if(matcher.equals("(?i)min\\s+price")){
                    allCurrentFiltters .add("min");
                    controller.sortByMinPrice(controller.getAllFilteredProduct());
                }if(matcher.equals("(?i)time")){
                    allCurrentFiltters.add("time");
                    controller.sortByTime(controller.getAllFilteredProduct());
                }if(matcher.equals("(?i)rate")){
                    allCurrentFiltters.add("rate");
                    controller.sortByRate(controller.getAllFilteredProduct());
                }
            }if(Commands.CURRENST_SORT.getMatcher(input).matches()){
                System.out.println(allCurrentFiltters);
            }if((Commands.DISABLE_SORT.getMatcher(input)).matches()){
                controller.sortByVisit(controller.getAllFilteredProduct());
            }
        }
    }

    public void showProducts() {
        controller.showProducts();
    }

    public void showProductByProductId(int id) {
        showProductByProductId(id);
    }

}
