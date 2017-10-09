USE GroupManagement;
GO

IF OBJECT_ID('Semester', 'U') IS NULL
BEGIN
CREATE TABLE [Semester] (
	Id bigint NOT NULL,
	Semester tinyint NOT NULL UNIQUE,
  CONSTRAINT [PK_SEMESTER] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('Semester', 'U') IS NULL
BEGIN
CREATE TABLE [Semester] (
	Id bigint NOT NULL,
	Semester tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_SEMESTER] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO
