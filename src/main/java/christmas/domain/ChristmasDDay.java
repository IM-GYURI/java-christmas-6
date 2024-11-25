package christmas.domain;

public class ChristmasDDay {

    private static final int START_DISCOUNT = 1_000;
    private static final int ADD_DISCOUNT = 100;
    private static final int START_DATE = 1;
    private static final int END_DATE = 25;

    public boolean isInRange(int date) {
        return date >= START_DATE && date <= END_DATE;
    }

    public int getDDayDiscount(int date) {
        int difference = date - START_DATE;
        return START_DISCOUNT + difference * ADD_DISCOUNT;
    }
}
