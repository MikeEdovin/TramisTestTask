package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="declarations")
@Data
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="declaration_id")
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
