USE GroupManagement;

INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'EXAM'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'CREDIT'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'CREDIT'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'EXAM'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'CREDIT'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'EXAM'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'EXAM'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'EXAM'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'CREDIT'));
INSERT INTO Examination (ExaminationType_Id) VALUES ((SELECT e.Id FROM EXAMINATIONTYPE e WHERE e.TYPE = 'NOTHING'));