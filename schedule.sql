CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    contents VARCHAR(1000),
    created_date DATETIME,
    updated_date DATETIME
);