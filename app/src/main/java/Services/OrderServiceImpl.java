package Services;

import Entities.Order;
import Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;

public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;
    @Override
    public String getOrderInJsonByOrderNumber(String orderNumber) {
        Order order=repository.findByOrderNumber(orderNumber);

        return null;
    }
}
