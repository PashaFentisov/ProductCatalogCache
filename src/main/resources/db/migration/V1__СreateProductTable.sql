CREATE TABLE products
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(255)   NOT NULL,
    description       TEXT,
    price             NUMERIC(15, 2) NOT NULL,
    category          VARCHAR(255),
    stock             INTEGER,
    created_date      TIMESTAMP      NOT NULL,
    last_updated_date TIMESTAMP      NOT NULL,
    CONSTRAINT unique_name_category UNIQUE (name, category)
);


CREATE INDEX idx_category ON products (category);
