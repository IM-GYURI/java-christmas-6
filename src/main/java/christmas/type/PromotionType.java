package christmas.type;

public enum PromotionType {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("특별 할인"),
    PRESENT("증정 이벤트");

    final String type;

    PromotionType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
