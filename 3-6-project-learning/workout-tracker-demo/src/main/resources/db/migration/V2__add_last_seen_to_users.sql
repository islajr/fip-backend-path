-- V2_add_last_seen_to_users.sql

ALTER TABLE users
ADD COLUMN last_seen TIMESTAMPTZ;
