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

insert into Course values(1, "Software Engineering Fundamentals", "It is a course specially designed for professionals", "To learn the MEDEPA methodology", 3, "Date-time","2019-05-23", "2019-07-23", 50,50, 700, "Oviedo. COIIPA", "Active");
insert into Course values(2, "MBA", "Business administration master", "To learn how to deal with money", 7, "Date-time","2019-05-23", "2019-07-23", 50,50, 700, "Oviedo. COIIPA", "Cancelled");
insert into Course values(3, "This is a trial", "Business administration master", "To learn how to deal with money", 7, "Date-time","2019-05-23", "2019-07-23", 25,50, 500, "Oviedo. COIIPA", "Active");

insert into Registration values(1,"Juan", "Palacios", "64156721", "jpalacios@gmail.com", "date-time", "Confirmed", 1);
insert into Registration values(2,"Lucas", "Palacios", "64156721", "lpalacios@gmail.com", "date-time", "Received", 1);
insert into Registration values(3,"Marta", "Palacios", "64156721", "mpalacios@gmail.com", "date-time", "Received", 2);
insert into Registration values(4,"Miguel", "Palacios", "64156721", "migpalacios@gmail.com", "date-time", "Received", 3);