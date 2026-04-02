package org.zerock;

//상품 객체 클래스
public class Product {

    private String name; //상품명
    private int price; //가격
    private String description; //설명
    private int quantity; //재고수량

    public Product(String name, int price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
    //상품 이름 반환
    public String getName() {
        return name;
    }
    //상품 가격 반환
    public int getPrice() {
        return price;
    }
    //상품 설명 반환
    public String getDescription() {
        return description;
    }
    //상품 재고 반환
    public int getQuantity() {
        return quantity;
    }
    //재고 감소 메서드
    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }
    //가격 수정 메서드
    public void setPrice(int price) {
        this.price = price;
    }
    //설명 수정 메서드
    public void setDescription(String description) {
        this.description = description;
    }
    //재고 수정 메서드
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
