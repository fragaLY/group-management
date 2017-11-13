USE GroupManagement;

CREATE TABLE PersonRole (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Role varchar NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE PersonRole (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Role varchar NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);
