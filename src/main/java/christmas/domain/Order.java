package christmas.domain;

import christmas.exception.ErrorMessage;

public class Order {

    private static final int MINIMUM_QUANTITY = 1;

    private final String name;
    private final int quantity;

    public Order(String name, int quantity) {
        validate(name, quantity);

        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validate(String name, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.MENU_NAME_NULL.getMessage());
        }

        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_QUANTITY.getMessage());
        }
    }
}
