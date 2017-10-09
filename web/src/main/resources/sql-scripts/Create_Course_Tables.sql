USE GroupManagement;
GO

IF OBJECT_ID('Course', 'U') IS NULL
BEGIN
CREATE TABLE [Course] (
	Id bigint NOT NULL,
	Course tinyint NOT NULL UNIQUE,
  CONSTRAINT [PK_COURSE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('Course', 'U') IS NULL
BEGIN
CREATE TABLE [Course] (
	Id bigint NOT NULL,
	Course tinyint NOT NULL UNIQUE,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_COURSE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO
