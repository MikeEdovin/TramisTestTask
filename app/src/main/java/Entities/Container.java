package Entities;

import jakarta.persistence.*;

@Entity
@Table(name="containers")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="container_id")
    int id;
    @Column(name="Номер контейнера")
    String number;
    @Column(name="Тип конт.")
    String type;


    @OneToOne
    @JoinColumn(name="container_id",referencedColumnName = "cargo_id")
    Cargo cargo;
    //@ManyToOne
    //@JoinColumn(name="container_id",nullable = false)
    //Delivery delivery;
}
