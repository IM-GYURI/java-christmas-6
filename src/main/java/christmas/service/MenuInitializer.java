package christmas.service;

import christmas.domain.Menu;
import christmas.exception.ErrorMessage;
import christmas.repository.MenuRepository;
import christmas.type.MenuType;
import christmas.validation.InputValidator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MenuInitializer {

    private static final String FILE_PATH = "src/main/resources/menus.md";
    private static final String TYPE_SPLIT = ":";
    private static final String MENU_SPLIT = ",";
    private static final String NAME_PRICE_SPLIT = "-";
    private static final int MENU_TYPE_INDEX = 0;
    private static final int MENUS_INDEX = 1;
    private static final int NAME_INDEX = 0;
    private static final int PRICE_INDEX = 1;

    public static void initMenuSetting() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.lines()
                    .forEach(MenuInitializer::parseEachLine);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.FILE_LOAD_ERROR.getMessage());
        }
    }

    private static void parseEachLine(String line) {
        InputValidator.validateInput(line);
        String[] parts = line.split(TYPE_SPLIT);
        MenuType menuType = MenuType.getMenuTypeFromInput(parts[MENU_TYPE_INDEX]);
        String[] menus = parts[MENUS_INDEX].split(MENU_SPLIT);

        Arrays.stream(menus)
                .forEach(menuDetail -> addMenu(menuType, menuDetail));
    }

    private static void addMenu(MenuType menuType, String menuDetail) {
        String[] parts = menuDetail.split(NAME_PRICE_SPLIT);
        String name = parts[NAME_INDEX];
        int price = Integer.parseInt(parts[PRICE_INDEX]);

        MenuRepository.addMenu(new Menu(menuType, name, price));
    }
}
