package Configs;

import Services.OrderService;
import Services.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean(name = "objectMapper")
    ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean(name="orderService")
    OrderService orderService(){
        return new OrderServiceImpl();
    }

}
