USE GroupManagement;
CREATE TABLE Address (
	Id bigint NOT NULL PRIMARY KEY,
	Country varchar(50) NOT NULL,
	City varchar(50) NOT NULL,
	Street varchar(50) NOT NULL,
	Home varchar(10) NOT NULL,
	ApartmentNumber varchar(10) NOT NULL
);

USE GroupManagement_Deleted;
CREATE TABLE Address (
	Id bigint NOT NULL PRIMARY KEY,
	Country varchar(50) NOT NULL,
	City varchar(50) NOT NULL,
	Street varchar(50) NOT NULL,
	Home varchar(10) NOT NULL,
	ApartmentNumber varchar(10) NOT NULL,
	Deleted datetime NOT NULL
);