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
     public HashMap<Product, Integer> getProductsAndCount() {
        return productsAndCount;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    
     public void editCategory(String newName, String attribute,Category parentCategory)
    {
        this.getParentCategory().subCategories.remove(this);
        this.parentCategory=parentCategory;
        this.name = name;
        this.attribute = attribute;
    }
    public Category getParentCategory() {
        return parentCategory;
    }

    public Category(String name, String attribute, Category parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount) {
        this.name = name;
        this.attribute = attribute;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
        this.productsAndCount = productsAndCount;
    }
}

