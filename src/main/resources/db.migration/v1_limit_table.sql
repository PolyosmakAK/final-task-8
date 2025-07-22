CREATE TABLE users_limit
(
    id               BIGINT PRIMARY KEY NOT NULL,
    day_limit        NUMERIC            NOT NULL,
    current_limit    NUMERIC              NOT NULL,
    last_reservation NUMERIC
);

INSERT INTO users_limit (id, day_limit, current_limit)
VALUES (1, 10000.00, 10000.00),
       (2, 10000.00, 10000.00),
       (3, 10000.00, 10000.00);