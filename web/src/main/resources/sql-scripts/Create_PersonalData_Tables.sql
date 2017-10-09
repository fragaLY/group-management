USE GroupManagement;
GO

IF OBJECT_ID('PersonalData', 'U') IS NULL
BEGIN

CREATE TABLE [PersonalData] (
	Id bigint NOT NULL,
	FirstName varchar(100) NOT NULL,
	LastName varchar(100) NOT NULL,
  CONSTRAINT [PK_PERSONALDATA] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('PersonalData', 'U') IS NULL
BEGIN

CREATE TABLE [PersonalData] (
	Id bigint NOT NULL,
	FirstName varchar(100) NOT NULL,
	LastName varchar(100) NOT NULL,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_PERSONALDATA] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
