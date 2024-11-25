package christmas.service;

import christmas.domain.Order;
import christmas.exception.ErrorMessage;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;
import christmas.util.NumberParser;
import java.util.Arrays;
import java.util.regex.Pattern;

public class OrderService {

    private static final String ORDER_REGEX = "([가-힣a-zA-Z0-9]+-\\d+)(,([가-힣a-zA-Z0-9]+-\\d+))*";
    private static final String ORDER_SPLIT = ",";
    private static final String NAME_QUANTITY_SPLIT = "-";
    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public void createOrders(String input) {
        validateRegex(input);

        String[] orders = input.split(ORDER_SPLIT);
        Arrays.stream(orders)
                .forEach(this::addOrder);

        OrderRepository.validateOrders();
    }

    private void validateRegex(String input) {
        if (!Pattern.compile(ORDER_REGEX).matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void addOrder(String eachOrder) {
        String[] parts = eachOrder.split(NAME_QUANTITY_SPLIT);
        String name = parts[NAME_INDEX];

        if (OrderRepository.exists(name)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        if (!MenuRepository.exists(name)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }

        OrderRepository.addOrder(new Order(parts[NAME_INDEX], NumberParser.parseNumber(parts[QUANTITY_INDEX])));
    }
}
