USE GroupManagement;

CREATE TABLE StudentGroup (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name varchar(50) NOT NULL UNIQUE,
	Course_Id bigint NOT NULL,
	Semester_Id bigint NOT NULL,
	Faculty_Id bigint NOT NULL
);

ALTER TABLE StudentGroup ADD FOREIGN KEY (Course_Id) REFERENCES Course(Id);
ALTER TABLE StudentGroup ADD FOREIGN KEY (Semester_Id) REFERENCES Semester(Id);
ALTER TABLE StudentGroup ADD FOREIGN KEY (Faculty_Id) REFERENCES Faculty(Id);

USE GroupManagement_Deleted;

CREATE TABLE StudentGroup (
	Id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name varchar(50) NOT NULL,
	Course_Id bigint NOT NULL,
	Semester_Id bigint NOT NULL,
	Faculty_Id bigint NOT NULL,
	Deleted datetime NOT NULL
);

ALTER TABLE StudentGroup ADD FOREIGN KEY (Course_Id) REFERENCES Course(Id);
ALTER TABLE StudentGroup ADD FOREIGN KEY (Semester_Id) REFERENCES Semester(Id);
ALTER TABLE StudentGroup ADD FOREIGN KEY (Faculty_Id) REFERENCES Faculty(Id);
