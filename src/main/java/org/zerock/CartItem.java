package org.zerock;

//장바구니에 담기는 객체
public class CartItem {
    private Product product; //상품 객체
    private int quantity; //상품 수량


    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    //상품 객체 호출
    public Product getProduct() {
        return product;
    }
    //장바구니 수량 호출
    public int getQuantity() {
        return quantity;
    }
    //수량 1증가
    public void addQuantity() {
        quantity++;
    }
    //총 가격 계산
    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }
}


