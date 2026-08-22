CREATE TABLE hotel
(
    "id"      bigserial NOT NULL,
    name    varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    rating int NOT NULL,
    price    double precision NOT NULL,
    CONSTRAINT pk_hotel PRIMARY KEY ( "id" )
);

CREATE TABLE tour
(
    "id"             bigserial NOT NULL,
    id_customer       varchar(20) NOT NULL,
    CONSTRAINT pk_tour PRIMARY KEY ( "id" ),
);

CREATE TABLE reservation
(
    "id"             uuid NOT NULL,
    date_reservation timestamp NOT NULL,
    date_start       date NOT NULL,
    date_end         date NULL,
    total_days       int NOT NULL,
    price            double precision not null,
    tour_id          bigint NULL,
    hotel_id         bigint NULL,
    customer_id      varchar(20) NOT NULL,
    CONSTRAINT pk_reservation PRIMARY KEY ( "id" ),
    CONSTRAINT fk_hotel_r FOREIGN KEY ( hotel_id ) REFERENCES hotel ( "id" ) ON DELETE NO ACTION ,
    CONSTRAINT fk_tour_r FOREIGN KEY ( tour_id ) REFERENCES tour ( "id" ) ON DELETE CASCADE
);
