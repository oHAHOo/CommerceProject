package org.zerock;

public class Customer {
    private String name;
    private String email;
    private String rating;

    public Customer(String name, String email, String rating) {
        this.name = name;
        this.email = email;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRating() {
        return rating;
    }
}
