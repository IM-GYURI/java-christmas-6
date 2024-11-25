package christmas;

import christmas.config.AppConfig;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasController controller = AppConfig.INSTANCE.getChristmasController();
        controller.start();
    }
}
