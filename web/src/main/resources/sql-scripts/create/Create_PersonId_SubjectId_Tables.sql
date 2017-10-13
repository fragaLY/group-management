USE GroupManagement;

CREATE TABLE PersonId_SubjectId (
	Person_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
);

ALTER TABLE PersonId_SubjectId ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);
ALTER TABLE PersonId_SubjectId ADD FOREIGN KEY (Subject_Id) REFERENCES Subject(Id);

USE GroupManagement_Deleted;

CREATE TABLE PersonId_SubjectId (
	Person_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
);

ALTER TABLE PersonId_SubjectId ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);
ALTER TABLE PersonId_SubjectId ADD FOREIGN KEY (Subject_Id) REFERENCES Subject(Id);