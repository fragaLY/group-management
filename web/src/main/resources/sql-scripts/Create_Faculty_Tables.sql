USE GroupManagement;

CREATE TABLE Faculty (
	Id bigint NOT NULL PRIMARY KEY,
	Name varchar NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE Faculty (
	Id bigint NOT NULL PRIMARY KEY,
	Name varchar NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);
