package Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    int id;
    @Column(name="Клиент")
    String client;
    @Column(name="Поставщик")
    String provider;
    @Column(name="Номер инвойса")
    String invoice;
    @Column(name="Получатель")
    String recipient;
    @Column(name="Экспедитор")
    String forwarder;
    @Column(name="Дата создания заказа")
    Date creationDate;
    @Column(name="Дата проверки")
    Date inspectionDate;
    @Column(name="Дата получения")
    Date recievingDate;
    @Column(name="Номер заказа")
    String orderNumber;
    @Column(name="Стоимость")
    BigDecimal price;
    @Column(name="Особые условия погрузки")
    String specialLoadingConditions;
    @Column(name="Дата готовн.")
    Date complitionDate;
    @Column(name="Packing")
    Date packing;
    @Column(name="Подготовка коммерческих док-ов")
    Date commercialDocsPreparationDate;
    @Column(name="Дата отправки док-тов")
    Date sendingDocsDate;
    @Column(name="Дата получ. док-тов")
    Date receptingDocsDate;
    @Column(name="Примечание")
    String notes;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    Delivery delivery;
}
