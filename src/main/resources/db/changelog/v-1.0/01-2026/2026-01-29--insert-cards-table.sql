--liquibase formatted sql
--changeset Alexandr Makutsevich:2026-01-29--insert-cards-table.sql

INSERT INTO cards (number, balance, created_at, updated_at)
VALUES (1, 0.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cards (number, balance, created_at, updated_at)
VALUES (2, 0.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cards (number, balance, created_at, updated_at)
VALUES (3, 0.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);