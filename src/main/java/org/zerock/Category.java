package org.zerock;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> products;
    private String categoryName;

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
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
