package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;


@Entity
@Table(name="deliveries")
public class Delivery {
    @Id
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
    @OneToMany
            
    List<Container> containers;
    SeaDelivery seaDelivery;
    LandDelivery landDelivery;

}
