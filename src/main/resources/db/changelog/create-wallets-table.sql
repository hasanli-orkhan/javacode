-- liquibase formatted sql

-- changeset Vadim:create-wallets-table
CREATE TABLE wallets
(
    id      UUID PRIMARY KEY NOT NULL,
    balance DECIMAL(19, 2)   NOT NULL
);