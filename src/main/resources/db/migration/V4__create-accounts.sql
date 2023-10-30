CREATE TABLE accounts
(
    id       UUID             NOT NULL,
    income   DOUBLE PRECISION NOT NULL,
    expenses DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

ALTER TABLE users
    ADD account_id UUID;

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (id);