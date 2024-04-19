-- liquibase formatted sql

-- changeset Vadim:create-wallets-table
CREATE TABLE wallets
(
    id      int GENERATED ALWAYS AS IDENTITY NOT NULL,
    balance DECIMAL(19, 2)   NOT NULL
);