package christmas.service;

import christmas.domain.ChristmasDDay;
import christmas.domain.Presentation;
import christmas.dto.PresentationDto;
import christmas.dto.PromotionDetailDto;
import christmas.type.PromotionType;
import java.util.Optional;

public class PromotionService {

    private final Presentation presentation;
    private final ChristmasDDay christmasDDay;

    public PromotionService(Presentation presentation, ChristmasDDay christmasDDay) {
        this.presentation = presentation;
        this.christmasDDay = christmasDDay;
    }

    public Optional<PresentationDto> determinePresentation(int totalPrice) {
        if (presentation.isOverThreshold(totalPrice)) {
            return Optional.of(presentation.givePresentation());
        }

        return Optional.empty();
    }

    public Optional<PromotionDetailDto> getChristmasDDayDiscount(int date) {
        if (christmasDDay.isInRange(date)) {
            return Optional.of(
                    new PromotionDetailDto(PromotionType.CHRISTMAS_D_DAY, christmasDDay.getDDayDiscount(date)));
        }

        return Optional.empty();
    }
}
