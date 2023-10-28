CREATE TABLE scripts
(
    id     UUID         NOT NULL,
    label  VARCHAR(255) NOT NULL,
    script TEXT         NOT NULL,
    CONSTRAINT pk_scripts PRIMARY KEY (id)
);

INSERT INTO users (id, login, password, role, name)
VALUES ('697d8a60-22fb-4665-b82b-86f782774d99', 'admin', '$2a$10$qhIl8FdG6kfMuII3COhfx.tsX0XgaHcLoVpuOCuLEYGPQ2fbrRhdi', 'ADMIN', 'Admin');