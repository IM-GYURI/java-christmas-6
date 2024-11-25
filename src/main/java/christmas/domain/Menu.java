package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.type.MenuType;

public class Menu {

    private static final int MINIMUM_PRICE = 0;

    private final MenuType menuType;
    private final String name;
    private final int price;

    public Menu(MenuType menuType, String name, int price) {
        validate(menuType, name, price);

        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private void validate(MenuType menuType, String name, int price) {
        if (menuType == null) {
            throw new IllegalArgumentException(ErrorMessage.FILE_LOAD_ERROR.getMessage());
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.MENU_NAME_NULL.getMessage());
        }

        if (price < MINIMUM_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.MENU_PRICE_MINUS.getMessage());
        }
    }
}
