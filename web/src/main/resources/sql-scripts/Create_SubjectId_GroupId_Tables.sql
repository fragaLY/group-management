USE GroupManagement;
GO

IF OBJECT_ID('SubjectId_GroupId', 'U') IS NULL
BEGIN
CREATE TABLE [SubjectId_GroupId] (
	Group_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
)
END
GO

ALTER TABLE [SubjectId_GroupId] WITH CHECK ADD CONSTRAINT [SubjectId_GroupId_fk0] FOREIGN KEY ([Group_Id]) REFERENCES [Group]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [SubjectId_GroupId] CHECK CONSTRAINT [SubjectId_GroupId_fk0]
GO

ALTER TABLE [SubjectId_GroupId] WITH CHECK ADD CONSTRAINT [SubjectId_GroupId_fk1] FOREIGN KEY ([Subject_Id]) REFERENCES [Subject]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [SubjectId_GroupId] CHECK CONSTRAINT [SubjectId_GroupId_fk1]
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('SubjectId_GroupId', 'U') IS NULL
BEGIN
CREATE TABLE [SubjectId_GroupId] (
	Group_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
)
END
GO

ALTER TABLE [PersonId_GroupID] WITH CHECK ADD CONSTRAINT [PersonId_GroupID_fk0] FOREIGN KEY ([Person_Id]) REFERENCES [Person]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [SubjectId_GroupId] WITH CHECK ADD CONSTRAINT [SubjectId_GroupId_fk0] FOREIGN KEY ([Group_Id]) REFERENCES [Group]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [SubjectId_GroupId] CHECK CONSTRAINT [SubjectId_GroupId_fk0]
GO

ALTER TABLE [SubjectId_GroupId] WITH CHECK ADD CONSTRAINT [SubjectId_GroupId_fk1] FOREIGN KEY ([Subject_Id]) REFERENCES [Subject]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [SubjectId_GroupId] CHECK CONSTRAINT [SubjectId_GroupId_fk1]
GO