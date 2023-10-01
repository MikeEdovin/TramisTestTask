package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="saedeliveries")
@Data
public class SeaDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seadelivery_id")
    int id;
    @Column(name="Порт")
    String destinationPort;
    @Column(name="Усл. поставки")
    String deliveryConditions;
    @Column(name="Линия")
    String line;
    @Column(name="Агент")
    String agent;
    @Column(name="Дата загрузки")
    Date loadingDate;
    @Column(name="Дата выхода в море")
    Date sailingDate;
    @Column(name="Telex")
    Date telex;
    @Column(name="Коносамент")
    String billOfLadingNumber;
    @Column(name="HBL")
    String Hbl;
    @Column(name="Дата релиза HBL")
    Date hblReleaseDate;
    @Column(name="Дата прибытия")
    Date arrivalDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="container_id")
    Delivery delivery;
}
