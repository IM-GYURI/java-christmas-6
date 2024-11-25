package christmas.config;

import christmas.controller.ChristmasController;
import christmas.service.OrderService;

public enum AppConfig {

    INSTANCE;

    private final OrderService orderService = new OrderService();
    private final ChristmasController christmasController = new ChristmasController(orderService);

    public ChristmasController getChristmasController() {
        return christmasController;
    }
}
