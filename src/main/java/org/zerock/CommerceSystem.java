package org.zerock;

import org.zerock.shoppingCart.CartService;
import org.zerock.shoppingCart.ShoppingCart;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private CartService cartService = new CartService();
    private Scanner scanner;
    private List<Category> categories;
    private List<Product> products;
    private Category selectedCategory;
    private int menu;
    private int selectProduct;
    private int selectOrder;
    private int addToCart;
    private ShoppingCart shoppingCart;


    public CommerceSystem(List<Category> categories, ShoppingCart shoppingCart, Scanner scanner) {
        this.scanner = scanner;
        this.categories = categories;
        this.shoppingCart = shoppingCart;
    }

    public void start() {
        while(true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 - 전자제품 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료   |프로그램 종료\n");
            if(!shoppingCart.getCartItems().isEmpty()){
                System.out.println("[ 주문 관리 ]");
                System.out.println("4. 장바구니 확인  | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. 주문 취소     | 진행중인 주문을 취소합니다");
            }

            menu = scanner.nextInt();
            if(menu == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            if((menu == 4 || menu == 5) && shoppingCart.getCartItems().isEmpty()) {
                System.out.println("존재하지 않는 메뉴입니다.");
                continue;
                //장바구니가 비었으니까 예외처리
            }

            if(menu >= 1 && menu <=3) {
                //1~3번 고를시
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
                if(selectProduct > products.size()) {
                    System.out.println("존재하지 않는 상품입니다.");
                    continue;
                }
                Product product = products.get(selectProduct - 1);
                System.out.println("\n선택한 상품: " + product.getName() + " | " + product.getPrice() + " | "
                        +  product.getDescription() + " | 재고: " + product.getQuantity() + "개");

                //도전과제 장바구니 만들기
                System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인    2. 취소");

                addToCart = scanner.nextInt();

                if (addToCart == 1) {
                    cartService.addCartItem(shoppingCart, product);
                    continue;
                }
            }
            switch (menu) {
                case 4: {
                    cartService.printCart(shoppingCart);
                    System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
                    selectOrder = scanner.nextInt();
                    switch (selectOrder) {
                        case 1: {
                            cartService.order(shoppingCart);
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
            }
        }
    }
}
