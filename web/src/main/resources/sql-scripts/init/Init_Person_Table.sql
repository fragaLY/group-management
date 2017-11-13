USE GroupManagement;

INSERT INTO Person (Contacts_Id, PersonalData_Id, PersonRole_Id) values ((SELECT c.Id FROM CONTACTS c WHERE c.EMAIL = 'fragalymlg@gmail.com'), 1, (SELECT p.Id FROM PERSONROLE p WHERE p.ROLE = 'ADMIN'));
INSERT INTO Person (Contacts_Id, PersonalData_Id, PersonRole_Id) values ((SELECT c.Id FROM CONTACTS c WHERE c.EMAIL = 'i.yana.blade@gmail.com'), 2, (SELECT p.Id FROM PERSONROLE p WHERE p.ROLE = 'STUDENT'));
INSERT INTO Person (Contacts_Id, PersonalData_Id, PersonRole_Id) values ((SELECT c.Id FROM CONTACTS c WHERE c.EMAIL = 'i.korshakov@yandex.ru'), 3, (SELECT p.Id FROM PERSONROLE p WHERE p.ROLE = 'STUDENT'));