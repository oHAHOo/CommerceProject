package org.zerock;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> products;
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return categoryName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean hasProductName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}
