USE GroupManagement;

CREATE TABLE PersonId_GroupId (
	Person_Id bigint NOT NULL,
	Group_Id bigint NOT NULL
);

ALTER TABLE PersonId_GroupId ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);
ALTER TABLE PersonId_GroupId ADD FOREIGN KEY (Group_Id) REFERENCES StudentGroup(Id);

USE GroupManagement_Deleted;

CREATE TABLE PersonId_GroupId (
	Person_Id bigint NOT NULL,
	Group_Id bigint NOT NULL
);

ALTER TABLE PersonId_GroupId ADD FOREIGN KEY (Person_Id) REFERENCES Person(Id);
ALTER TABLE PersonId_GroupId ADD FOREIGN KEY (Group_Id) REFERENCES StudentGroup(Id);