package Entities;

import jakarta.persistence.*;

@Entity
@Table(name="containers")
public class Container {
    @Id
    int id;
    @Column(name="Номер контейнера")
    String number;
    @Column(name="Тип конт.")
    String type;
    @OneToOne
    @JoinColumn(name="cargo_id",referencedColumnName = "id")
    Cargo cargo;
}
