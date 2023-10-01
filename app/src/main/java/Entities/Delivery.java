package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;


@Entity
@Table(name="deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany()
    @JoinColumn(name="container_id",referencedColumnName = "delivery_id")
    List<Container> containers;
    @OneToOne
    @JoinColumn(name="delivery_id",referencedColumnName = "seadelivery_id")
    SeaDelivery seaDelivery;
    @OneToOne
    @JoinColumn(name="delivery_id",referencedColumnName = "landdelivery_id")
    LandDelivery landDelivery;



}
