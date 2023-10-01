package Entities;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class LandDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="landdelivery_id")
    int id;
    @Column(name="Дата отправки по ж/д")
    Date departureRailwayDate;
    @Column(name="Дата прибытия по ж/д")
    Date arrivalRailwayDate;
    @Column(name="Дата выгрузки")
    Date uploadingDate;
    @Column(name="ВТТ")
    boolean internalCustomsTransit;
    @ManyToOne
    @JoinColumn(name="container_id")
    Delivery delivery;
}
