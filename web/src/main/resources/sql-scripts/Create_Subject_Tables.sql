USE GroupManagement;

CREATE TABLE Subject (
	Id bigint NOT NULL PRIMARY KEY,
	Examination_Id bigint NOT NULL UNIQUE,
	Name varchar(100) NOT NULL UNIQUE,
);

ALTER TABLE Subject ADD FOREIGN KEY (Examination_Id) REFERENCES Examination(Id);

USE GroupManagement_Deleted;

CREATE TABLE Subject (
	Id bigint NOT NULL PRIMARY KEY,
	Examination_Id bigint NOT NULL UNIQUE,
	Name varchar(100) NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);

ALTER TABLE Subject ADD FOREIGN KEY (Examination_Id) REFERENCES Examination(Id);
