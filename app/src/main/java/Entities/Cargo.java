package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name="cargoes")
public class Cargo {
    @Id
    int id;
    @Column(name="Товар")
    String product;
    @Column(name="Кол-во мест")
    int numberOfItems;
    @Column(name="Вес брутто")
    BigDecimal grossWeight;
    @Column(name="Объем")
    BigDecimal volume;
    @OneToOne(mappedBy = "cargoes")
    Container container;
}
