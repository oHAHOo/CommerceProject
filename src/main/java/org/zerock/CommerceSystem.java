package org.zerock;

import java.util.List;
import java.util.Scanner;

//커머스 시스템 클래스
public class CommerceSystem {
    private List<Category> categories; //카테고리 리스트
    private Scanner scanner;
    private Category selectedCategory; //선택된 카테고리
    private CartService cartService;
    private AdminService adminService;

    private int selectedMenu; //선택된 메뉴
    private int selectedProductNumber; //선택된 상품
    private boolean authority;

    public CommerceSystem(List<Category> categories, Scanner scanner, CartService cartService, AdminService adminService) {
        this.scanner = scanner;
        this.categories = categories;
        this.cartService = cartService;
        this.adminService = adminService;
    }
    //프로그램 시작 메서드
    public void start() {
        while (true) {
            selectedMenu = selectMenu();
            //프로그램 종료
            if (selectedMenu == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            //장바구니가 비어있을때 4,5번 선택시
            if ((selectedMenu == 4 || selectedMenu == 5) && cartService.getCartItems().isEmpty()) {
                System.out.println("존재하지 않는 메뉴입니다.");
                continue;
            }

            //1~3번 메뉴
            if (selectedMenu >= 1 && selectedMenu <=3) {
                selectProduct();
                continue;
            }

            //4,5,6번 메뉴
            switch (selectedMenu) {
                case 4: {//장바구니 출력, 주문
                    cartService.printCart();
                    cartService.order();
                    break;
                }
                case 5: {//장바구니 상품 제거
                    cartService.printCart();
                    if (cartService.getCartItems().isEmpty()) {
                        break;
                    }
                    System.out.print("제거할 상품명을 입력하세요: ");
                    String productName = scanner.nextLine().trim();
                    cartService.removeProductByName(productName);
                    break;
                }
                case 6: {//관리자 메뉴
                    if (authority == false){
                        for (int i = 0; i < 3; i++) {
                            System.out.println("관리자 비밀번호를 입력해주세요:");
                            String password = scanner.nextLine().trim();

                            if (adminService.checkAdmin(password)) {
                                authority = true;
                                break;
                            }
                            System.out.println("비밀번호가 일치하지 않습니다.");                        }
                        //3회 실패하면 메인으로
                        if(authority == false) {
                            System.out.println("비밀번호 3회 입력 실패로 메인 메뉴로 돌아갑니다.");
                            break;
                        }
                    }
                    if (authority == true) {
                        //관리자 메뉴 출력
                        while (true) {
                            System.out.println("\n[ 관리자 모드 ]");
                            System.out.println("1. 상품 추가");
                            System.out.println("2. 상품 수정");
                            System.out.println("3. 상품 삭제");
                            System.out.println("4. 전체 상품 현황");
                            System.out.println("0. 메인으로 돌아가기");

                            int adminMenu;
                            try {
                                adminMenu = Integer.parseInt(scanner.nextLine().trim());
                            } catch (NumberFormatException e) {
                                System.out.println("숫자를 입력해주세요.");
                                continue;
                            }

                            if (adminMenu == 0) {
                                break;
                            }

                            switch (adminMenu) {
                                case 1://상품 추가
                                    adminService.addProductByAdmin();
                                    break;
                                case 2://상품 수정
                                    adminService.updateProductByAdmin();
                                    break;
                                case 3://상품 삭제
                                    adminService.deleteProductByAdmin();
                                    break;
                                case 4://모든 상품 출력
                                    adminService.printAllProducts();
                                    break;
                                default:
                                    System.out.println("잘못된 입력입니다.");
                            }
                        }
                    }
                }
            }
        }
    }
    //메인 메뉴 출력 메서드
    private int selectMenu() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인]");
        //카테고리 출력
        for(int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.println(i+1 + ". " + category.getName());
        }
        System.out.println("0. 종료   |프로그램 종료\n");
        //장바구니에 상품이 존재하면 출력
        if(!cartService.getCartItems().isEmpty()){
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인  | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소     | 진행중인 주문을 취소합니다");
        }
        System.out.println("6. 관리자 모드");

        while (true) {
            try {
                selectedMenu = Integer.parseInt(scanner.nextLine().trim());
                return selectedMenu;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
    //상품 선택 메서드
    private int selectProduct() {

        selectedCategory = categories.get(selectedMenu -1);
        List<Product> products = selectedCategory.getProducts();
        //선택한 카테고리 출력
        System.out.println("\n[ " + selectedCategory.getName() + " 카테고리 ]");
        System.out.println("1. 전체 상품 보기");
        System.out.println("2. 가격대별 필터링 (100만원 이하)");
        System.out.println("3. 가격대별 필터링 (100만원 초과)");
        System.out.println("0. 뒤로가기");

        int filterMenu;
        while (true) {
            try {
                filterMenu = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
        //필터 적용 메뉴
        List<Product> filteredProducts;
        switch (filterMenu) {
            case 1:
                System.out.println("\n[ 전체 상품 목록 ]");
                filteredProducts = products;
                break;
            case 2:
                System.out.println("\n[ 100만원 이하 상품 목록 ]");
                filteredProducts = products.stream().filter(product -> product.getPrice() <= 1000000).toList();
                break;
            case 3:
                System.out.println("\n[ 100만원 초과 상품 목록 ]");
                filteredProducts = products.stream().filter(product -> product.getPrice() > 1000000).toList();
                break;
            case 0:
                return 0;
            default:
                System.out.println("잘못된 입력입니다.");
                return 0;
        }
        if (filteredProducts.isEmpty()) {
            System.out.println("조건에 맞는 상품이 없습니다");
            return 0;
        }
        //상품 목록 출력
        for (int i = 0; i < filteredProducts.size(); i++) {
            Product product = filteredProducts.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " | "
                    + product.getPrice() + "원 | " + product.getDescription()
                    + " | 재고: " + product.getQuantity() + "개");
        }
        System.out.println("0. 뒤로가기");
        //상품 선택
        while (true) {
            try {
                selectedProductNumber = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }

        if (selectedProductNumber == 0) {
            return 0;
        }
        //상품이 없을 경우
        if (selectedProductNumber < 0 || selectedProductNumber > filteredProducts.size()) {
            System.out.println("존재하지 않는 상품입니다.");
            return 0;
        }
        //선택한 상품 출력
        Product selectedProduct = filteredProducts.get(selectedProductNumber - 1);
        System.out.println("\n선택한 상품: " + selectedProduct.getName() + " | "
                + selectedProduct.getPrice() + "원 | "
                + selectedProduct.getDescription() + " | 재고: "
                + selectedProduct.getQuantity() + "개");

        cartService.addCartItem(selectedProduct);
        return selectedProductNumber;
    }

}
