# TramisTestTask
Предлагаю в качестве тестового задания реализовать модель БД и на любом языке в соответствии с принципами ООП объектную модель.
Классы должны уметь получать данные и сохранять их в БД.
Также должны уметь серилизоваться/десерилизоваться в JSON.

Скрипт создания таблиц
create table cargoes (cargo_id serial not null, shipping_container_container_id integer unique, "вес брутто" numeric(38,2), "кол-во мест" integer, объем numeric(38,2), товар varchar(255), primary key (cargo_id))
create table containers (container_id serial not null, delivery_delivery_id integer, "номер контейнера" varchar(255), "тип конт_" varchar(255), primary key (container_id))
create table declarations (cargo_cargo_id integer unique, declaration_id serial not null, "дата выпуска декларации" timestamp(6), "дата подачи декларации" timestamp(6), брокер varchar(255), "номер декларации" varchar(255), "таможенный пост" varchar(255), primary key (declaration_id))
create table deliveries (delivery_id serial not null, order_order_id integer unique, "место доставки" varchar(255), "место отправки" varchar(255), склад varchar(255), "страна доставки" varchar(255), "страна отправки" varchar(255), primary key (delivery_id))
create table landdeliveries (delivery_delivery_id integer unique, landdelivery_id serial not null, втт boolean, "дата выгрузки" timestamp(6), "дата отправки по ж/д" timestamp(6), "дата прибытия по ж/д" timestamp(6), primary key (landdelivery_id))
create table orders (order_id serial not null, стоимость numeric(38,2), packing timestamp(6), "дата готовн_" timestamp(6), "дата отправки док-тов" timestamp(6), "дата получ_ док-тов" timestamp(6), "дата получения" timestamp(6), "дата проверки" timestamp(6), "дата создания заказа" timestamp(6), "подготовка коммерческих док-ов" timestamp(6), клиент varchar(255), "номер заказа" varchar(255), "номер инвойса" varchar(255), "особые условия погрузки" varchar(255), получатель varchar(255), поставщик varchar(255), примечание varchar(255), экспедитор varchar(255), primary key (order_id))
create table saedeliveries (delivery_delivery_id integer unique, seadelivery_id serial not null, telex timestamp(6), "дата выхода в море" timestamp(6), "дата загрузки" timestamp(6), "дата прибытия" timestamp(6), "дата релиза hbl" timestamp(6), hbl varchar(255), агент varchar(255), коносамент varchar(255), линия varchar(255), порт varchar(255), "усл_ поставки" varchar(255), primary key (seadelivery_id))
alter table if exists cargoes add constraint cargoconstraint foreign key (shipping_container_container_id) references containers
alter table if exists containers add constraint containerconstraint foreign key (delivery_delivery_id) references deliveries
alter table if exists declarations add constraint declarationsconstraint foreign key (cargo_cargo_id) references cargoes
alter table if exists deliveries add constraint deliverieconstraint foreign key (order_order_id) references orders
alter table if exists landdeliveries add constraint landdeliveriesconstraint foreign key (delivery_delivery_id) references deliveries
alter table if exists saedeliveries add constraint seadeliveriesconstraint foreign key (delivery_delivery_id) references deliveries
