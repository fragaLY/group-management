USE GroupManagement;

CREATE TABLE SubjectId_GroupId (
	Group_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
);

ALTER TABLE SubjectId_GroupId ADD FOREIGN KEY (Group_Id) REFERENCES StudentGroup(Id);
ALTER TABLE SubjectId_GroupId ADD FOREIGN KEY (Subject_Id) REFERENCES Subject(Id);

USE GroupManagement_Deleted;

CREATE TABLE SubjectId_GroupId (
	Group_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
);

ALTER TABLE SubjectId_GroupId ADD FOREIGN KEY (Group_Id) REFERENCES StudentGroup(Id);
ALTER TABLE SubjectId_GroupId ADD FOREIGN KEY (Subject_Id) REFERENCES Subject(Id);
