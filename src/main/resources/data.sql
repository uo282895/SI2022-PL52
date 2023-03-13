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

-- Insert 5 random entries into Teacher table
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email)
VALUES 
  (1, 'John', 'Smith', '555123456', 'john.smith@example.com'),
  (2, 'Mary', 'Johnson', '555987654', 'mary.johnson@example.com'),
  (3, 'David', 'Brown', '555456789', 'david.brown@example.com'),
  (4, 'Sarah', 'Taylor', '555234567', 'sarah.taylor@example.com'),
  (5, 'James', 'Miller', '555345678', 'james.miller@example.com');

-- Insert 5 random entries into Course table
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, course_start_period, course_end_period, total_places, available_places, course_fee, place, course_state, teacher_id)
VALUES 
  (1, 'English Conversation', 'Improve your spoken English with this course.', 'By the end of the course, students will be able to engage in conversations on various topics.', 20, '2023-12-01', '10:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Online', 'Open', 101),
  (2, 'Introduction to Python', 'Learn the basics of Python programming language.', 'By the end of the course, students will be able to write simple programs using Python.', 30, '2023-12-01', '14:00:00', '2023-05-01', '2023-06-01', 30, 20, 150, 'Classroom A', 'Open', 102),
  (3, 'Digital Marketing', 'Learn the basics of digital marketing strategies and tools.', 'By the end of the course, students will be able to develop a digital marketing plan for a business.', 25, '2023-12-01', '16:00:00', '2023-06-01', '2023-07-01', 25, 15, 200, 'Online', 'Open', 103),
  (4, 'Spanish for Beginners', 'Learn the basics of the Spanish language.', 'By the end of the course, students will be able to introduce themselves and hold simple conversations in Spanish.', 40, '2023-12-01', '18:00:00', '2023-07-01', '2023-08-01', 40, 30, 120, 'Classroom B', 'Open', 104),
  (5, 'Data Analysis with Excel', 'Learn how to use Excel to analyze and visualize data.', 'By the end of the course, students will be able to use various Excel tools and functions to analyze and present data.', 20, '2023-10-01', '10:00:00', '2023-08-01', '2023-09-01', 20, 10, 100, 'Classroom C', 'Open', 105);



-- Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    (1, 'John', 'Doe', '123456789', 'john.doe@email.com', '2023-03-13', '10:00:00', 'registered', 1),
    (2, 'Jane', 'Doe', '987654321', 'jane.doe@email.com', '2023-03-14', '14:00:00', 'registered', 1),
    (3, 'Michael', 'Johnson', '456789123', 'michael.johnson@email.com', '2023-03-15', '16:00:00', 'registered', 2),
    (4, 'Sarah', 'Williams', '789123456', 'sarah.williams@email.com', '2023-03-16', '10:00:00', 'registered', 2),
    (5, 'David', 'Brown', '321654987', 'david.brown@email.com', '2023-03-17', '14:00:00', 'registered', 3),
    (6, 'Emily', 'Davis', '654987321', 'emily.davis@email.com', '2023-03-18', '16:00:00', 'registered', 3),
    (7, 'Daniel', 'Wilson', '987321654', 'daniel.wilson@email.com', '2023-03-19', '10:00:00', 'registered', 4),
    (8, 'Olivia', 'Moore', '321789654', 'olivia.moore@email.com', '2023-03-20', '14:00:00', 'registered', 4);
    (9, 'Anna', 'Lee', '123456789', 'anna.lee@email.com', '2023-03-21', '10:00:00', 'registered', 5),
    (10, 'Lucas', 'Miller', '987654321', 'lucas.miller@email.com', '2023-03-22', '14:00:00', 'registered', 5),
    (11, 'Ethan', 'Jones', '456789123', 'ethan.jones@email.com', '2023-03-23', '16:00:00', 'registered', 6),
    (12, 'Ava', 'Taylor', '789123456', 'ava.taylor@email.com', '2023-03-24', '10:00:00', 'registered', 6),
    (13, 'Isabella', 'Wilson', '321654987', 'isabella.wilson@email.com', '2023-03-25', '14:00:00', 'registered', 7),
    (14, 'Liam', 'Davis', '654987321', 'liam.davis@email.com', '2023-03-26', '16:00:00', 'registered', 7),
    (15, 'Mason', 'Anderson', '987321654', 'mason.anderson@email.com', '2023-03-27', '10:00:00', 'registered', 8),
    (16, 'Evelyn', 'Moore', '321789654', 'evelyn.moore@email.com', '2023-03-28', '14:00:00', 'registered', 8),
    (17, 'Noah', 'Martin', '123456789', 'noah.martin@email.com', '2023-03-29', '10:00:00', 'registered', 9),
    (18, 'Charlotte', 'Clark', '987654321', 'charlotte.clark@email.com', '2023-03-30', '14:00:00', 'registered', 9),
    (19, 'Amelia', 'Rodriguez', '456789123', 'amelia.rodriguez@email.com', '2023-03-31', '16:00:00', 'registered', 10),
    (20, 'Sophia', 'Garcia', '789123456', 'sophia.garcia@email.com', '2023-04-01', '10:00:00', 'registered', 10)







