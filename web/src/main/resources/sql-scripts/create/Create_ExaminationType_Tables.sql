USE GroupManagement;

CREATE TABLE ExaminationType (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Type varchar(25) NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE ExaminationType (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Type varchar(25) NOT NULL,
	Deleted datetime NOT NULL UNIQUE
);
