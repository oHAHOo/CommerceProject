package org.zerock;

import org.zerock.shoppingCart.CartService;
import org.zerock.shoppingCart.ShoppingCart;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Scanner scanner;
    private Category selectedCategory;
    private CartService cartService;
    private ShoppingCart shoppingCart;
    private Admin admin;
    private int selectedMenu;
    private int selectedProductNumber;
    private boolean authority;

    public CommerceSystem(List<Category> categories, ShoppingCart shoppingCart, Scanner scanner, CartService cartService, Admin admin) {
        this.scanner = scanner;
        this.categories = categories;
        this.shoppingCart = shoppingCart;
        this.cartService = cartService;
        this.admin = admin;
    }

    private int selectMenu() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인]");
        for(int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.println(i+1 + ". " + category.getName());
        }
        System.out.println("0. 종료   |프로그램 종료\n");
        if(!shoppingCart.getCartItems().isEmpty()){
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인  | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소     | 진행중인 주문을 취소합니다");
        }
        System.out.println("6. 관리자 모드");

        selectedMenu = scanner.nextInt();
        scanner.nextLine();

        return selectedMenu;
    }

    private int selectProduct() {
        selectedCategory = categories.get(selectedMenu -1);
        List<Product> products = selectedCategory.getProducts();

        //선택 카테고리 출력
        System.out.println("\n[ " +  selectedCategory.getName() + " 카테고리 ]");
        for(int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i+1) + ". " + product.getName() + "   | " + product.getPrice() + "원 | " + product.getDescription());
        }
        System.out.println("0. 뒤로가기");

        //상품 선택
        selectedProductNumber = scanner.nextInt();
        return selectedProductNumber;
    }

    public void start() {
        while(true) {
            selectedMenu = selectMenu();

            if(selectedMenu == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            if((selectedMenu == 4 || selectedMenu == 5) && shoppingCart.getCartItems().isEmpty()) {
                System.out.println("존재하지 않는 메뉴입니다.");
                continue;
                //장바구니가 비었으니까 예외처리
            }

            //1~3번 메뉴
            if(selectedMenu >= 1 && selectedMenu <=3) {
                //1~3번 고를시
                selectedProductNumber = selectProduct();

                List<Product> products = selectedCategory.getProducts();

                if(selectedProductNumber == 0) {continue;}
                if(selectedProductNumber > products.size()) {
                    System.out.println("존재하지 않는 상품입니다.");
                    continue;
                }

                //선택한 상품 출력
                Product product = products.get(selectedProductNumber - 1);
                System.out.println("\n선택한 상품: " + product.getName() + " | " + product.getPrice() + " | "
                        +  product.getDescription() + " | 재고: " + product.getQuantity() + "개");

                //도전과제 장바구니 만들기
                cartService.addCartItem(product);
            }

            //4,5,6번 메뉴
            switch (selectedMenu) {
                case 4: {
                    cartService.printCart();
                    cartService.order();
                    break;
                }
                case 5: {
                    //주문 취소
                    System.out.println("장바구니를 비웠습니다.");
                    shoppingCart.getCartItems().clear();
                    continue;
                }
                case 6: {
                    if (authority == false){
                        for (int i = 0; i < 3; i++) {
                            System.out.println("관리자 비밀번호를 입력해주세요:");
                            String password = scanner.nextLine().trim();

                            if (admin.checkAdmin(password)) {
                                authority = true;
                                break;
                            }
                            System.out.println("비밀번호가 일치하지 않습니다.");                        }

                        if(authority == false) {
                            System.out.println("비밀번호 3회 입력 실패로 메인 메뉴로 돌아갑니다.");
                            break;
                        }

                    }
                    if (authority == true) {
                        //관리자 메뉴 출력
                        while (true) {
                            System.out.println("\n[ 관리자 모드 ]");
                            System.out.println("1. 상품 추가");
                            System.out.println("2. 상품 수정");
                            System.out.println("3. 상품 삭제");
                            System.out.println("4. 전체 상품 현황");
                            System.out.println("0. 메인으로 돌아가기");

                            int adminMenu = Integer.parseInt(scanner.nextLine());

                            switch (adminMenu) {
                                case 1:
                                    addProductByAdmin();
                                    break;
                                case 2:
                                    updateProductByAdmin();
                                    break;
                                case 3:
                                    deleteProductByAdmin();
                                    break;
                                case 4:
                                    printAllProducts();
                                    break;
                                case 0:
                                    return;
                                default:
                                    System.out.println("잘못된 입력입니다.");
                            }
                        }
                    }
                }
            }
        }
    }

    private void addProductByAdmin() {
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
    private void updateProductByAdmin() {
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
    private void deleteProductByAdmin() {
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
    private void printAllProducts() {
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
}
