CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    iban VARCHAR(34) NOT NULL UNIQUE,
    balance DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    from_account_id BIGINT,
    to_account_id BIGINT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_from_account FOREIGN KEY(from_account_id) REFERENCES accounts(id),
    CONSTRAINT fk_to_account FOREIGN KEY(to_account_id) REFERENCES accounts(id)
);
CREATE TABLE audit_logs (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    details TEXT,
    user_id BIGINT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audit_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE
    SET NULL
);