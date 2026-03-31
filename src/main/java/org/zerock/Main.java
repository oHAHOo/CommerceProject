package org.zerock;

import org.zerock.Admin.Admin;
import org.zerock.shoppingCart.CartService;
import org.zerock.shoppingCart.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> electronicsProducts = List.of(
                new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 5),
                new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 4),
                new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 5),
                new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 7)
        );

        List<Product> clothesProducts = List.of(
                new Product("오버핏 티셔츠", 29000, "편안한 착용감의 기본 티셔츠", 20),
                new Product("슬림핏 청바지", 59000, "스타일리시한 데님 팬츠", 15),
                new Product("후드 집업", 49000, "간절기에 입기 좋은 아우터", 10),
                new Product("패딩 점퍼", 129000, "겨울용 보온 아우터", 8)
        );

        List<Product> groceriesProducts = List.of(
                new Product("사과 1kg", 8000, "신선한 국내산 사과", 30),
                new Product("우유 1L", 2500, "고소한 일반 우유", 25),
                new Product("계란 30구", 9000, "신선한 대란", 20),
                new Product("즉석밥 6개입", 12000, "간편하게 먹는 즉석밥", 18)
        );

        Category electronics = new Category("전자제품", electronicsProducts);
        Category clothes = new Category("의류", clothesProducts);
        Category groceries = new Category("식품", groceriesProducts);

        List<Category> categories = List.of(electronics, clothes, groceries);

        ShoppingCart shoppingCart = new ShoppingCart();
        CartService cartService = new CartService(shoppingCart, scanner);
        Admin admin = new Admin();

        CommerceSystem commerceSystem =
                new CommerceSystem(categories, shoppingCart, scanner, cartService, admin);

        commerceSystem.start();
    }
}