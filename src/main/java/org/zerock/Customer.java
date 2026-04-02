package org.zerock;

//고객 정보 글래스
public class Customer {
    private String name;
    private String email;
    private String rating;

    public Customer(String name, String email, String rating) {
        this.name = name; //고객 이름
        this.email = email; //고객 이메일
        this.rating = rating; //고객 등급
    }
    //이름 반환
    public String getName() {
        return name;
    }
    //이메일 반환
    public String getEmail() {
        return email;
    }
    //등급 반환
    public String getRating() {
        return rating;
    }
}
