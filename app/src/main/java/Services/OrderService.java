package Services;

import Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    String getOrderInJsonByOrderNumber(String orederNumber);

}
