CREATE TABLE author
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    created_date DATETIME,
    updated_date DATETIME
);

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    contents VARCHAR(1000),
    created_date DATETIME,
    updated_date DATETIME,
    author_id BIGINT,
    foreign key (author_id) references author(id)
);

