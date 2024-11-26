CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255)        NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    role_id  BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT unique_email UNIQUE (email)
);

CREATE INDEX idx_role_id ON users (role_id);

