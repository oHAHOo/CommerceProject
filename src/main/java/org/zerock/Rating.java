package org.zerock;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Rating {
    BRONZE(1,0),
    SILVER(2,5),
    GOLD(3,10),
    PLATINUM(4,15);

    //등급번호와 Rating매칭
    private static final Map<Integer, Rating> BY_CODE = Arrays.stream(Rating.values())
            .collect(Collectors.toUnmodifiableMap(Rating::getRatingNumber, Function.identity()));

    private final int ratingNumber; //등급 번호
    private final int discountPercent; //할인율

    Rating(int ratingNumber, int discountPercent) {
        this.ratingNumber = ratingNumber;
        this.discountPercent = discountPercent;
    }
    //할인율 반환
    public int getDiscountPercent() {
        return discountPercent;
    }
    //등급번호 반환
    public int getRatingNumber() {
        return ratingNumber;
    }
    //등급번호로 Rating조회
    public static Rating fromCode(int ratingNumber) {
        Rating rating = BY_CODE.get(ratingNumber);
        if (rating == null) {
            throw new IllegalArgumentException("존재하지 않는 등급입니다.");
        }
        return rating;
    }
}
