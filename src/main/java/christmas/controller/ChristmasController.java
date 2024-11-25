package christmas.controller;

import christmas.service.MenuInitializer;
import christmas.service.OrderService;
import christmas.util.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private static OrderService orderService;

    public ChristmasController(OrderService orderService) {
        ChristmasController.orderService = orderService;
    }

    public void start() {
        MenuInitializer.initMenuSetting();
        OutputView.printHello();
        int date = RetryHandler.handleRetry(InputView::askDate);
        getOrders();
    }

    private void getOrders() {
        RetryHandler.handleRetry(() -> {
            String orders = InputView.askOrder();
            orderService.createOrders(orders);
        });
    }
}
