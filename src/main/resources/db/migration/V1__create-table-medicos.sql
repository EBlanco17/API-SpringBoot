CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(10) NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    distrito VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    PRIMARY KEY (id)
);