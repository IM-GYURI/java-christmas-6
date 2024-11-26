package christmas.controller;

import christmas.domain.Order;
import christmas.dto.PreviewDto;
import christmas.repository.OrderRepository;
import christmas.service.PriceService;
import christmas.service.PromotionService;
import christmas.util.RetryHandler;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private final RetryHandler retryHandler;
    private final PriceService priceService;
    private final PromotionService promotionService;

    public ChristmasController(RetryHandler retryHandler, PriceService priceService,
                               PromotionService promotionService) {
        this.retryHandler = retryHandler;
        this.priceService = priceService;
        this.promotionService = promotionService;
    }

    public void start() {
        OutputView.printHello();
        int date = retryHandler.getDate();
        getOrders();
        printPreview(date);
    }

    private void getOrders() {
        List<Order> orders = retryHandler.getOrders();
        orders.forEach(OrderRepository::addOrder);

        OrderRepository.validateOrders();
    }

    private void printPreview(int date) {
        PreviewDto preview = promotionService.getPreview(date, priceService.getTotalPrice());
        OutputView.printPreview(preview);
    }
}
