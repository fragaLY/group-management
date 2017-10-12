USE GroupManagement;

CREATE TABLE Semester (
	Id bigint NOT NULL PRIMARY KEY,
	Semester tinyint NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE Semester (
	Id bigint NOT NULL PRIMARY KEY,
	Semester tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);
