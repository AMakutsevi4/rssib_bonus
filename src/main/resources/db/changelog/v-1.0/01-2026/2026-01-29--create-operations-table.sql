--liquibase formatted sql
--changeset Alexandr Makutsevich:2026-01-29--create-operations-table.sql

CREATE TABLE operations (
 id BIGSERIAL PRIMARY KEY NOT NULL,
    card_id BIGINT NOT NULL REFERENCES cards (id) ON delete CASCADE,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    refund BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);