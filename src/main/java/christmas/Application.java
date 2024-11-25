package christmas;

import christmas.config.AppConfig;
import christmas.controller.ChristmasController;
import christmas.service.MenuInitializer;

public class Application {
    public static void main(String[] args) {
        MenuInitializer.initMenuSetting();

        ChristmasController controller = AppConfig.INSTANCE.getChristmasController();
        controller.start();
    }
}
