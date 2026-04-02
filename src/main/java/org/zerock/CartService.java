package org.zerock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartService {
    private Scanner scanner;
    private int totalPrice; //장바구니 총 금액
    private int quantity; //상품 재고
    private List<CartItem> cartItems = new ArrayList<>(); //장바구니 목록

    public CartService(Scanner scanner) {
        this.scanner = scanner;
    }
    //장바구니 목록 반환
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    //장바구니 상품 추가
    public void addCartItem(Product product) {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int addToCart;
        try {
            addToCart = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        if (addToCart != 1) {
            System.out.println("취소되었습니다.");
            return;
        }
        //재고가 없는 경우
        if (product.getQuantity() == 0) {
            System.out.println("재고가 부족합니다.");
            return;
        }

        for (CartItem cartItem : getCartItems()) {
            //장바구니에 있는 상품이면 수량 추가
            if (cartItem.getProduct().equals(product)) {
                //장바구니 수량이 남은 재고보다 많으면 재고부족
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
    //이름으로 상품 삭제 메서드
    public void removeProductByName(String productName) {

        //검색한 상품을 제외한 리스트생성
        List<CartItem> searchedCartItems = cartItems.stream()
                .filter(cartItem -> !cartItem.getProduct().getName().equals(productName))
                .collect(Collectors.toList());

        if (searchedCartItems.size() == cartItems.size()) {
            System.out.println("장바구니에 해당 상품이 없습니다.");
            return;
        }

        //기존 리스트를 비우고 새 리스트 추가
        cartItems.clear();
        cartItems.addAll(searchedCartItems);

        System.out.println(productName + " 상품이 장바구니에서 제거되었습니다.");
    }
    //장바구니 출력 메서드
    public void printCart() {
        totalPrice = 0;
        //장바구니가 비었을 경우
        if (getCartItems().isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        //장바구니 목록 출력
        System.out.println("[ 장바구니 내역 ]");
        for (CartItem cartItem : getCartItems()) {
            Product product = cartItem.getProduct();
            System.out.println(product.getName() + " | " +  product.getPrice() +
                    " | " + product.getDescription() + " | " +  cartItem.getQuantity());
        }
        //금액 계산
        for (CartItem cartItem : getCartItems()) {
            totalPrice+= cartItem.getTotalPrice();
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(totalPrice + "원");
    }
    //주문 메서드
    public void order() {
        System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
        int selectedOrderMenu;
        try {
            selectedOrderMenu = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }
        switch (selectedOrderMenu) {
            case 1: {//고객 등급 선택
                System.out.println("고객 등급을 입력해주세요.");
                System.out.println("1. BRONZE   :  0% 할인");
                System.out.println("2. SILVER   :  5% 할인");
                System.out.println("3. GOLD     : 10% 할인");
                System.out.println("4. PLATINUM : 15% 할인");

                int ratingNumber;
                try {
                    ratingNumber = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요.");
                    return;
                }

                Rating rating = Rating.fromCode(ratingNumber);
                //등급에 따른 할인계산
                int discountPrice = totalPrice * rating.getDiscountPercent() / 100;
                int finalPrice = totalPrice - discountPrice;

                System.out.println("주문이 완료되었습니다!");
                System.out.println("할인 전 금액: " + totalPrice + "원");
                System.out.println(rating + " 등급 할인(" + rating.getDiscountPercent() + "%): -" + discountPrice + "원");
                System.out.println("최종 결제 금액: " + finalPrice + "원");

                for (CartItem cartItem : getCartItems()) {
                    Product product = cartItem.getProduct();
                    quantity = cartItem.getQuantity();
                    System.out.print(product.getName() + "재고가 " + product.getQuantity() +
                            "개 -> ");
                    product.reduceQuantity(quantity);
                    System.out.println(product.getQuantity() + "개로 업데이트되었습니다.");
                }
                //장바구니 비우기
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
