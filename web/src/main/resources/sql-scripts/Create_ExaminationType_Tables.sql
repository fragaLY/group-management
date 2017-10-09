USE GroupManagement;
GO

IF OBJECT_ID('ExaminationType', 'U') IS NULL
BEGIN
CREATE TABLE [ExaminationType] (
	Id bigint NOT NULL,
	Type varchar(25) NOT NULL,
  CONSTRAINT [PK_EXAMINATIONTYPE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
GO

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('ExaminationType', 'U') IS NULL
BEGIN
CREATE TABLE [ExaminationType] (
	Id bigint NOT NULL,
	Type varchar(25) NOT NULL,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_EXAMINATIONTYPE] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
END
