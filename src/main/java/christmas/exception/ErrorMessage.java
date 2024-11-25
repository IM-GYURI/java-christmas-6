package christmas.exception;

public enum ErrorMessage {
    FILE_LOAD_ERROR("파일을 불러오는 도중 오류가 발생하였습니다."),
    INVALID_INPUT("유효하지 않은 입력입니다. 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_TYPE_NULL("메뉴 타입은 빈 값일 수 없습니다."),
    MENU_NAME_NULL("메뉴 이름은 빈 값일 수 없습니다."),
    MENU_PRICE_MINUS("메뉴 가격은 음수일 수 없습니다."),
    ORDER_QUANTITY("메뉴 개수는 1 이상이어야 합니다."),
    MENU_NOT_FOUND("해당 메뉴를 찾을 수 없습니다."),
    TOO_MANY_INVALID_INPUT("유효하지 않은 입력이 5회 반복되어 프로그램이 종료됩니다.");

    private static final String PREFIX = "[ERROR] ";

    final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
