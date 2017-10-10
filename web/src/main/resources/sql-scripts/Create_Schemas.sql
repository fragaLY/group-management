IF NOT EXISTS (
SELECT  schema_name
FROM    information_schema.schemata
WHERE   schema_name = 'GroupManagement' )

BEGIN
EXEC sp_executesql N'CREATE SCHEMA GroupManagement'
END

GO

IF NOT EXISTS (
SELECT  schema_name
FROM    information_schema.schemata
WHERE   schema_name = 'GroupManagement_Deleted' )

BEGIN
EXEC sp_executesql N'CREATE SCHEMA GroupManagement_Deleted'
END