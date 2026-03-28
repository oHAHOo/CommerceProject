package org.zerock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category("전자제품");
        Category clothes = new Category("의류");
        Category groceries = new Category("식품");

        List<Category> categories = new ArrayList<>();
        CommerceSystem commerceSystem = new CommerceSystem(categories, scanner);

        categories.add(electronics);
        categories.add(clothes);
        categories.add(groceries);

        Product electronic1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 5);
        Product electronic2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 4);
        Product electronic3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북",5);
        Product electronic4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 7);

        electronics.addProduct(electronic1);
        electronics.addProduct(electronic2);
        electronics.addProduct(electronic3);
        electronics.addProduct(electronic4);

        Product clothes1 = new Product("오버핏 티셔츠", 29000, "편안한 착용감의 기본 티셔츠", 20);
        Product clothes2 = new Product("슬림핏 청바지", 59000, "스타일리시한 데님 팬츠", 15);
        Product clothes3 = new Product("후드 집업", 49000, "간절기에 입기 좋은 아우터", 10);
        Product clothes4 = new Product("패딩 점퍼", 129000, "겨울용 보온 아우터", 8);

        clothes.addProduct(clothes1);
        clothes.addProduct(clothes2);
        clothes.addProduct(clothes3);
        clothes.addProduct(clothes4);

        Product food1 = new Product("사과 1kg", 8000, "신선한 국내산 사과", 30);
        Product food2 = new Product("우유 1L", 2500, "고소한 일반 우유", 25);
        Product food3 = new Product("계란 30구", 9000, "신선한 대란", 20);
        Product food4 = new Product("즉석밥 6개입", 12000, "간편하게 먹는 즉석밥", 18);

        groceries.addProduct(food1);
        groceries.addProduct(food2);
        groceries.addProduct(food3);
        groceries.addProduct(food4);

        commerceSystem.start();
    }
}