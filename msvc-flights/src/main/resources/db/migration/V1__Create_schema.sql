
CREATE TABLE fly
(
    "id"         bigserial NOT NULL,
    origin_lat   decimal NOT NULL,
    origin_lng   decimal NOT NULL,
    destiny_lng  decimal NOT NULL,
    destiny_lat  decimal NOT NULL,
    origin_name  varchar(20) NOT NULL,
    destiny_name varchar(20) NOT NULL,
    aero_line varchar(20) NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT pk_fly PRIMARY KEY ( "id" )
);

CREATE TABLE ticket
(
    "id"           uuid NOT NULL,
    price          double precision NOT NULL,
    fly_id         bigint NOT NULL,
    departure_date timestamp NOT NULL,
    arrival_date   timestamp NOT NULL,
    purchase_date  timestamp NOT NULL,
    customer_id    varchar(20) NOT NULL,
    tour_id        bigint,
    CONSTRAINT pk_ticket PRIMARY KEY ( "id" ),
    CONSTRAINT fk_fly_t FOREIGN KEY ( fly_id ) REFERENCES fly ( "id" ) ON DELETE NO ACTION
);
