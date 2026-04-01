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

    private static final Map<Integer, Rating> BY_CODE = Arrays.stream(Rating.values())
            .collect(Collectors.toUnmodifiableMap(Rating::getRatingNumber, Function.identity()));

    private final int ratingNumber;
    private final int discountPercent;

    Rating(int ratingNumber, int discountPercent) {
        this.ratingNumber = ratingNumber;
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public static Rating fromCode(int ratingNumber) {
        Rating rating = BY_CODE.get(ratingNumber);
        if (rating == null) {
            throw new IllegalArgumentException("존재하지 않는 등급입니다.");
        }
        return rating;
    }
}
