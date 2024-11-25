package christmas.repository;

import christmas.domain.Menu;
import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static final List<Menu> menus = new ArrayList<>();

    public static List<Menu> menus() {
        return menus.stream().toList();
    }

    public static void addMenu(Menu menu) {
        menus.add(menu);
    }

    public static boolean exists(String name) {
        return menus.stream()
                .anyMatch(menu -> menu.getName().equals(name));
    }

    public static Menu findByName(String name) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_NOT_FOUND.getMessage()));
    }

}
