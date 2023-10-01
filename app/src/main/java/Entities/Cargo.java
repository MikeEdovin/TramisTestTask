package Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name="cargoes")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cargo_id")
    int id;
    @Column(name="Товар")
    String product;
    @Column(name="Кол-во мест")
    int numberOfItems;
    @Column(name="Вес брутто")
    BigDecimal grossWeight;
    @Column(name="Объем")
    BigDecimal volume;
   // @OneToOne(mappedBy = "cargo")
   // Container container;
   // @OneToOne(mappedBy = "cargo")
   // Declaration declaration;
}
