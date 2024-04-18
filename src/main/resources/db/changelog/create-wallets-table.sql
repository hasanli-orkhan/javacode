-- liquibase formatted sql

-- changeset Vadim:create-wallets-table
CREATE TABLE wallets
(
    id      SERIAL NOT NULL,
    UUID    VARCHAR(128) NOT NULL,
    balance DECIMAL(19, 2)   NOT NULL
);