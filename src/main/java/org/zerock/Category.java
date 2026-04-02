package org.zerock;

import java.util.ArrayList;
import java.util.List;
//여러개의 상품을 관리하는 카테고리 클래스
public class Category {
    private List<Product> products; //카테고리에 저장될 상품들
    private String categoryName; //카테고리 이름

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
    }
    //카테고리 이름 반환
    public String getName() {
        return categoryName;
    }
    //상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }
    //상품 삭제
    public void removeProduct(Product product) {
        products.remove(product);
    }
    //전체 상품 반환
    public List<Product> getProducts() {
        return products;
    }
    //이름을 통해 상품이 있는지 확인
    public boolean hasProductName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }
    //이름으로 상품 검색
    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
}
