ALTER TABLE users
    ADD investor_profile VARCHAR(255);

ALTER TABLE users
    ADD item_id UUID;

ALTER TABLE users
    ADD name VARCHAR(255);

ALTER TABLE users
    ALTER COLUMN name SET NOT NULL;