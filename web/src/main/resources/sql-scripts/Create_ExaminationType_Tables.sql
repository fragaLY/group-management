USE GroupManagement;

CREATE TABLE ExaminationType (
	Id bigint NOT NULL PRIMARY KEY,
	Type varchar(25) NOT NULL
);

USE GroupManagement_Deleted;

CREATE TABLE ExaminationType (
	Id bigint NOT NULL PRIMARY KEY,
	Type varchar(25) NOT NULL,
	Deleted datetime NOT NULL
);
