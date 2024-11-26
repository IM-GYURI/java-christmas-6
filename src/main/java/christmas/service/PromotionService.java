package christmas.service;

import christmas.domain.DDayPromotion;
import christmas.domain.Order;
import christmas.domain.PresentationPromotion;
import christmas.domain.SpecialPromotion;
import christmas.domain.WeekdayPromotion;
import christmas.domain.WeekendPromotion;
import christmas.dto.PresentationDto;
import christmas.dto.PreviewDto;
import christmas.dto.PromotionDetailDto;
import christmas.repository.OrderRepository;
import christmas.type.Badge;
import christmas.type.PromotionType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionService {

    private static final int MINIMUM_PRICE_FOR_PROMOTION = 10_000;

    private final PresentationPromotion presentationPromotion;
    private final DDayPromotion DDayPromotion;
    private final WeekdayPromotion weekdayPromotion;
    private final WeekendPromotion weekendPromotion;
    private final SpecialPromotion specialPromotion;

    public PromotionService(PresentationPromotion presentationPromotion, DDayPromotion DDayPromotion,
                            WeekdayPromotion weekdayPromotion, WeekendPromotion weekendPromotion,
                            SpecialPromotion specialPromotion) {
        this.presentationPromotion = presentationPromotion;
        this.DDayPromotion = DDayPromotion;
        this.weekdayPromotion = weekdayPromotion;
        this.weekendPromotion = weekendPromotion;
        this.specialPromotion = specialPromotion;
    }

    public PreviewDto getPreview(int date, int totalPrice) {
        List<Order> orders = OrderRepository.orders();
        List<PromotionDetailDto> promotionDetails = new ArrayList<>();

        if (totalPrice >= MINIMUM_PRICE_FOR_PROMOTION) {
            promotionDetails = getPromotionDetails(date, orders);
        }

        Optional<PresentationDto> presentation = determinePresentation(totalPrice);
        getPresentationPromotion(presentation)
                .ifPresent(promotionDetails::add);

        int discountPrice = getDiscountPrice(promotionDetails);

        return new PreviewDto(date, orders, totalPrice, presentation, promotionDetails,
                discountPrice, getResultPrice(totalPrice, discountPrice, presentation), getBadge(discountPrice));
    }

    private List<PromotionDetailDto> getPromotionDetails(int date, List<Order> orders) {
        List<PromotionDetailDto> promotionDetails = new ArrayList<>();

        getChristmasDDayDiscount(date).ifPresent(promotionDetails::add);
        getWeekdayDiscount(date, orders).ifPresent(promotionDetails::add);
        getWeekendDiscount(date, orders).ifPresent(promotionDetails::add);
        getSpecialDiscount(date).ifPresent(promotionDetails::add);

        return promotionDetails;
    }

    private Optional<PresentationDto> determinePresentation(int totalPrice) {
        if (presentationPromotion.isOverThreshold(totalPrice)) {
            return Optional.of(presentationPromotion.givePresentation());
        }

        return Optional.empty();
    }

    private Optional<PromotionDetailDto> getPresentationPromotion(Optional<PresentationDto> presentation) {
        return presentation.map(p ->
                new PromotionDetailDto(PromotionType.PRESENT, p.menu().getPrice())
        );
    }

    private Optional<PromotionDetailDto> getChristmasDDayDiscount(int date) {
        if (DDayPromotion.isInRange(date)) {
            return Optional.of(
                    new PromotionDetailDto(PromotionType.CHRISTMAS_D_DAY, DDayPromotion.getDDayDiscount(date)));
        }

        return Optional.empty();
    }

    private Optional<PromotionDetailDto> getWeekdayDiscount(int date, List<Order> orders) {
        if (weekdayPromotion.isWeekday(date)) {
            return Optional.of(
                    new PromotionDetailDto(PromotionType.WEEKDAY, weekdayPromotion.getWeekdayDiscount(orders))
            );
        }

        return Optional.empty();
    }

    private Optional<PromotionDetailDto> getWeekendDiscount(int date, List<Order> orders) {
        if (weekendPromotion.isWeekend(date)) {
            return Optional.of(
                    new PromotionDetailDto(PromotionType.WEEKEND, weekendPromotion.getWeekendDiscount(orders))
            );
        }

        return Optional.empty();
    }

    private Optional<PromotionDetailDto> getSpecialDiscount(int date) {
        if (specialPromotion.isSpecialDay(date)) {
            return Optional.of(
                    new PromotionDetailDto(PromotionType.SPECIAL, specialPromotion.getSpecialDiscount())
            );
        }

        return Optional.empty();
    }

    private int getDiscountPrice(List<PromotionDetailDto> promotionDetails) {
        if (promotionDetails.isEmpty()) {
            return 0;
        }

        return promotionDetails.stream()
                .mapToInt(PromotionDetailDto::discountPrice)
                .sum();
    }

    private int getResultPrice(int totalPrice, int discountPrice, Optional<PresentationDto> presentation) {
        return presentation.map(presentationDto -> totalPrice - discountPrice + presentationDto.menu().getPrice())
                .orElseGet(() -> totalPrice - discountPrice);
    }

    private Badge getBadge(int discountPrice) {
        if (discountPrice < Badge.STAR.getThreshold()) {
            return Badge.NOTHING;
        }

        if (discountPrice < Badge.TREE.getThreshold()) {
            return Badge.STAR;
        }

        if (discountPrice < Badge.SANTA.getThreshold()) {
            return Badge.TREE;
        }

        return Badge.SANTA;
    }
}
