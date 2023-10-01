package Entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="containers")
@Data
public class ShippingContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="container_id")
    int id;
    @Column(name="Номер контейнера")
    String number;
    @Column(name="Тип конт.")
    String type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="container_id",referencedColumnName = "cargo_id")
    Cargo cargo;


}
