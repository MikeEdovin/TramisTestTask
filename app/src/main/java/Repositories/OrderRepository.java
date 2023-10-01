package Repositories;

import Entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    Order findByOrderNumber(String orderNumber);
}
