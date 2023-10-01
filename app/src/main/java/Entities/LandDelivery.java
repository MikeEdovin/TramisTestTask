package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name="landdeliveries")
@Data
public class LandDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="container_id")
    Delivery delivery;
}
