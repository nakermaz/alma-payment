CREATE TABLE payments (
    id          BIGSERIAL PRIMARY KEY,
    amount      DOUBLE PRECISION,
    currency    VARCHAR(20),
    description VARCHAR(1000),
    client_id   BIGINT,
    status      VARCHAR(30)
);
