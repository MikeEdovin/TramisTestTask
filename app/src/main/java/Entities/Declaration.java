package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="declarations")
public class Declaration {
    @Id
    int id;
    @Column(name="Номер декларации")
    String decalarationNumber;
    @Column(name="Дата подачи декларации")
    Date dateOfFilling;
    @Column(name="Дата выпуска декларации")
    Date dateOfIssue;
    @Column(name="Брокер")
    String broker;
    @Column(name="Таможенный пост")
    String customsPost;

}
