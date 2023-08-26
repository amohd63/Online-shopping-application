DROP TABLE Product;

-- Product
CREATE TABLE Product (
    id VARCHAR(20) PRIMARY KEY UNIQUE default '100',
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL default '',
    price BIGINT NOT NULL,
    creation_time LONG NOT NULL,
    last_update_time LONG,
    category VARCHAR(20),
    version VARCHAR(20)
);

SET FOREIGN_KEY_CHECKS=0; -- to disable them
