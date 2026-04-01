package org.zerock.shoppingCart;

import org.zerock.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartService {
    private Scanner scanner;
    private int totalPrice;
    private int quantity;
    private List<CartItem> cartItems = new ArrayList<>();

    public CartService(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(Product product) {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int addToCart = scanner.nextInt();

        if (addToCart != 1) {
            System.out.println("취소되었습니다.");
            return;
        }

        if (product.getQuantity() == 0) {
            System.out.println("재고가 부족합니다.");
            return;
        }

        for (CartItem cartItem : getCartItems()) {
            if (cartItem.getProduct().equals(product)) {
                if (cartItem.getQuantity() >= product.getQuantity()) {
                    System.out.println("재고가 부족합니다.");
                    return;
                }
                cartItem.addQuantity();
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
                return;
            }
        }
        getCartItems().add(new CartItem(product));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
    }

    public void printCart() {
        totalPrice = 0;
        if (getCartItems().isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("[ 장바구니 내역 ]");
        for (CartItem cartItem : getCartItems()) {
            Product product = cartItem.getProduct();
            System.out.println(product.getName() + " | " +  product.getPrice() +
                    " | " + product.getDescription() + " | " +  cartItem.getQuantity());
        }

        for (CartItem cartItem : getCartItems()) {
            totalPrice+= cartItem.getTotalPrice();
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(totalPrice + "원");
    }

    public void order() {
        System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
        int selectedOrderMenu = scanner.nextInt();
        switch (selectedOrderMenu) {
            case 1: {
                System.out.println("주문이 완료됐습니다! 총 금액: " + totalPrice + "원");
                for (CartItem cartItem : getCartItems()) {
                    Product product = cartItem.getProduct();
                    quantity = cartItem.getQuantity();
                    System.out.print(product.getName() + "재고가 " + product.getQuantity() +
                            "개 -> ");
                    product.reduceQuantity(quantity);
                    System.out.println(product.getQuantity() + "개로 업데이트되었습니다.");
                }
                getCartItems().clear();
                return;
            }
            case 2: return;
            default: {
                System.out.println("잘못된 입력입니다.");
                return;
            }
        }
    }
}
