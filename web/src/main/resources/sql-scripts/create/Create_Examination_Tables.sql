USE GroupManagement;

CREATE TABLE Examination (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Grade_Id bigint DEFAULT NULL,
	ExaminationType_Id bigint NOT NULL
);

ALTER TABLE Examination ADD FOREIGN KEY (Grade_Id) REFERENCES Grade(Id);
ALTER TABLE Examination ADD FOREIGN KEY (ExaminationType_Id) REFERENCES ExaminationType(Id);

USE GroupManagement_Deleted;

CREATE TABLE Examination (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Grade_Id bigint DEFAULT NULL,
	ExaminationType_Id bigint NOT NULL,
	Deleted datetime NOT NULL
);

ALTER TABLE Examination ADD FOREIGN KEY (Grade_Id) REFERENCES Grade(Id);
ALTER TABLE Examination ADD FOREIGN KEY (ExaminationType_Id) REFERENCES ExaminationType(Id);