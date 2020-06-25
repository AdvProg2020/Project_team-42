package Model;

import Controller.Exceptions;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    private String attribute;
    private String parentCategory;
    private ArrayList<String> subCategories;
    private HashMap<Integer, Integer> productsAndCount;

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
        HashMap<Product, Integer> productsAndCount = new HashMap<>();
        for (Integer productId : this.productsAndCount.keySet()) {
            try {
                productsAndCount.put(Shop.getInstance().getProductById(productId), this.productsAndCount.get(productId));
            } catch (Exceptions.NoProductByThisIdException ignored) {
            }
        }
        return productsAndCount;
    }

    public HashMap<Integer, Integer> getProductIdAndCount () {
        return productsAndCount;
    }

    public ArrayList<Category> getSubCategories() {
        ArrayList<Category> subCategories = new ArrayList<>();
        for (String subCategoryName : this.subCategories) {
            try {
                subCategories.add(Shop.getInstance().getCategoryByName(subCategoryName));
            } catch (Exceptions.NoCategoryException ignored) {
            }
        }
        return subCategories;
    }

    public ArrayList<String> addToSubCategories() {
        return subCategories;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory.getName();

        try {
            updateResources();
        } catch (IOException ignored) {
        }
    }

    public void editCategory(String newName, String attribute, Category parentCategory) {
        this.getParentCategory().subCategories.remove(this.name);
        this.parentCategory = parentCategory.getName();
        this.name = name;
        this.attribute = attribute;

        try {
            updateResources();
        } catch (IOException ignored) {
        }
    }

    public Category getParentCategory() {
        try {
            return Shop.getInstance().getCategoryByName(parentCategory);
        } catch (Exceptions.NoCategoryException e) {
            return null;
        }
    }

    public Category(String name, String attribute, Category parentCategory, ArrayList<Category> subCategories, HashMap<Product, Integer> productsAndCount) {
        this.name = name;
        this.attribute = attribute;
        if (parentCategory != null)
            this.parentCategory = parentCategory.getName();
        this.subCategories = new ArrayList<>();
        for (Category subCategory : subCategories) {
            this.subCategories.add(subCategory.name);
        }
        this.productsAndCount = new HashMap<>();
        for (Product product : productsAndCount.keySet()) {
            this.productsAndCount.put((int) product.getProductId(), productsAndCount.get(product));
        }

        try {
            updateResources();
        } catch (IOException ignored) {
        }
    }

    public void updateResources() throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src\\main\\resources\\Categories\\" + this.name + ".txt");

        gson.toJson(this, fileWriter);
        fileWriter.close();
    }
}

