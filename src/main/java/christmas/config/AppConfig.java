package christmas.config;

import christmas.controller.ChristmasController;
import christmas.domain.DDayPromotion;
import christmas.domain.PresentationPromotion;
import christmas.domain.SpecialPromotion;
import christmas.domain.WeekdayPromotion;
import christmas.domain.WeekendPromotion;
import christmas.service.MenuInitializer;
import christmas.service.PriceService;
import christmas.service.PromotionService;
import christmas.util.InputParser;
import christmas.util.RetryHandler;
import christmas.validation.InputValidator;

public class AppConfig {

    public ChristmasController getChristmasController() {
        MenuInitializer.initMenuSetting();

        RetryHandler retryHandler = getRetryHandler();
        PriceService priceService = new PriceService();
        PromotionService promotionService = getPromotionService();

        return new ChristmasController(retryHandler, priceService, promotionService);
    }

    private RetryHandler getRetryHandler() {
        return new RetryHandler(new InputParser(), new InputValidator());
    }

    private PromotionService getPromotionService() {
        PresentationPromotion presentationPromotion = new PresentationPromotion();
        DDayPromotion DDayPromotion = new DDayPromotion();
        WeekdayPromotion weekdayPromotion = new WeekdayPromotion();
        WeekendPromotion weekendPromotion = new WeekendPromotion();
        SpecialPromotion specialPromotion = new SpecialPromotion();

        return new PromotionService(presentationPromotion, DDayPromotion, weekdayPromotion, weekendPromotion,
                specialPromotion);
    }
}
