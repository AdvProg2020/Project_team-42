package Controller;

import Model.Product;
import Model.Shop;
import Model.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AllProductsPageController {
    private static AllProductsPageController allProductsPageController = new AllProductsPageController();

    private AllProductsPageController() {
        allFilteredProduct = new ArrayList<>();
        allFilteredProduct.addAll(shop.getAllProductAndCount().keySet());
        allProducts = new ArrayList<>();
        allProducts.addAll(shop.getAllProductAndCount().keySet());
        allFilteredProductOnOff = new ArrayList<>();
        allFilteredProductOnOff.addAll(shop.getAllProductOnOffsAndCount().keySet());
        allProductsOnOff = new ArrayList<>();
        allProductsOnOff.addAll(shop.getAllProductOnOffsAndCount().keySet());
    }

    public static AllProductsPageController getInstance() {
        return allProductsPageController;
    }

    private Shop shop = Shop.getInstance();
    private ArrayList<Product> allFilteredProduct;
    private ArrayList<Product> allProducts;
    private ArrayList<Product> allFilteredProductOnOff;
    private ArrayList<Product> allProductsOnOff;
    private String nameForFilter = null;
    private String brandForFilter = null;
    private Category categoryForFilter = null;
    private double rateForFilter = 0;
    private double minPrice = 0;
    private double maxPrice = 1000000000;
    private String nameForFilterOnOff = null;
    private String brandForFilterOnOff = null;
    private Category categoryForFilterOnOff = null;
    private double rateForFilterOnOff = 0;
    private double minPriceOnOff = 0;
    private double maxPriceOnOff = 1000000000;

    public static void setAllProductsPageController(AllProductsPageController allProductsPageController) {
        AllProductsPageController.allProductsPageController = allProductsPageController;
    }


    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setNameForFilterOnOff(String nameForFilterOnOff) {
        this.nameForFilterOnOff = nameForFilterOnOff;
    }

    public void setBrandForFilterOnOff(String brandForFilterOnOff) {
        this.brandForFilterOnOff = brandForFilterOnOff;
    }

    public void setCategoryForFilterOnOff(Category categoryForFilterOnOff) {
        this.categoryForFilterOnOff = categoryForFilterOnOff;
    }

    public void setRateForFilterOnOff(double rateForFilterOnOff) {
        this.rateForFilterOnOff = rateForFilterOnOff;
    }

    public void setMinPriceOnOff(double minPriceOnOff) {
        this.minPriceOnOff = minPriceOnOff;
    }

    public void setMaxPriceOnOff(double maxPriceOnOff) {
        this.maxPriceOnOff = maxPriceOnOff;
    }

    public void setNameForFilter(String nameForFlter) {
        this.nameForFilter = nameForFlter;
    }

    public void setBrandForFilter(String brandForFilter) {
        this.brandForFilter = brandForFilter;
    }

    public void setCategoryForFilter(Category categoryForFilter) {
        this.categoryForFilter = categoryForFilter;
    }

    public void setRateForFilter(double rateForFilter) {
        this.rateForFilter = rateForFilter;
    }

    public void setRateZiro() {
        this.rateForFilter = 0;
    }

    public void setMinPriceZiro() {
        this.minPrice = 0;
    }

    public void setMaxPriceZiro() {
        this.maxPrice = 1000000000;
    }

    public void setNameZiro() {
        this.nameForFilter = null;
    }

    public void setBrandZiro() {
        this.brandForFilter = null;
    }

    public void setCategoryZiro() {
        this.categoryForFilter = null;
    }

    public void setRateZiroForOff() {
        this.rateForFilterOnOff = 0;
    }

    public void setMinPriceZiroForOff() {
        this.minPriceOnOff = 0;
    }

    public void setMaxPriceZiroForOff() {
        this.maxPriceOnOff = 1000000000;
    }

    public void setNameZiroForOff() {
        this.nameForFilterOnOff = null;
    }

    public void setBrandZiroForOff() {
        this.brandForFilterOnOff = null;
    }

    public void setCategoryZiroForOff() {
        this.categoryForFilterOnOff = null;
    }

    public void showOFFProducts() {
        for (Product product : shop.getAllProductOnOffsAndCount().keySet()) {
            System.out.println(product);
        }
    }

    public String showCategories() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if (!shop.getAllCategories().isEmpty()) {
            for (Category category : shop.getAllCategories()) {
                stringBuilder.append(category.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }

    public ArrayList<Product> prossesFiltering(ArrayList<Product> products, double maxPrice, double minPrice, double minRateForFilter, Category categoryForFilter, String brandForFilter, String nameForFlter) {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (Product product : products) {
            if ((product.getPrice() <= maxPrice && product.getPrice() >= minPrice) && product.getAverageRate() >= minRateForFilter) {
                if (!categoryForFilter.equals(null) && product.getCategory().equals(categoryForFilter)) {
                    if (!brandForFilter.equals(null) && product.getBrand().equals(brandForFilter)) {
                        if (!nameForFlter.equals(null) && product.getName().equals(nameForFlter)) {
                            filteredProducts.add(product);
                        } else {
                            filteredProducts.add(product);
                        }
                    } else {
                        if (!nameForFlter.equals(null) && product.getName().equals(nameForFlter)) {
                            filteredProducts.add(product);
                        } else {
                            filteredProducts.add(product);
                        }
                    }
                } else {
                    if (!brandForFilter.equals(null) && product.getBrand().equals(brandForFilter)) {
                        if (!nameForFlter.equals(null) && product.getName().equals(nameForFlter)) {
                            filteredProducts.add(product);
                        } else {
                            filteredProducts.add(product);
                        }
                    } else {
                        if (!nameForFlter.equals(null) && product.getName().equals(nameForFlter)) {
                            filteredProducts.add(product);
                        } else {
                            filteredProducts.add(product);
                        }
                    }
                }
            }
        }
        return products;
    }

    public ArrayList<Product> sortByMaxPrice(ArrayList<Product> products) {
        Collections.sort(products, new SortByMaxPrice());
        return products;
    }

    public ArrayList<Product> sortByMinPrice(ArrayList<Product> products) {
        Collections.sort(products, new SortByMinPrice());
        return products;
    }

    public ArrayList<Product> sortByVisit(ArrayList<Product> products) {
        Collections.sort(products, new SortByVisit());
        return products;
    }

    public ArrayList<Product> sortByTime(ArrayList<Product> products) {
        Collections.sort(products, new SortByTime());
        return products;
    }

    public ArrayList<Product> sortByRate(ArrayList<Product> products) {
        Collections.sort(products, new SortByRate());
        return products;
    }

    public void showProducts() {
        for (Product product : allFilteredProduct) {
            System.out.println(product);
        }
    }

    public void showProductByProcuctId(int id) {
        boolean find = false;
        for (Product product : allFilteredProduct) {
            if (product.getProductId() == id) {
                System.out.println(product);
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("there is not this id in out list");
        }
    }

    public void showProductByProcuctOnOffId(int id) {
        boolean find = false;
        for (Product product : allFilteredProductOnOff) {
            if (product.getProductId() == id) {
                System.out.println(product);
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("there is not this id in out list");
        }
    }

    public void showAvailableFilters() {
        System.out.println("name " +
                "brand " +
                "category " +
                "price maxprice minprice " +
                "rate minrate ");
    }

    public ArrayList<Product> getAllFilteredProduct() {
        return allFilteredProduct;
    }

    public ArrayList<Product> getAllFilteredProductOnOff() {
        return allFilteredProductOnOff;
    }

    private class SortByMaxPrice implements Comparator<Product> {

        public int compare(Product a, Product b) {
            if (a.getPrice() >= b.getPrice())
                return a.getPrice() - b.getPrice();
            else {
                return b.getPrice() - a.getPrice();
            }
        }
    }

    private class SortByMinPrice implements Comparator<Product> {

        public int compare(Product a, Product b) {
            if (a.getPrice() <= b.getPrice())
                return a.getPrice() - b.getPrice();
            else {
                return b.getPrice() - a.getPrice();
            }
        }
    }

    private class SortByVisit implements Comparator<Product> {

        public int compare(Product a, Product b) {
            if (a.getVisit() >= b.getVisit())
                return a.getVisit() - b.getVisit();
            else {
                return b.getVisit() - a.getVisit();
            }
        }
    }

    private class SortByTime implements Comparator<Product> {

        public int compare(Product a, Product b) {
            if (a.getProductId() >= b.getProductId())
                return (int) (a.getProductId() - b.getProductId());
            else {
                return b.getPrice() - a.getPrice();
            }
        }
    }

    private class SortByRate implements Comparator<Product> {

        public int compare(Product a, Product b) {
            if (a.getAverageRate() >= b.getAverageRate())
                return (int) (a.getAverageRate() - b.getAverageRate());
            else {
                return b.getPrice() - a.getPrice();
            }
        }
    }

    public Product getProductById (int id) throws Exceptions.NoProductByThisIdException {
        return shop.getProductById(id);
    }
}

