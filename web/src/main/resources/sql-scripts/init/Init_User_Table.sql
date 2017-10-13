USE GroupManagement;

INSERT INTO User (Person_Id, Login, Password) values ((SELECT p.Id FROM PERSON p INNER JOIN Contacts c ON p.Contacts_Id = c.Id where c.Email = 'fragalymlg@gmail.com'), 'VK_AZ141', 'Password');
INSERT INTO User (Person_Id, Login, Password) values ((SELECT p.Id FROM PERSON p INNER JOIN Contacts c ON p.Contacts_Id = c.Id where c.Email = 'i.yana.blade@gmail.com'), 'YS_AZ141', 'Password');
INSERT INTO User (Person_Id, Login, Password) values ((SELECT p.Id FROM PERSON p INNER JOIN Contacts c ON p.Contacts_Id = c.Id where c.Email = 'i.korshakov@yandex.ru'), 'IK_AZ141', 'Password');