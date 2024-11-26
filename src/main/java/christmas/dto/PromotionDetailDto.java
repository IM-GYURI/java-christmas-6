package christmas.dto;

import christmas.type.PromotionType;

public record PromotionDetailDto(
        PromotionType promotionType,
        int discountPrice
) {
}
