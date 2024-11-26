package christmas.domain;

import christmas.repository.MenuRepository;
import christmas.type.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayPromotion {

    private static final List<DayOfWeek> weekdays = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    private static final int DISCOUNT = 2_023;

    public boolean isWeekday(int date) {
        LocalDate changedDate = LocalDate.of(2023, 12, date);

        return weekdays.contains(DayOfWeek.from(changedDate.getDayOfWeek()));
    }

    public int getWeekdayDiscount(List<Order> orders) {
        return orders.stream()
                .filter(order -> {
                    Menu menu = MenuRepository.findByName(order.getName());
                    return menu.getMenuType() == MenuType.DESSERT;
                })
                .mapToInt(order -> order.getQuantity() * DISCOUNT)
                .sum();
    }
}
