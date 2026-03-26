package org.zerock;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    List<Product> products;
    Scanner scanner;

    CommerceSystem(List<Product> products, Scanner scanner) {
        this.products = products;
        this.scanner = scanner;
    }

    public void start(){
        int menu;

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for(int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i+1) + ". " + p.getName() + "   | " + p.getPrice() + "원 | " + p.getDescription());
        }
        System.out.println("0. 종료   |프로그램 종료");

        menu = scanner.nextInt();
        if(menu == 0){
            System.out.println("커머스 플랫폼을 종료합니다.");
        }
    }
}
