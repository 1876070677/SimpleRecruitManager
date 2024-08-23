CREATE TABLE recruit (
    id int PRIMARY KEY AUTO_INCREMENT,
    company VARCHAR(32) NOT NULL,
    job VARCHAR(32) NOT NULL,
    money int UNSIGNED NOT NULL,
    region VARCHAR(32) NOT NULL,
    phone varchar(64) NOT NULL
) ENGINE=INNODB default character set utf8 collate utf8_general_ci;