USE GroupManagement;

INSERT INTO StudentGroup (Name, Course_Id, Semester_Id, Faculty_Id) values ('ASOIZ-141', (SELECT c.Id FROM Course c WHERE c.Course = 4), (SELECT s.Id FROM Semester s WHERE s.Semester = 7), (SELECT f.Id FROM Faculty f WHERE f.Name = 'DISTANCE-LEARNING'));