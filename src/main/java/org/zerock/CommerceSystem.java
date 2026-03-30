package org.zerock;

import org.zerock.Admin.Admin;
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

    private int selectedMenu;
    private int selectedProductNumber;
    private int selectedOrderMenu;

    private String password;
    private Admin admin;
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
                    System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
                    selectedOrderMenu = scanner.nextInt();
                    switch (selectedOrderMenu) {
                        case 1: {
                            cartService.order();
                            continue;
                        }
                        case 2: continue;
                        default: {
                            System.out.println("잘못된 입력입니다.");
                            continue;
                        }
                    }
                }
                case 5: {
                    //주문 취소
                    continue;
                }
                case 6: {
                    if (authority == false){
                        System.out.println("관리자 비밀번호를 입력해주세요:");
                        password = scanner.next();
                        authority = admin.checkAdmin(password);
                    }
                    if (authority == true) {
                        //관리자 메뉴 출력
                    }
                }
            }
        }
    }
}
