CREATE TABLE customer
(
    dni            varchar(20) NOT NULL,
    full_name       varchar(50) NOT NULL,
    credit_card    varchar(20) NOT NULL,
    total_flights  int NOT NULL,
    total_lodgings int NOT NULL,
    total_tours    int NOT NULL,
    phone_number  varchar(20) NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY ( dni )
);

CREATE TABLE roles (
                       id int not null generated always as identity primary key,
                       role_name VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       email varchar(45) not null,
                       pwd varchar(200) not null,
                       enabled boolean default true,
                       role_id int not null,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE authorities (
                             id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             authority_name VARCHAR(50) NOT NULL UNIQUE
);
create table user_roles (
                            user_id int not null primary key references users(id) on delete cascade,
                            role_id int not null references roles(id) on delete cascade


);
create table role_authorities(
                                 role_id INT NOT NULL,
                                 authority_id INT NOT NULL,

                                 PRIMARY KEY (role_id, authority_id),

                                 FOREIGN KEY (role_id) REFERENCES roles(id),
                                 FOREIGN KEY (authority_id) REFERENCES authorities(id)
)