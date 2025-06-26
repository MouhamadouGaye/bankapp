-- CREATE TABLE users0 (
--     id BIGSERIAL PRIMARY KEY,
--     first_name VARCHAR(255) NOT NULL,
--     last_name VARCHAR(255) NOT NULL,
--     email VARCHAR(255) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     role VARCHAR(50) NOT NULL
-- );
-- CREATE TABLE accounts0 (
--     id BIGSERIAL PRIMARY KEY,
--     iban VARCHAR(34) NOT NULL UNIQUE,
--     balance DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
--     user_id BIGINT NOT NULL,
--     CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
-- );
-- CREATE TABLE transactions0 (
--     id BIGSERIAL PRIMARY KEY,
--     amount DECIMAL(19, 2) NOT NULL,
--     type VARCHAR(50) NOT NULL,
--     from_account_id BIGINT,
--     to_account_id BIGINT,
--     created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     CONSTRAINT fk_from_account FOREIGN KEY(from_account_id) REFERENCES accounts(id),
--     CONSTRAINT fk_to_account FOREIGN KEY(to_account_id) REFERENCES accounts(id)
-- );
-- CREATE TABLE audit_logs0 (
--     id BIGSERIAL PRIMARY KEY,
--     action VARCHAR(255) NOT NULL,
--     details TEXT,
--     user_id BIGINT,
--     created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     CONSTRAINT fk_audit_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE
--     SET NULL
-- );
CREATE TABLE users0 (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
CREATE TABLE accounts0 (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    iban VARCHAR(34) NOT NULL UNIQUE,
    balance DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS transactions0;
CREATE TABLE transactions0 (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    from_account_id UUID,
    to_account_id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_from_account FOREIGN KEY(from_account_id) REFERENCES accounts0(id),
    CONSTRAINT fk_to_account FOREIGN KEY(to_account_id) REFERENCES accounts0(id)
);
DROP TABLE IF EXISTS audit_logs0;
CREATE TABLE audit_logs0 (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    details TEXT,
    user_id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audit_user FOREIGN KEY(user_id) REFERENCES users0(id) ON DELETE
    SET NULL
);
-- Enable the UUID extension if not done yet
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Create users table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
-- Create accounts table
CREATE TABLE accounts (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    iban VARCHAR(34) NOT NULL UNIQUE,
    balance DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
-- Create transactions table
CREATE TABLE transactions (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    from_account_id UUID,
    to_account_id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_from_account FOREIGN KEY(from_account_id) REFERENCES accounts(id),
    CONSTRAINT fk_to_account FOREIGN KEY(to_account_id) REFERENCES accounts(id)
);
-- Create audit_logs table
CREATE TABLE audit_logs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    action VARCHAR(255) NOT NULL,
    details TEXT,
    user_id UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audit_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE
    SET NULL
);