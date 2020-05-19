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
    }

    public static AllProductsPageController getInstance() {
        return allProductsPageController;
    }

    private Shop shop = Shop.getInstance();
    private ArrayList<Product> allFilteredProduct = (ArrayList<Product>) shop.getAllProductOnOffsAndCount().keySet();
    private ArrayList<Product> allProducts = (ArrayList<Product>) shop.getAllProductOnOffsAndCount().keySet();
    private String nameForFlter = null;
    private String brandForFilter = null;
    private Category categoryForFilter = null;
    private double rateForFilter = 0;
    private double minPrice = 0;
    private double maxPrice = 1000000000;

    public void setNameForFlter(String nameForFlter) {
        this.nameForFlter = nameForFlter;
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

    public void showOFFProducts() {
        for (Product product : shop.getAllProductOnOffsAndCount().keySet()) {
            System.out.println(product);
        }
    }

    public void showCategories() {
        if (!shop.getAllCategories().isEmpty()) {
            for (Category category : shop.getAllCategories()) {
                System.out.println(category);
            }
        }
    }

    public void prossesFiltering() {
        ArrayList<Product> filteredProducts = new ArrayList<Product>();
        for (Product product : allProducts) {
            if ((product.getPrice() <= maxPrice && product.getPrice() >= maxPrice) && product.getRate() >= rateForFilter) {
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
        allFilteredProduct = filteredProducts;
    }

    public void sortByMaxPrice() {
        Collections.sort(allFilteredProduct, new SortByMaxPrice());
    }

    public void sortByMinPrice() {
        Collections.sort(allFilteredProduct, new SortByMinPrice());
    }

    public void sortByVisit() {
        Collections.sort(allFilteredProduct, new SortByVisit());
    }

    public void sortByTime() {
        Collections.sort(allFilteredProduct, new SortByTime());
    }

    public void sortByRate() {
        Collections.sort(allFilteredProduct, new SortByRate());
    }

}

class SortByMaxPrice implements Comparator<Product> {

    public int compare(Product a, Product b) {
        if (a.getPrice() >= b.getPrice())
            return a.getPrice() - b.getPrice();
        else {
            return b.getPrice() - a.getPrice();
        }
    }
}

class SortByMinPrice implements Comparator<Product> {

    public int compare(Product a, Product b) {
        if (a.getPrice() <= b.getPrice())
            return a.getPrice() - b.getPrice();
        else {
            return b.getPrice() - a.getPrice();
        }
    }
}

class SortByVisit implements Comparator<Product> {

    public int compare(Product a, Product b) {
        if (a.getVisit() >= b.getVisit())
            return a.getVisit() - b.getVisit();
        else {
            return b.getVisit() - a.getVisit();
        }
    }
}

class SortByTime implements Comparator<Product> {

    public int compare(Product a, Product b) {
        if (a.getProductId() >= b.getProductId())
            return (int) (a.getProductId() - b.getProductId());
        else {
            return b.getPrice() - a.getPrice();
        }
    }
}

class SortByRate implements Comparator<Product> {

    public int compare(Product a, Product b) {
        if (a.getRate() >= b.getRate())
            return (int) (a.getRate() - b.getRate());
        else {
            return b.getPrice() - a.getPrice();
        }
    }
}
