package christmas.domain;

import christmas.dto.PresentationDto;
import christmas.repository.MenuRepository;

public class Presentation {

    private static final int PRICE_THRESHOLD = 120_000;
    private static final Menu PRESENT_MENU = MenuRepository.findByName("샴페인");
    private static final int QUANTITY = 1;

    public boolean isOverThreshold(int totalPrice) {
        return totalPrice >= PRICE_THRESHOLD;
    }

    public PresentationDto givePresentation() {
        return new PresentationDto(PRESENT_MENU, QUANTITY);
    }
}
