--Datos para carga inicial de la base de datos
--delete from carreras;
--insert into carreras(id,inicio,fin,fecha,descr) values 
--	(100,'2016-10-05','2016-10-25','2016-11-09','finalizada'),
--	(101,'2016-10-05','2016-10-25','2016-11-10','en fase 3'),
--	(102,'2016-11-05','2016-11-09','2016-11-20','en fase 2'),
--	(103,'2016-11-10','2016-11-15','2016-11-21','en fase 1'),
--	(104,'2016-11-11','2016-11-15','2016-11-22','antes inscripcion');


delete from Course;
delete from Registration;
delete from Payment;

insert into Course values(1, "Software Engineering Fundamentals", "It is a course specially designed for professionals", "To learn the MEDEPA methodology", 3, "2023-03-14", "18:00:00", "2023-01-13", "2023-02-26", 50,50, 700, "Oviedo. COIIPA", "Active", 1);
insert into Course values(2, "MBA", "Business administration master", "To learn how to deal with money", 7, "2021-07-23", "12:00:00", "2021-05-23", "2021-06-29", 75,75,350, "Oviedo. Informatics School", "Cancelled", 2);
insert into Course values(3, "Azure Cloud certifications", "A one-day formative action oriented to cloud developers", "To learn a new fixture introduced by Microsoft in its Azure servers", 5, "2023-04-12", "13:00:00", "2023-02-23", "2023-03-23", 50,2, 500, "Oviedo. COIIPA", "Active", 3);

insert into Registration values(1,"Juan", "Núñez Pérez", "64156721", "jnupe@gmail.com", "2023-01-30", "17:33:23", "Confirmed", 1);
insert into Registration values(2,"Lucas", "Palacios Miguélez", "64156721", "lpalacios@gmail.com", "2023-02-02", "08:39:10", "Received", 1);
insert into Registration values(3,"Marta", "Álvarez Pérez", "64156721", "martalv@hotmail.com", "2021-06-18", "07:56:49", "Received", 2);
insert into Registration values(4,"Miguel", "Roque Arcos", "64156721", "migroque@hotmail.com", "2023-03-01", "22:59:12", "Received", 3);
insert into Registration values(17,"Hola","Jeje","117121278", "kik","a","a","Received",3);

INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (1, 'John', 'Doe', '123456789', 'johndoe@email.com');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (2, 'Mary', 'Smith', '987654321', 'marysmith@email.com');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (3, 'Peter', 'Johnson', '555555555', 'peterjohnson@email.com');


