USE GroupManagement;

CREATE TABLE Grade (
	Id bigint NOT NULL PRIMARY KEY,
	Grade tinyint NOT NULL UNIQUE
);

USE GroupManagement_Deleted;

CREATE TABLE Grade (
	Id bigint NOT NULL PRIMARY KEY,
	Grade tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);
