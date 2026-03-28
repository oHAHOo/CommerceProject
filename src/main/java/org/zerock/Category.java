package org.zerock;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> products =  new ArrayList<>();
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return categoryName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public List<Product> getProducts() {
        return products;
    }


}
