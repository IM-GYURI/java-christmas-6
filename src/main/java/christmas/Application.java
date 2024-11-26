package christmas;

import christmas.config.AppConfig;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ChristmasController controller = appConfig.getChristmasController();

        controller.start();
    }
}
