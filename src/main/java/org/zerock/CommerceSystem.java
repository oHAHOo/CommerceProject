package org.zerock;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private Scanner scanner;
    private List<Category> categories;
    private List<Product> products;
    private Category selectedCategory;
    private int menu;
    private int selectProduct;

    public CommerceSystem(List<Category> categories, Scanner scanner) {
        this.scanner = scanner;
        this.categories = categories;
    }

    public void start() {
        while(true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 - 전자제품 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료   |프로그램 종료");

            menu = scanner.nextInt();
            if(menu == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            selectedCategory = categories.get(menu-1);
            products = selectedCategory.getProducts();

            System.out.println("\n[ " +  selectedCategory.getName() + " 카테고리 ]");
            for(int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.println((i+1) + ". " + product.getName() + "   | " + product.getPrice() + "원 | " + product.getDescription());
            }
            System.out.println("0. 뒤로가기");

            selectProduct = scanner.nextInt();
            if(selectProduct == 0) {continue;}
            Product product = products.get(selectProduct - 1);
            System.out.println("\n선택한 상품: " + product.getName() + " | " + product.getPrice() + " | "
                    +  product.getDescription() + " | 재고: " + product.getQuantity() + "개");
        }

    }
}
