package org.zerock;

import org.zerock.shoppingCart.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category("전자제품");
        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 5));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 4));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 5));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 7));

        Category clothes = new Category("의류");
        clothes.addProduct(new Product("오버핏 티셔츠", 29000, "편안한 착용감의 기본 티셔츠", 20));
        clothes.addProduct(new Product("슬림핏 청바지", 59000, "스타일리시한 데님 팬츠", 15));
        clothes.addProduct(new Product("후드 집업", 49000, "간절기에 입기 좋은 아우터", 10));
        clothes.addProduct(new Product("패딩 점퍼", 129000, "겨울용 보온 아우터", 8));

        Category groceries = new Category("식품");
        groceries.addProduct(new Product("사과 1kg", 8000, "신선한 국내산 사과", 30));
        groceries.addProduct(new Product("우유 1L", 2500, "고소한 일반 우유", 25));
        groceries.addProduct(new Product("계란 30구", 9000, "신선한 대란", 20));
        groceries.addProduct(new Product("즉석밥 6개입", 12000, "간편하게 먹는 즉석밥", 18));

        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(clothes);
        categories.add(groceries);

        CartService cartService = new CartService(scanner);
        AdminService adminService = new AdminService(categories, scanner);

        CommerceSystem commerceSystem = new CommerceSystem(categories, scanner, cartService, adminService);

        commerceSystem.start();
    }
}