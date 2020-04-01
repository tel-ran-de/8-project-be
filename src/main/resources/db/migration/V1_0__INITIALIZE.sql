CREATE TABLE customer
(
    customer_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name        VARCHAR(45)       NOT NULL,
    PRIMARY KEY (customer_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE shipment
(
    shipment_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(90)       NOT NULL,
    customer_id SMALLINT UNSIGNED,
    PRIMARY KEY (shipment_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE tracking
(
    tracking_id SMALLINT UNSIGNED                                                   NOT NULL AUTO_INCREMENT,
    status      ENUM ('initiated','in progress','delivered','cancelled','returned') NOT NULL,
    shipment_id SMALLINT UNSIGNED,
    PRIMARY KEY (tracking_id),
    FOREIGN KEY (shipment_id) REFERENCES shipment (shipment_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO customer (customer_id, name)
VALUES (1, 'Evgeny Grazhdansky'),
       (2, 'Ivan Petrov'),
       (3, 'Evgeny Grazhdansky'),
       (4, 'Alla Petrova'),
       (5, 'Dmitri Sokolov'),
       (6, 'Oleg Smirnov'),
       (7, 'Dmitri Sokolov');

INSERT INTO shipment (shipment_id, description, customer_id)
VALUES (1, 'TP-Link AC1350 Powerline Adapter', 1),
       (2, 'Bosch MAZ2BC for Car MUC2', 7),
       (3, '345gPU Leather Wallet Case with Card Slots and Stand', 1),
       (4, 'Emsa 513357 Insulated Travel Mug  Blue', 3),
       (5, 'Jaques Chess Box Complete Hand Carved', 6),
       (6, 'BiC Kids Tropicolors Colouring Pencils (Pack of 24)', 6),
       (7, 'Panasonic KX-TS520GB', 7);

INSERT INTO tracking(tracking_id, status, shipment_id)
VALUES (1, 'initiated', 2),
       (2, 'cancelled', 4),
       (3, 'delivered', 1),
       (4, 'in progress', 3),
       (5, 'cancelled', 7),
       (6, 'delivered', 5),
       (7, 'in progress', 6);
