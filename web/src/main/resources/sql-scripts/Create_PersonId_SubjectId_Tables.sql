USE GroupManagement;
GO

IF OBJECT_ID('PersonId_SubjectId', 'U') IS NULL
BEGIN
CREATE TABLE [PersonId_SubjectId] (
	Person_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
)
END
GO

ALTER TABLE [PersonId_SubjectId] WITH CHECK ADD CONSTRAINT [PersonId_SubjectId_fk0] FOREIGN KEY ([Person_Id]) REFERENCES [Person]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [PersonId_SubjectId] CHECK CONSTRAINT [PersonId_SubjectId_fk0]
GO

ALTER TABLE [PersonId_SubjectId] WITH CHECK ADD CONSTRAINT [PersonId_SubjectId_fk1] FOREIGN KEY ([Subject_Id]) REFERENCES [Subject]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [PersonId_SubjectId] CHECK CONSTRAINT [PersonId_SubjectId_fk1]

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('PersonId_SubjectId', 'U') IS NULL
BEGIN
CREATE TABLE [PersonId_SubjectId] (
	Person_Id bigint NOT NULL,
	Subject_Id bigint NOT NULL
)
END
GO

ALTER TABLE [PersonId_GroupID] WITH CHECK ADD CONSTRAINT [PersonId_GroupID_fk0] FOREIGN KEY ([Person_Id]) REFERENCES [Person]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [PersonId_SubjectId] WITH CHECK ADD CONSTRAINT [PersonId_SubjectId_fk0] FOREIGN KEY ([Person_Id]) REFERENCES [Person]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [PersonId_SubjectId] CHECK CONSTRAINT [PersonId_SubjectId_fk0]
GO

ALTER TABLE [PersonId_SubjectId] WITH CHECK ADD CONSTRAINT [PersonId_SubjectId_fk1] FOREIGN KEY ([Subject_Id]) REFERENCES [Subject]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [PersonId_SubjectId] CHECK CONSTRAINT [PersonId_SubjectId_fk1]
