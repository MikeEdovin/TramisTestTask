package Services;

import Entities.*;
import Repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {



    String jsonOrder="{\"id\":0,\"client\":\"ТДМ прямой\",\"provider\":\"Jiaxperia compressor co ltd\",\"invoice\":\"19-257\",\"recipient\":\"llc tdm logistic\",\"forwarder\":\"Tramis Vladivostok\",\"creationDate\":1588798800000,\"inspectionDate\":null,\"recievingDate\":null,\"orderNumber\":\"19-257\",\"price\":null,\"specialLoadingConditions\":\"не отгружать на жд без отмашки клиента\",\"complitionDate\":null,\"packing\":null,\"commercialDocsPreparationDate\":null,\"sendingDocsDate\":null,\"receptingDocsDate\":null,\"notes\":\"ждут оплату, часть не готов, некоторые фабрики начнут работу 01.03\",\"delivery\":{\"id\":0,\"placeOfDeparture\":\"Zhongsan\",\"countryOfDeparture\":\"Китай\",\"deliveryPlace\":\"Москва поезд\",\"deliveryCountry\":\"Россия\",\"destinationWarehouse\":\"Баранцевское\",\"shippingContainers\":[{\"id\":0,\"number\":\"CXDU2274188\",\"type\":\"40'HC\",\"cargo\":{\"id\":0,\"product\":\"Электротехнические товары\",\"numberOfItems\":0,\"grossWeight\":13079.17,\"volume\":null,\"declaration\":{\"id\":0,\"decalarationNumber\":\"10702070/100720/0152359\",\"dateOfFilling\":1594328400000,\"dateOfIssue\":1594674000000,\"broker\":\"Контракт клиента\",\"customsPost\":null,\"cargo\":null},\"shippingContainer\":null},\"delivery\":null}],\"seaDelivery\":{\"id\":0,\"destinationPort\":\"ВМПП\",\"deliveryConditions\":\"FOB\",\"line\":\"Sinokor\",\"agent\":\"Ken Wang\",\"loadingDate\":1591995600000,\"sailingDate\":1592859600000,\"telex\":1593637200000,\"billOfLadingNumber\":\"SNKO03B200600287\",\"hblReleaseDate\":null,\"arrivalDate\":1594155600000,\"delivery\":null,\"hbl\":\"НЕТ\"},\"landDelivery\":{\"id\":0,\"departureRailwayDate\":1595278800000,\"arrivalRailwayDate\":null,\"uploadingDate\":null,\"internalCustomsTransit\":false,\"delivery\":null},\"order\":null}}\n";

    @Mock
    OrderRepository repository;
    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    OrderServiceImpl service;


    Order setUp(){
        Declaration declaration=new Declaration();
        declaration.setDeclarationNumber("10702070/100720/0152359");
        declaration.setDateOfFilling(Date.valueOf("2020-07-10"));
        declaration.setDateOfIssue(Date.valueOf("2020-07-14"));
        declaration.setBroker("Контракт клиента");
        Cargo cargo=new Cargo();
        cargo.setProduct("Электротехнические товары");
        cargo.setGrossWeight(BigDecimal.valueOf(13079.17));
        ShippingContainer container=new ShippingContainer();
        container.setNumber("CXDU2274188");
        container.setType("40'HC");
        SeaDelivery sea=new SeaDelivery();
        sea.setDestinationPort("ВМПП");
        sea.setDeliveryConditions("FOB");
        sea.setLine("Sinokor");
        sea.setAgent("Ken Wang");
        sea.setBillOfLadingNumber("SNKO03B200600287");
        sea.setLoadingDate(Date.valueOf("2020-06-13"));
        sea.setSailingDate(Date.valueOf("2020-06-23"));
        sea.setTelex(Date.valueOf("2020-07-02"));
        sea.setHbl("НЕТ");
        sea.setArrivalDate(Date.valueOf("2020-07-08"));
        LandDelivery land=new LandDelivery();
        land.setDepartureRailwayDate(Date.valueOf("2020-07-21"));
        land.setInternalCustomsTransit(false);
        Delivery delivery=new Delivery();
        delivery.setPlaceOfDeparture("Zhongsan");
        delivery.setCountryOfDeparture("Китай");
        delivery.setDeliveryPlace("Москва поезд");
        delivery.setDeliveryCountry("Россия");
        delivery.setDestinationWarehouse("Баранцевское");
        Order order=new Order();
        order.setOrderNumber("19-257");
        order.setCreationDate(Date.valueOf("2019-10-10"));
        order.setClient("ТДМ прямой");
        order.setProvider("Jiaxperia compressor co ltd");
        order.setInvoice("19-257");
        order.setRecipient("llc tdm logistic");
        order.setForwarder("Tramis Vladivostok");
        order.setSpecialLoadingConditions("не отгружать на жд без отмашки клиента");
        order.setCreationDate(Date.valueOf("2020-05-07"));
        order.setNotes("ждут оплату, часть не готов, некоторые фабрики начнут работу 01.03");
        cargo.setDeclaration(declaration);
        container.setCargo(cargo);
        List<ShippingContainer> containers=new ArrayList<>();
        containers.add(container);
        delivery.setShippingContainers(containers);
        delivery.setSeaDelivery(sea);
        delivery.setLandDelivery(land);
        order.setDelivery(delivery);
        return order;
    }

    @Test
    void getOrderInJsonByOrderNumberShouldReturnExpectedValue() throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        Order order=setUp();
        Mockito.when(repository.findByOrderNumber("19-257")).thenReturn(order);
        Mockito.when(objectMapper.writeValueAsString(order)).thenReturn(jsonOrder);
        Assertions.assertNotNull(service.getOrderInJsonByOrderNumber("19-257"));
        Assertions.assertEquals(mapper.writeValueAsString(order).trim(),service.getOrderInJsonByOrderNumber("19-257").trim());
    }

    @Test
    void saveOrderFromJsonToDbShouldReturnExpectedValue() throws JsonProcessingException {
        Order order=setUp();
        Mockito.when(repository.save(objectMapper.readValue(jsonOrder,Order.class))).thenReturn(order);
        Order savedOrder=service.saveOrderFromJsonToDb(jsonOrder);
        Assertions.assertNotNull(savedOrder);
        Assertions.assertNotNull(savedOrder.getDelivery());
        Assertions.assertNotNull(savedOrder.getDelivery().getSeaDelivery());
        Assertions.assertNotNull(savedOrder.getDelivery().getLandDelivery());
        Assertions.assertNotNull(savedOrder.getDelivery().getShippingContainers());
        Assertions.assertNotNull(savedOrder.getDelivery().getShippingContainers().get(0));
        Assertions.assertNotNull(savedOrder.getDelivery().getShippingContainers().get(0).getCargo());
        Assertions.assertNotNull(savedOrder.getDelivery().getShippingContainers().get(0).getCargo().getDeclaration());
        Assertions.assertEquals("19-257",savedOrder.getOrderNumber());
        Assertions.assertEquals("SNKO03B200600287",savedOrder.getDelivery().getSeaDelivery().getBillOfLadingNumber());
        Assertions.assertEquals(false,savedOrder.getDelivery().getLandDelivery().isInternalCustomsTransit());
        Assertions.assertEquals("CXDU2274188",savedOrder.getDelivery().getShippingContainers().get(0).getNumber());
        Assertions.assertEquals("Контракт клиента",savedOrder.getDelivery().getShippingContainers().get(0).getCargo().getDeclaration().getBroker());
    }

}