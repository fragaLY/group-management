USE GroupManagement;

CREATE TABLE Contacts (
	Id bigint NOT NULL PRIMARY KEY,
	Address_Id bigint,
	Phone varchar(13) NOT NULL UNIQUE,
	Skype varchar(35) UNIQUE,
	Email varchar NOT NULL UNIQUE
);
ALTER TABLE Contacts ADD FOREIGN KEY (Address_Id) REFERENCES Address(Id);


USE GroupManagement_Deleted;

CREATE TABLE Contacts (
	Id bigint NOT NULL PRIMARY KEY,
	Address_Id bigint,
	Phone varchar(13) NOT NULL UNIQUE,
	Skype varchar(35) UNIQUE,
	Email varchar NOT NULL UNIQUE,
	Deleted datetime NOT NULL
);

ALTER TABLE Contacts ADD FOREIGN KEY (Address_Id) REFERENCES Address(Id);

