package christmas.type;

import christmas.exception.ErrorMessage;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MenuType {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private static final Map<String, MenuType> MENU_TYPES = Stream.of(values())
            .collect(Collectors.toMap(menuType -> menuType.type, menuType -> menuType));

    final String type;

    MenuType(final String type) {
        this.type = type;
    }

    public static MenuType getMenuTypeFromInput(String input) {
        MenuType menuType = MENU_TYPES.get(input);

        if (menuType == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }

        return menuType;
    }
}
