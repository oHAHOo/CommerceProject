package org.zerock;

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

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }
    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
