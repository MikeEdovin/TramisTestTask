package Services;

import Configs.ServiceConfig;
import Entities.Order;
import Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)

class OrderServiceImplTest {
    @Mock
    OrderRepository repository;
    @InjectMocks
    OrderServiceImpl service;
  @InjectMocks
    ObjectMapper objectMapper;





    @Test
    void getOrderInJsonByOrderNumberShouldReturnExpectedValue() throws JsonProcessingException {
        Order order=new Order();
        order.setId(1);
        order.setOrderNumber("ON1");
        order.setClient("Sinergia");
        Mockito.when(repository.findByOrderNumber("ON1")).thenReturn(order);
        System.out.println(service.getOrderInJsonByOrderNumber("ON1"));

    }
}