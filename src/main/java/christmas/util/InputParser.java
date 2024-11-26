package christmas.util;

import christmas.domain.Order;
import christmas.exception.ErrorMessage;
import christmas.validation.InputValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String ORDER_SPLIT = ",";
    private static final String NAME_QUANTITY_SPLIT = "-";
    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;


    public static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public int parseDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public List<Order> parseOrders(String input) {
        InputValidator.validateRegex(input);

        List<Order> orders = new ArrayList<>();
        String[] parts = input.split(ORDER_SPLIT);

        Arrays.stream(parts)
                .forEach(order -> {
                    Order newOrder = parseOrder(order);
                    orders.add(newOrder);
                });

        return orders;
    }

    private Order parseOrder(String eachOrder) {
        String[] parts = eachOrder.split(NAME_QUANTITY_SPLIT);
        String name = parts[NAME_INDEX];

        return new Order(name, InputParser.parseNumber(parts[QUANTITY_INDEX]));
    }
}
