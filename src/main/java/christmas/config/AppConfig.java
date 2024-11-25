package christmas.config;

import christmas.controller.ChristmasController;
import christmas.domain.ChristmasDDay;
import christmas.domain.Presentation;
import christmas.service.OrderService;
import christmas.service.PriceService;
import christmas.service.PromotionService;

public enum AppConfig {

    INSTANCE;

    private final Presentation presentation = new Presentation();
    private final ChristmasDDay christmasDDay = new ChristmasDDay();
    private final OrderService orderService = new OrderService();
    private final PriceService priceService = new PriceService();
    private final PromotionService promotionService = new PromotionService(presentation, christmasDDay);
    private final ChristmasController christmasController = new ChristmasController(
            orderService, priceService, promotionService);

    public ChristmasController getChristmasController() {
        return christmasController;
    }
}
