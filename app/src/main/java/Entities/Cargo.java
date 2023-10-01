package Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Table(name="cargoes")
@Data
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cargo_id",referencedColumnName = "declaration_id")
    Declaration declaration;
}
