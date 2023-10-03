package Services;

import Entities.Order;
import Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    @Transactional
    public String getOrderInJsonByOrderNumber(String orderNumber) throws JsonProcessingException {
        Order order=repository.findByOrderNumber(orderNumber);
        return objectMapper.writeValueAsString(order);
    }

    @Override
    @Transactional
    public Order saveOrderFromJsonToDb(String jsonOrder) throws JsonProcessingException {
        return repository.save(objectMapper.readValue(jsonOrder, Order.class));
    }
}
