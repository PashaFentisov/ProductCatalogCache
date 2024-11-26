CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT unique_name UNIQUE (name)
);

