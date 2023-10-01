package Services;

import Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    String getOrderInJsonByOrderNumber(String orderNumber) throws JsonProcessingException;
    boolean saveOrderFromJsonToDb(String jsonOrder) throws JsonProcessingException;
    }

