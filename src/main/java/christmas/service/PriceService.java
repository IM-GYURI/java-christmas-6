package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;
import java.util.List;

public class PriceService {

    public int getTotalPrice() {
        List<Order> orders = OrderRepository.orders();
        return orders.stream()
                .mapToInt(this::getEachPrice)
                .sum();
    }

    private int getEachPrice(Order order) {
        Menu menu = MenuRepository.findByName(order.getName());
        return menu.getPrice() * order.getQuantity();
    }
}
