package Services;

import Entities.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    String getOrderInJsonByOrderNumber(String orderNumber) throws JsonProcessingException;
    Order saveOrderFromJsonToDb(String jsonOrder) throws JsonProcessingException;
    }

