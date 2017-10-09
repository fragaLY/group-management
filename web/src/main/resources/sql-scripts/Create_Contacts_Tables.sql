USE GroupManagement;
GO

IF OBJECT_ID('Contacts', 'U') IS NULL
BEGIN

CREATE TABLE [Contacts] (
	Id bigint NOT NULL,
	Address_Id bigint,
	Phone varchar(13) NOT NULL UNIQUE,
	Skype varchar(35) UNIQUE,
	Email varchar NOT NULL UNIQUE,
  CONSTRAINT [PK_CONTACTS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)

END
GO

ALTER TABLE [Contacts] WITH CHECK ADD CONSTRAINT [Contacts_fk0] FOREIGN KEY ([Address_Id]) REFERENCES [Address]([Id])
ON UPDATE CASCADE
GO

ALTER TABLE [Contacts] CHECK CONSTRAINT [Contacts_fk0]
GOa

USE GroupManagement_Deleted;
GO

IF OBJECT_ID('Contacts', 'U') IS NULL
BEGIN

CREATE TABLE [Contacts] (
	Id bigint NOT NULL,
	Address_Id bigint,
	Phone varchar(13) NOT NULL UNIQUE,
	Skype varchar(35) UNIQUE,
	Email varchar NOT NULL UNIQUE,
	Deleted datetime NOT NULL,
  CONSTRAINT [PK_CONTACTS] PRIMARY KEY CLUSTERED
  (
  [Id] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)

END
GO

ALTER TABLE [Contacts] WITH CHECK ADD CONSTRAINT [Contacts_fk0] FOREIGN KEY ([Address_Id]) REFERENCES [Address]([Id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [Contacts] CHECK CONSTRAINT [Contacts_fk0]
GO

