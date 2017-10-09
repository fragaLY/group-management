USE GroupManagement;
GO

IF OBJECT_ID('PersonRole', 'U') IS NULL
BEGIN

CREATE TABLE [PersonRole] (
	Id bigint NOT NULL,
	Role varchar NOT NULL UNIQUE,
  CONSTRAINT [PK_PERSONROLE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('PersonRole', 'U') IS NULL
BEGIN

CREATE TABLE [PersonRole] (
	Id bigint NOT NULL,
	Role varchar NOT NULL UNIQUE,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_PERSONROLE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)

END
