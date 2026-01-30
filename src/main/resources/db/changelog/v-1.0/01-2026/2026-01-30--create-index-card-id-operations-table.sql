--liquibase formatted sql
--changeset Alexandr Makutsevich:2026-01-30--create-index-card-id-operations-table.sql

CREATE INDEX operations_card_if_fkey ON operations (card_id)