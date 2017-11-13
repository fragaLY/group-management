USE GroupManagement;

CREATE TABLE Semester (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Semester tinyint NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE Semester (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Semester tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);
