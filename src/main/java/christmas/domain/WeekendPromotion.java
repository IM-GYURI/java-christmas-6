package christmas.domain;

import christmas.repository.MenuRepository;
import christmas.type.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendPromotion {

    private static final List<DayOfWeek> weekdays = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int DISCOUNT = 2_023;

    public boolean isWeekend(int date) {
        LocalDate changedDate = LocalDate.of(2023, 12, date);

        return weekdays.contains(DayOfWeek.from(changedDate.getDayOfWeek()));
    }

    public int getWeekendDiscount(List<Order> orders) {
        return orders.stream()
                .map(order -> MenuRepository.findByName(order.getName()))
                .filter(menu -> menu.getMenuType() == MenuType.MAIN)
                .mapToInt(menu -> DISCOUNT)
                .sum();
    }
}
