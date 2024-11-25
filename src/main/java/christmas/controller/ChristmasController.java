package christmas.controller;

import christmas.domain.Order;
import christmas.dto.PresentationDto;
import christmas.dto.PreviewDto;
import christmas.dto.PromotionDetailDto;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import christmas.service.PriceService;
import christmas.service.PromotionService;
import christmas.util.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChristmasController {

    private final OrderService orderService;
    private final PriceService priceService;
    private final PromotionService promotionService;

    public ChristmasController(OrderService orderService, PriceService priceService,
                               PromotionService promotionService) {
        this.orderService = orderService;
        this.priceService = priceService;
        this.promotionService = promotionService;
    }

    public void start() {
        OutputView.printHello();
        int date = RetryHandler.handleRetry(InputView::askDate);
        getOrders();

        printPreview(date);
    }

    private void getOrders() {
        RetryHandler.handleRetry(() -> {
            String orders = InputView.askOrder();
            orderService.createOrders(orders);
        });
    }

    private List<PromotionDetailDto> getPromotionDetails(int date) {
        List<PromotionDetailDto> promotionDetails = new ArrayList<>();

        if (promotionService.getChristmasDDayDiscount(date).isPresent()) {
            promotionDetails.add(promotionService.getChristmasDDayDiscount(date).get());
        }

        return promotionDetails;
    }

    private void printPreview(int date) {
        List<Order> orders = OrderRepository.orders();
        int totalPrice = priceService.getTotalPrice();
        Optional<PresentationDto> presentation = promotionService.determinePresentation(totalPrice);

        List<PromotionDetailDto> promotionDetails = getPromotionDetails(date);

        PreviewDto preview = new PreviewDto(date, orders, totalPrice, presentation, promotionDetails);
        OutputView.printPreview(preview);
    }
}
