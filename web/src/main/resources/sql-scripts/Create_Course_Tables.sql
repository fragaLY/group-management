USE GroupManagement;

CREATE TABLE Course (
	Id bigint NOT NULL PRIMARY KEY,
	Course tinyint NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE Course (
	Id bigint NOT NULL PRIMARY KEY,
	Course tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);

