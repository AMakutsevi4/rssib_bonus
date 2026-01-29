--liquibase formatted sql
--changeset Alexandr Makutsevich:2026-01-29--create-cards-table.sql

CREATE TABLE cards (
  id BIGSERIAL PRIMARY KEY NOT NULL,
    number VARCHAR(255) UNIQUE NOT NULL,
    balance DECIMAL(19, 2) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);