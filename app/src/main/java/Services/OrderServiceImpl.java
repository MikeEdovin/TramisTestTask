package Services;

import Entities.Order;
import Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    @Transactional
    public String getOrderInJsonByOrderNumber(String orderNumber) throws JsonProcessingException {
        Order order=repository.findByOrderNumber(orderNumber);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
    }

    @Override
    @Transactional
    public boolean saveOrderFromJsonToDb(String jsonOrder) throws JsonProcessingException {
        Order order=objectMapper.readValue(jsonOrder, Order.class);
        if(order!=null){
            repository.save(order);
            return true;
        }
        return false;
    }
}
