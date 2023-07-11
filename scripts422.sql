CREATE TABLE human
(
    id            SERIAL UNIQUE,
    name          text PRIMARY KEY,
    age           INTEGER CHECK (age >= 18),
    driverLicense BOOLEAN,
    car_id        SERIAL REFERENCES car (id)
);
CREATE TABLE car
(
    id    SERIAL UNIQUE,
    brand text,
    model text,
    cost  INTEGER
);