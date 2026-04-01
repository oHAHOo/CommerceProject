package org.zerock;

import java.util.List;
import java.util.Scanner;

public class AdminService {
    private final String adminPassword = "admin123";
    private Scanner scanner;
    private List<Category> categories;
    private Category selectedCategory;

    public AdminService(List<Category> categories, Scanner scanner) {
        this.scanner = scanner;
        this.categories = categories;
    }

    public void addProductByAdmin() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");

        int i = 1;
        for(Category category : categories) {
            System.out.println(i + ". " + category.getName());
            i++;
        }
        System.out.println("0. 뒤로가기");

        int categoryNumber = Integer.parseInt(scanner.nextLine().trim());

        if (categoryNumber == 0) {
            return;
        }
        if (categoryNumber < 1 || categoryNumber > categories.size()) {
            System.out.println("잘못된 입력입니다");
            return;
        }
        selectedCategory = categories.get(categoryNumber - 1);

        System.out.println("[ " + selectedCategory.getName() + "카테고리에 상품 추가 ]");

        System.out.println("상품명을 입력해주세요: ");
        String name = scanner.nextLine().trim();

        if(selectedCategory.hasProductName(name)) {
            System.out.println("같은 카테고리에 중복된 상품명이 존재합니다.");
            return;
        }

        System.out.println("가격을 입력해주세요: ");
        int price = Integer.parseInt(scanner.nextLine());

        System.out.print("상품 설명을 입력해주세요: ");
        String description = scanner.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Product newProduct = new Product(name, price, description, quantity);

        System.out.println(newProduct.getName() + " | " +
                newProduct.getPrice() + "원 | " +
                newProduct.getDescription() + " | 재고: " +
                newProduct.getQuantity() + "개");

        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int confirm = Integer.parseInt(scanner.nextLine());

        if (confirm == 1) {
            selectedCategory.addProduct(newProduct);
            System.out.println("상품이 성공적으로 추가되었습니다!");
        } else {
            System.out.println("취소되었습니다.");
        }

    }
    public void updateProductByAdmin() {
        Product product = null;
        System.out.println("수정할 상품명을 입력해주세요: ");

        String productName = scanner.nextLine().trim();


        for(Category category : categories) {
            product = category.findProductByName(productName);
            if (product != null) {
                break;
            }
        }
        if (product == null) {
            System.out.println("상품이 존재하지 않습니다.");
            return;
        }
        System.out.println("현재 상품 정보: " + product.getName() + " | "
                + product.getPrice() + "원 | "
                + product.getDescription() + " | 재고: "
                + product.getQuantity() + "개");

        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");

        int updateMenu = Integer.parseInt(scanner.nextLine());

        switch (updateMenu) {
            case 1: {
                int beforePrice = product.getPrice();
                System.out.println("현재 가격: " + beforePrice + "원");
                System.out.print("새로운 가격을 입력해주세요: ");
                int afterPrice = Integer.parseInt(scanner.nextLine());
                product.setPrice(afterPrice);
                System.out.println(product.getName() + "의 가격이 " + beforePrice + "원 -> " + afterPrice + "원으로 수정되었습니다.");
                return;
            }
            case 2: {
                String beforeDescription = product.getDescription();
                System.out.println("현재 설명: " + beforeDescription);
                System.out.print("새로운 설명을 입력해주세요: ");
                String afterDescription = scanner.nextLine();
                product.setDescription(afterDescription);
                System.out.println(product.getName() + "의 설명이 수정되었습니다.");
                return;
            }
            case 3: {
                int beforeQuantity = product.getQuantity();
                System.out.println("현재 재고수량: " + beforeQuantity + "개");
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int afterQuantity = Integer.parseInt(scanner.nextLine());
                product.setQuantity(afterQuantity);
                System.out.println(product.getName() + "의 재고가 " + beforeQuantity + "개 -> " + afterQuantity + "개로 수정되었습니다.");
                return;
            }
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
    public void deleteProductByAdmin() {
        System.out.println("삭제할 상품명을 입력해주세요: ");
        String productName = scanner.nextLine().trim();

        Product selectedProduct = null;

        for(Category category : categories) {
            Product product = category.findProductByName(productName);
            if (product != null) {
                selectedCategory = category;
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct  == null) {
            System.out.println("상품이 존재하지 않습니다.");
            return;
        }
        System.out.println("현재 상품 정보: " + selectedProduct.getName() + " | "
                + selectedProduct.getPrice() + "원 | "
                + selectedProduct.getDescription() + " | 재고: "
                + selectedProduct.getQuantity() + "개");

        System.out.println("위 상품을 삭제하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int confirm = Integer.parseInt(scanner.nextLine());

        if (confirm != 1) {
            System.out.println("취소되었습니다.");
            return;
        }

        selectedCategory.removeProduct(selectedProduct);

        System.out.println("상품이 성공적으로 삭제되었습니다.");

    }
    public void printAllProducts() {
        for (Category category : categories) {
            System.out.println("\n[ " + category.getName() + " ]");

            for (Product product : category.getProducts()) {
                System.out.println(product.getName() + " | "
                        + product.getPrice() + "원 | "
                        + product.getDescription() + " | 재고: "
                        + product.getQuantity() + "개");
            }
        }
    }

    public boolean checkAdmin(String password) {
        return  adminPassword.equals(password);
    }
}
