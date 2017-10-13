USE GroupManagement;

INSERT INTO Contacts (Address_Id, Phone, Skype, Email) values (
(SELECT a.Id FROM ADDRESS a WHERE a.Country = 'Belarus' AND a.CITY = 'Mahileu' AND a.STREET = '30 Let Pobedy' AND a.HOME = '8a' AND a.ApartmentNumber = '46'),
'+375(29)246-3050',
'frager_',
'fragalymlg@gmail.com');

INSERT INTO Contacts (Address_Id, Phone, Skype, Email) values (
(SELECT a.Id FROM ADDRESS a WHERE a.Country = 'Belarus' AND a.CITY = 'Kolodischi' AND a.STREET = 'Tyulenina' AND a.HOME = '24' AND a.ApartmentNumber = '72'),
'+375(29)944-7055',
'murmuryasik',
'i.yana.blade@gmail.com');

INSERT INTO Contacts (Address_Id, Phone, Skype, Email) values (
(SELECT a.Id FROM ADDRESS a WHERE a.Country = 'Belarus' AND a.CITY = 'Mahileu' AND a.STREET = 'Grishina' AND a.HOME = '122a' AND a.ApartmentNumber = '6'),
'+375(29)128-2950',
'tnomore',
'i.korshakov@yandex.ru');