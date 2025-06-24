-- =================================================================
-- MOCK SECURITY DATA
-- Contains a set of users with different roles for testing purposes.
-- Passwords are pre-hashed with BCrypt.
--
-- Raw Passwords for testing:
--   - admin123
--   - password123
-- =================================================================
-- 1. The System Administrator
-- Purpose: Testing admin-only endpoints and system-wide views.
INSERT INTO users (first_name, last_name, email, password, role)
VALUES (
        'Alice',
        'Admin',
        'admin@bankcore.com',
        '$2a$10$s7v2A8p5z3.i9Q2j9K.1a.XoD1C/V1xJ8pY.Fz6i.iN8Uv7e.a9C',
        -- 'admin123'
        'ADMIN'
    );
-- 2. A Standard User with an active profile
-- Purpose: Testing core user flows like transactions, account viewing, etc.
INSERT INTO users (first_name, last_name, email, password, role)
VALUES (
        'Bob',
        'Banker',
        'bob@example.com',
        '$2a$10$y5w.K.3wA9m2Yv/g.rR1z.T4E9Z/fJ4o.k9s8Uv7e.o9N/l4b.dC',
        -- 'password123'
        'USER'
    );
-- 3. Another Standard User
-- Purpose: Testing transfers between two distinct users.
INSERT INTO users (first_name, last_name, email, password, role)
VALUES (
        'Charlie',
        'Customer',
        'charlie@example.com',
        '$2a$10$y5w.K.3wA9m2Yv/g.rR1z.T4E9Z/fJ4o.k9s8Uv7e.o9N/l4b.dC',
        -- 'password123'
        'USER'
    );
-- 4. A User who has just registered but has no bank accounts yet
-- Purpose: Testing the "empty state" for a new user and the account creation flow.
INSERT INTO users (first_name, last_name, email, password, role)
VALUES (
        'David',
        'Default',
        'david@example.com',
        '$2a$10$y5w.K.3wA9m2Yv/g.rR1z.T4E9Z/fJ4o.k9s8Uv7e.o9N/l4b.dC',
        -- 'password123'
        'USER'
    );