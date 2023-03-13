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

-- Insert 5 random entries into Course table
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, course_start_period, course_end_period, total_places, available_places, course_fee, place, course_state, teacher_id)
VALUES 
  (4, 'English Conversation', 'Improve your spoken English with this course.', 'By the end of the course, students will be able to engage in conversations on various topics.', 20, '2023-12-01', '10:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Online', 'Active', 1),
  (5, 'Introduction to Python', 'Learn the basics of Python programming language.', 'By the end of the course, students will be able to write simple programs using Python.', 30, '2023-12-01', '14:00:00', '2023-05-01', '2023-06-01', 30, 20, 150, 'Classroom A', 'Active', 2),
  (6, 'Digital Marketing', 'Learn the basics of digital marketing strategies and tools.', 'By the end of the course, students will be able to develop a digital marketing plan for a business.', 25, '2023-12-01', '16:00:00', '2023-06-01', '2023-07-01', 25, 15, 200, 'Online', 'Active', 3),
  (7, 'Spanish for Beginners', 'Learn the basics of the Spanish language.', 'By the end of the course, students will be able to introduce themselves and hold simple conversations in Spanish.', 40, '2023-12-01', '18:00:00', '2023-07-01', '2023-08-01', 40, 30, 120, 'Classroom B', 'Active', 2),
  (8, 'Data Analysis with Excel', 'Learn how to use Excel to analyze and visualize data.', 'By the end of the course, students will be able to use various Excel tools and functions to analyze and present data.', 20, '2023-10-01', '10:00:00', '2023-08-01', '2023-09-01', 20, 10, 100, 'Classroom C', 'Active', 3);


insert into Registration values(1,"Juan", "Núñez Pérez", "64156721", "jnupe@gmail.com", "2023-01-30", "17:33:23", "Confirmed", 1);
insert into Registration values(2,"Lucas", "Palacios Miguélez", "64156721", "lpalacios@gmail.com", "2023-02-02", "08:39:10", "Received", 1);
insert into Registration values(3,"Marta", "Álvarez Pérez", "64156721", "martalv@hotmail.com", "2021-06-18", "07:56:49", "Received", 2);
insert into Registration values(4,"Miguel", "Roque Arcos", "64156721", "migroque@hotmail.com", "2023-03-01", "22:59:12", "Received", 3);

INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (1, 'John', 'Doe', '123456789', 'johndoe@email.com');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (2, 'Mary', 'Smith', '987654321', 'marysmith@email.com');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email) 
VALUES (3, 'Peter', 'Johnson', '555555555', 'peterjohnson@email.com');


-- Insert 5 random entries into Teacher table
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email)
VALUES 
  (4, 'John', 'Smith', '555123456', 'john.smith@example.com'),
  (5, 'Mary', 'Johnson', '555987654', 'mary.johnson@example.com'),
  (6, 'David', 'Brown', '555456789', 'david.brown@example.com'),
  (7, 'Sarah', 'Taylor', '555234567', 'sarah.taylor@example.com'),
  (8, 'James', 'Miller', '555345678', 'james.miller@example.com');


-- Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    (5, 'John', 'Doe', '123456789', 'john.doe@email.com', '2023-03-13', '10:00:00', 'Confirmed', 1),
    (6, 'Jane', 'Doe', '987654321', 'jane.doe@email.com', '2023-03-14', '14:00:00', 'Confirmed', 1),
    (7, 'Michael', 'Johnson', '456789123', 'michael.johnson@email.com', '2023-03-15', '16:00:00', 'Confirmed', 2),
    (8, 'Sarah', 'Williams', '789123456', 'sarah.williams@email.com', '2023-03-16', '10:00:00', 'Confirmed', 2),
    (9, 'David', 'Brown', '321654987', 'david.brown@email.com', '2023-03-17', '14:00:00', 'Confirmed', 3),
    (10, 'Emily', 'Davis', '654987321', 'emily.davis@email.com', '2023-03-18', '16:00:00', 'Confirmed', 3),
    (11, 'Daniel', 'Wilson', '987321654', 'daniel.wilson@email.com', '2023-03-19', '10:00:00', 'Confirmed', 4),
    (12, 'Olivia', 'Moore', '321789654', 'olivia.moore@email.com', '2023-03-20', '14:00:00', 'Confirmed', 4);


-- Insert 5 random entries into Teacher table
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email)
VALUES 
  (9, 'John', 'Smith', '555123456', 'john.smith@example.com'),
  (10, 'Mary', 'Johnson', '555987654', 'mary.johnson@example.com'),
  (11, 'David', 'Brown', '555456789', 'david.brown@example.com'),
  (12, 'Sarah', 'Taylor', '555234567', 'sarah.taylor@example.com'),
  (13, 'James', 'Miller', '555345678', 'james.miller@example.com');

-- Insert 5 random entries into Course table
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, course_start_period, course_end_period, total_places, available_places, course_fee, place, course_state, teacher_id)
VALUES 
  (9, 'Mobile App Development with React Native', 'Learn to build mobile apps with React Native', 'Develop mobile app development skills', 60, '2023-8-01', '18:00:00', '2023-07-01', '2023-08-01', 10, 5, 250, 'In-person', 'Active', 4),
  (10, 'Database Design with MySQL', 'Learn how to design and create databases using MySQL', 'Learn how to design and implement databases, and how to use SQL to manage data', 30, '2023-12-01', '12:00:00', '2023-09-01', '2023-10-31', 20, 20, 150, 'Online', 'Active', 1);

-- Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    
    (13, 'Isabella', 'Wilson', '321654987', 'isabella.wilson@email.com', '2023-03-25', '14:00:00', 'registered', 7),
    (14, 'Liam', 'Davis', '654987321', 'liam.davis@email.com', '2023-03-26', '16:00:00', 'registered', 7),
    (15, 'Mason', 'Anderson', '987321654', 'mason.anderson@email.com', '2023-03-27', '10:00:00', 'registered', 8),
    (16, 'Evelyn', 'Moore', '321789654', 'evelyn.moore@email.com', '2023-03-28', '14:00:00', 'registered', 8),
    (17, 'Noah', 'Martin', '123456789', 'noah.martin@email.com', '2023-03-29', '10:00:00', 'registered', 9),
    (18, 'Charlotte', 'Clark', '987654321', 'charlotte.clark@email.com', '2023-03-30', '14:00:00', 'registered', 9),
    (19, 'Amelia', 'Rodriguez', '456789123', 'amelia.rodriguez@email.com', '2023-03-31', '16:00:00', 'registered', 10),
    (20, 'Sophia', 'Garcia', '789123456', 'sophia.garcia@email.com', '2023-04-01', '10:00:00', 'registered', 10);

INSERT INTO Invoice (invoice_id, invoice_number, invoice_date, invoice_quantity, invoice_state, teacher_id, course_id)
VALUES
  (1, 1001, '2023-03-01', 100, 'Paid', null, 1),
  (2, 1002, '2023-03-02', 100, 'Paid', null, 2),
  (3, 1003, '2023-03-03', 80, 'Paid', null, 3),
  (4, 1004, '2023-03-04', 120, 'Paid', null, 4),
  (5, 1005, '2023-03-05', 90, 'Paid', null, 5),
  (6, 1006, '2023-03-06', 60, 'Paid', null, 6),
  (7, 1007, '2023-03-07', 110, 'Paid', null, 7),
  (8, 1008, '2023-03-08', 70, 'Paid', null, 8),
  (9, 1009, '2023-03-09', 130, 'Paid', null, 9),
  (10, 1010, '2023-03-10', 85, 'Paid', null, 10);

-- Payment corresponding to the invoice
INSERT INTO Payment (payment_id, amount, payment_date, payment_time, payment_type, invoice_id, reg_id)
VALUES
  (1, 100, '2023-03-01', '14:30:00', 'Bank transfer', 1, 1),
  (2, 50, '2023-03-02', '10:15:00', 'Bank transfer', 2, 2),
  (3, 80, '2023-03-03', '11:45:00', 'Bank transfer', 3, 3),
  (4, 120, '2023-03-04', '15:00:00', 'Bank transfer', 4, 4),
  (5, 90, '2023-03-05', '09:00:00', 'Bank transfer', 5, 5),
  (6, 60, '2023-03-06', '13:30:00', 'Bank transfer', 6, 6),
  (7, 110, '2023-03-07', '16:00:00', 'Bank transfer', 7, 7),
  (8, 70, '2023-03-08', '08:45:00', 'Bank transfer', 8, 8),
  (9, 130, '2023-03-09', '12:15:00', 'Bank transfer', 9, 9),
  (10, 85, '2023-03-10', '17:30:00', 'Bank transfer', 10, 10);








