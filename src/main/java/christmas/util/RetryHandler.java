package christmas.util;

import christmas.domain.Order;
import christmas.validation.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RetryHandler {

    private final InputParser inputParser;
    private final InputValidator inputValidator;

    public RetryHandler(InputParser inputParser, InputValidator inputValidator) {
        this.inputParser = inputParser;
        this.inputValidator = inputValidator;
    }

    public int getDate() {
        return retryLogics(InputView::askDate, inputParser::parseDate, inputValidator::validateDate);
    }

    public List<Order> getOrders() {
        return retryLogics(InputView::askOrder, inputParser::parseOrders, inputValidator::validateOrders);
    }

    public <T> T retryLogics(Supplier<String> input, Function<String, T> parser,
                             Consumer<T> validator) {
        while (true) {
            try {
                String userInput = input.get();
                T parsedInput = parser.apply(userInput);
                validator.accept(parsedInput);
                return parsedInput;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
