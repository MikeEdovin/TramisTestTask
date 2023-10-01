package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Cascade;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="container_id",referencedColumnName = "delivery_id")
    List<ShippingContainer> shippingContainers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id",referencedColumnName = "seadelivery_id")
    SeaDelivery seaDelivery;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id",referencedColumnName = "landdelivery_id")
    LandDelivery landDelivery;



}
