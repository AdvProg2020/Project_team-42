package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    private String attribute;
    private Category parentCategory;
    private ArrayList<Category> subCategories;
    private HashMap<Product, Integer> productsAndCount;

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", attribute='" + attribute + '\'' +
                ", parentCategory=" + parentCategory +
                ", subCategories=" + subCategories +
                ", productsAndCount=" + productsAndCount +
                '}';
    }
}

