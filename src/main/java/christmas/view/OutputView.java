package christmas.view;

import christmas.domain.Order;
import christmas.dto.PresentationDto;
import christmas.dto.PreviewDto;
import christmas.dto.PromotionDetailDto;
import java.util.List;
import java.util.Optional;

public class OutputView {

    private static final String MENU_AND_QUANTITY = "%s %d개";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String NOTHING = "없음";
    private static final String PROMOTION_AND_DISCOUNT = "%s: -%,d원";

    public static void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printPreview(PreviewDto preview) {
        System.out.printf("12월 %d일 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
                + System.lineSeparator() + System.lineSeparator(), preview.date());
        printOrders(preview.orders());
        printTotalPrice(preview.totalPrice());
        printPresentation(preview.presentation());
        printPromotionDetails(preview.promotionDetails());
    }

    private static void printOrders(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        orders.forEach(order -> {
            System.out.printf(MENU_AND_QUANTITY + System.lineSeparator()
                    , order.getName(), order.getQuantity());
        });
        System.out.println();
    }

    private static void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(PRICE_FORMAT + System.lineSeparator() + System.lineSeparator()
                , totalPrice);
    }

    private static void printPresentation(Optional<PresentationDto> presentation) {
        System.out.println("<증정 메뉴>");

        presentation.ifPresentOrElse(
                p -> {
                    System.out.printf(MENU_AND_QUANTITY + System.lineSeparator() + System.lineSeparator()
                            , presentation.get().menu().getName(), presentation.get().quantity());
                },
                () -> System.out.println(NOTHING + System.lineSeparator())
        );
    }

    private static void printPromotionDetails(List<PromotionDetailDto> promotionDetails) {
        System.out.println("<혜택 내역>");

        if (promotionDetails.isEmpty()) {
            System.out.println(NOTHING + System.lineSeparator());
            return;
        }

        promotionDetails.forEach(promotionDetail -> {
            System.out.printf(PROMOTION_AND_DISCOUNT + System.lineSeparator()
                    , promotionDetail.promotionType().getType(), promotionDetail.discountPrice());
        });

        System.out.println();
    }
}
