package Repositories;

import Configs.DBConfig;
import Entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@DataJpaTest()
@Testcontainers
@ContextConfiguration(classes = DBConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("testDb")
            .withPassword("password")
            .withUsername("user");
    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
    }

    void setUp(){
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
        repository.save(order);
    }

    @Autowired
    OrderRepository repository;
    @Test
    void findByOrderNumberShouldReturnExpectedValue() {
        setUp();
        Order order=repository.findByOrderNumber("19-257");
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getDelivery());
        Assertions.assertNotNull(order.getDelivery().getSeaDelivery());
        Assertions.assertNotNull(order.getDelivery().getLandDelivery());
        Assertions.assertNotNull(order.getDelivery().getShippingContainers());
        Assertions.assertNotNull(order.getDelivery().getShippingContainers().get(0));
        Assertions.assertNotNull(order.getDelivery().getShippingContainers().get(0).getCargo());
        Assertions.assertNotNull(order.getDelivery().getShippingContainers().get(0).getCargo().getDeclaration());
        Assertions.assertEquals("19-257",order.getOrderNumber());
        Assertions.assertEquals("SNKO03B200600287",order.getDelivery().getSeaDelivery().getBillOfLadingNumber());
        Assertions.assertEquals(false,order.getDelivery().getLandDelivery().isInternalCustomsTransit());
        Assertions.assertEquals("CXDU2274188",order.getDelivery().getShippingContainers().get(0).getNumber());
        Assertions.assertEquals("Контракт клиента",order.getDelivery().getShippingContainers().get(0).getCargo().getDeclaration().getBroker());
        repository.deleteAll();
    }
}