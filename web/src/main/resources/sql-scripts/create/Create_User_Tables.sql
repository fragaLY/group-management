USE GroupManagement;

CREATE TABLE User (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Person_Id bigint NOT NULL UNIQUE,
	Login varchar(35) NOT NULL UNIQUE,
	Password varchar(50) NOT NULL
);

ALTER TABLE User ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);

USE GroupManagement_Deleted;

CREATE TABLE User (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Person_Id bigint NOT NULL UNIQUE,
	Login varchar(35) NOT NULL UNIQUE,
	Password varchar(50) NOT NULL,
	Deleted datetime NOT NULL
);

ALTER TABLE User ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);