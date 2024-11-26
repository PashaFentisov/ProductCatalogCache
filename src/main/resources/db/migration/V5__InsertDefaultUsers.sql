INSERT INTO roles (name)
VALUES ('ADMIN');
INSERT INTO roles (name)
VALUES ('USER');

INSERT INTO users (username, password, email, role_id)
VALUES ('admin', '$2a$12$DORiGFV4SEplMGYWTfxBNea7qFZ8xvhBCvca6aPJ8Xu2a6haJ1zvq', 'admin@example.com', 1);

INSERT INTO users (username, password, email, role_id)
VALUES ('user', '$2a$12$5w8XguRh3CZBnMf31KGgoOX6lNb8Jt2tB1VgGebjjHsLzRAfpvB7O', 'user@example.com', 2);
