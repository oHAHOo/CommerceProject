package org.zerock.shoppingCart;

import org.zerock.Product;

public class CartService {
    private int totalPrice;
    private int quantity;

    public void addCartItem(ShoppingCart shoppingCart, Product product) {
        if (product.getQuantity() == 0) {
            System.out.println("재고가 부족합니다.");
            return;
        }

        for (CartItem cartItem : shoppingCart.getCartItems()) {
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
        shoppingCart.getCartItems().add(new CartItem(product));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
    }

    public void printCart(ShoppingCart shoppingCart) {
        totalPrice = 0;
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("[ 장바구니 내역 ]");
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Product product = cartItem.getProduct();
            System.out.println(product.getName() + " | " +  product.getPrice() +
                    " | " + product.getDescription() + " | " +  cartItem.getQuantity());
        }

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            totalPrice+= cartItem.getTotalPrice();
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(totalPrice + "원");
    }

    public void order(ShoppingCart shoppingCart) {
        System.out.println("주문이 완료됐습니다! 총 금액: " + totalPrice + "원");
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Product product = cartItem.getProduct();
            quantity = cartItem.getQuantity();
            System.out.print(product.getName() + "재고가 " + product.getQuantity() +
                    "개 -> ");
            product.reduceQuantity(quantity);
            System.out.println(product.getQuantity() + "개로 업데이트되었습니다.");
        }
        shoppingCart.getCartItems().clear();
    }
}
