package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    private String attribute;
    private Category parentCategory;
    private ArrayList<Category> subCategories;
    private HashMap<Product, Integer> productsAndCount;
}
