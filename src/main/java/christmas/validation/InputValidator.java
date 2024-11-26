package christmas.validation;

import christmas.domain.Order;
import christmas.exception.ErrorMessage;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String ORDER_REGEX = "([가-힣a-zA-Z0-9]+-\\d+)(,([가-힣a-zA-Z0-9]+-\\d+))*";

    public static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
    
    public static void validateRegex(String input) {
        if (!Pattern.compile(ORDER_REGEX).matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void validateDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public void validateOrders(List<Order> orders) {
        if (orders.stream()
                .map(Order::getName)
                .distinct()
                .count() != orders.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        orders.forEach(order -> {
            if (OrderRepository.exists(order.getName())) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }

            if (!MenuRepository.exists(order.getName())) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        });
    }
}
