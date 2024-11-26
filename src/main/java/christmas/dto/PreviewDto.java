package christmas.dto;

import christmas.domain.Order;
import christmas.type.Badge;
import java.util.List;
import java.util.Optional;

public record PreviewDto(
        int date,
        List<Order> orders,
        int totalPrice,
        Optional<PresentationDto> presentation,
        List<PromotionDetailDto> promotionDetails,
        int discountPrice,
        int resultPrice,
        Badge badge
) {
}
