package christmas.repository;

import christmas.domain.Order;
import christmas.exception.ErrorMessage;
import christmas.type.MenuType;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final List<Order> orders = new ArrayList<>();

    private static final int ORDER_LIMIT = 20;

    public static List<Order> orders() {
        return orders.stream().toList();
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static boolean exists(String name) {
        return orders.stream()
                .anyMatch(order -> order.getName().equals(name));
    }

    public static void validateOrders() {
        if (isOnlyDrinks() || isOverLimit()) {
            clearOrders();
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static boolean isOnlyDrinks() {
        return orders.stream()
                .map(order -> MenuRepository.findByName(order.getName()).getMenuType())
                .allMatch(menuType -> menuType == MenuType.DRINK);
    }

    private static boolean isOverLimit() {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();

        return totalQuantity > ORDER_LIMIT;
    }

    private static void clearOrders() {
        orders.clear();
    }
}
