IF (SCHEMA_ID('star_wars') IS NULL)
BEGIN
EXEC ('CREATE SCHEMA [star_wars]')
END
GO