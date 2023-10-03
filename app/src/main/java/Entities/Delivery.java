package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.List;


@Entity
@Table(name="deliveries")
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_id")
    int id;
    @Column(name="Место отправки")
    String placeOfDeparture;
    @Column(name="Страна отправки")
    String countryOfDeparture;
    @Column(name="Место доставки")
    String deliveryPlace;
    @Column(name="Страна доставки")
    String deliveryCountry;
    @Column(name="Склад")
    String destinationWarehouse;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "delivery")
    List<ShippingContainer> shippingContainers;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "delivery")
    SeaDelivery seaDelivery;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "delivery")
    LandDelivery landDelivery;
    @OneToOne
    Order order;



}
