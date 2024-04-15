DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    UUID       VARCHAR(128)           NOT NULL,
	balance	   VARCHAR(128)           NOT NULL
);