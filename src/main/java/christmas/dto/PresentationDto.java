package christmas.dto;

import christmas.domain.Menu;

public record PresentationDto(
        Menu menu,
        int quantity
) {
}
