package christmas.domain;

import java.util.List;

public class SpecialPromotion {

    private static final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
    private static final int DISCOUNT = 1_000;

    public boolean isSpecialDay(int date) {
        return specialDays.contains(date);
    }

    public int getSpecialDiscount() {
        return DISCOUNT;
    }
}
