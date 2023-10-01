package tramis.testTask.app;

import Configs.DBConfig;
import Configs.ServiceConfig;
import Entities.*;
import Repositories.OrderRepository;
import Services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Import({DBConfig.class, ServiceConfig.class})
public class AppApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(AppApplication.class, args);
		OrderService service=context.getBean(OrderService.class);
		OrderRepository repository=context.getBean(OrderRepository.class);

		Order order=new Order();
		order.setId(1);
		order.setOrderNumber("ON1");
		order.setClient("Sinergia");
		Delivery delivery=new Delivery();
		delivery.setDeliveryCountry("Israel");
		SeaDelivery seaDelivery=new SeaDelivery();
		seaDelivery.setLine("line");

		Cargo cargo=new Cargo();
		cargo.setGrossWeight(BigDecimal.valueOf(20000));
		Declaration declaration=new Declaration();
		declaration.setDecalarationNumber("10101010/101010/1011001");
		cargo.setDeclaration(declaration);
		ShippingContainer container=new ShippingContainer();
		container.setNumber("CN100");
		container.setCargo(cargo);
		delivery.setSeaDelivery(seaDelivery);
		List<ShippingContainer> containers=new ArrayList<>();
		containers.add(container);
		delivery.setShippingContainers(containers);
		order.setDelivery(delivery);
		repository.save(order);
		System.out.println("saved");

		Order order1=new Order();
		order1.setClient("client2");
		order1.setCreationDate(Date.from(Instant.now()));
		repository.save(order1);



		try {
			System.out.println("Start");
			String orderSt=service.getOrderInJsonByOrderNumber("ON1");
			System.out.println("Got it "+orderSt);
		}
		catch(JsonProcessingException e){

		}
		try{
			System.out.println("Start deserialize");
			String input="{\"client\":\"Simple\",\"provider\":null,\"invoice\":null,\"recipient\":null,\"forwarder\":null,\"creationDate\":null,\"inspectionDate\":null,\"recievingDate\":null,\"orderNumber\":\"ON1\",\"price\":null,\"specialLoadingConditions\":null,\"complitionDate\":null,\"packing\":null,\"commercialDocsPreparationDate\":null,\"sendingDocsDate\":null,\"receptingDocsDate\":null,\"notes\":null,\"delivery\":null}";
		    boolean result=service.saveOrderFromJsonToDb(input);
			System.out.println("Process complete "+result);
		}
		catch (JsonProcessingException e){
			System.out.println(e.getMessage());

		}
	}

}
