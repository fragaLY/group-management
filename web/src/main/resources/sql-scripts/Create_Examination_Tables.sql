USE GroupManagement;
GO

IF OBJECT_ID('Examination', 'U') IS NULL
BEGIN
CREATE TABLE [Examination] (
	Id bigint NOT NULL,
	Grade_Id bigint,
	ExaminationType_Id bigint NOT NULL,
  CONSTRAINT [PK_EXAMINATION] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

ALTER TABLE [Examination] WITH CHECK ADD CONSTRAINT [Examination_fk0] FOREIGN KEY ([Grade_Id]) REFERENCES [Grade]([Id])
GO

ALTER TABLE [Examination] CHECK CONSTRAINT [Examination_fk0]
GO

ALTER TABLE [Examination] WITH CHECK ADD CONSTRAINT [Examination_fk1] FOREIGN KEY ([ExaminationType_Id]) REFERENCES [ExaminationType]([Type])
GO

ALTER TABLE [Examination] CHECK CONSTRAINT [Examination_fk1]
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('Examination', 'U') IS NULL
BEGIN
CREATE TABLE [Examination] (
	Id bigint NOT NULL,
	Grade_Id bigint,
	ExaminationType_Id bigint NOT NULL,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_EXAMINATION] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

ALTER TABLE [Examination] WITH CHECK ADD CONSTRAINT [Examination_fk0] FOREIGN KEY ([Grade_Id]) REFERENCES [Grade]([Id])
GO

ALTER TABLE [Examination] CHECK CONSTRAINT [Examination_fk0]
GO

ALTER TABLE [Examination] WITH CHECK ADD CONSTRAINT [Examination_fk1] FOREIGN KEY ([ExaminationType_Id]) REFERENCES [ExaminationType]([Type])
GO

ALTER TABLE [Examination] CHECK CONSTRAINT [Examination_fk1]
GO