USE GroupManagement;

CREATE TABLE Person (
	Id bigint NOT NULL PRIMARY KEY,
	Contacts_Id bigint UNIQUE,
	PersonalData_Id bigint UNIQUE,
	PersonRole_Id bigint NOT NULL
);

ALTER TABLE Person ADD FOREIGN KEY (Contacts_Id) REFERENCES Contacts(Id);
ALTER TABLE Person ADD FOREIGN KEY (PersonalData_Id) REFERENCES PersonalData(Id);
ALTER TABLE Person ADD FOREIGN KEY (PersonRole_Id) REFERENCES PersonRole(Id);

USE GroupManagement_Deleted;


CREATE TABLE Person (
	Id bigint NOT NULL PRIMARY KEY,
	Contacts_Id bigint UNIQUE,
	PersonalData_Id bigint UNIQUE,
	PersonRole_Id bigint NOT NULL,
	Deleted datetime NOT NULL
);

ALTER TABLE Person ADD FOREIGN KEY (Contacts_Id) REFERENCES Contacts(Id);
ALTER TABLE Person ADD FOREIGN KEY (PersonalData_Id) REFERENCES PersonalData(Id);
ALTER TABLE Person ADD FOREIGN KEY (PersonRole_Id) REFERENCES PersonRole(Id);
