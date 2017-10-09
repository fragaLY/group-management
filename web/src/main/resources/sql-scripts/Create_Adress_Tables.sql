USE GroupManagement;

IF OBJECT_ID('Address', 'U') IS NULL
BEGIN
CREATE TABLE [Address] (
	Id bigint NOT NULL,
	Country varchar(50) NOT NULL,
	City varchar(50) NOT NULL,
	Street varchar(50) NOT NULL,
	Home varchar(10) NOT NULL,
	ApartmentNumber varchar(10) NOT NULL,
  CONSTRAINT [PK_ADDRESS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC 
  ) WITH (IGNORE_DUP_KEY = OFF)
)
END
GO

USE GroupManagement_Deleted;

IF OBJECT_ID('Address', 'U') IS NULL
BEGIN
CREATE TABLE [Address] (
	Id bigint NOT NULL,
	Country varchar(50) NOT NULL,
	City varchar(50) NOT NULL,
	Street varchar(50) NOT NULL,
	Home varchar(10) NOT NULL,
	ApartmentNumber varchar(10) NOT NULL,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_ADDRESS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC 
  ) WITH (IGNORE_DUP_KEY = OFF)
)
END
GO