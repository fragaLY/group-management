USE GroupManagement;

CREATE TABLE PersonalData (
	Id bigint NOT NULL PRIMARY KEY,
	FirstName varchar(100) NOT NULL,
	LastName varchar(100) NOT NULL
);

USE GroupManagement_Deleted;

CREATE TABLE PersonalData (
	Id bigint NOT NULL PRIMARY KEY,
	FirstName varchar(100) NOT NULL,
	LastName varchar(100) NOT NULL,
	Deleted datetime NOT NULL
);
