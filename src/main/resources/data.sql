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
  (1, 'English Conversation', 'Improve your spoken English with this course.', 'By the end of the course, students will be able to engage in conversations on various topics.', 20, '2023-12-01', '10:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Online', 'Active', 1),
  (2, 'Introduction to Python', 'Learn the basics of Python programming language.', 'By the end of the course, students will be able to write simple programs using Python.', 30, '2023-12-01', '14:00:00', '2023-05-01', '2023-06-01', 30, 20, 150, 'Classroom A', 'Active', 2),
  (3, 'Digital Marketing', 'Learn the basics of digital marketing strategies and tools.', 'By the end of the course, students will be able to develop a digital marketing plan for a business.', 25, '2023-12-01', '16:00:00', '2023-06-01', '2023-07-01', 25, 15, 200, 'Online', 'Active', 3),
  (4, 'Spanish for Beginners', 'Learn the basics of the Spanish language.', 'By the end of the course, students will be able to introduce themselves and hold simple conversations in Spanish.', 40, '2023-12-01', '18:00:00', '2023-07-01', '2023-08-01', 40, 30, 120, 'Classroom B', 'Active', 4),
  (5, 'Data Analysis with Excel', 'Learn how to use Excel to analyze and visualize data.', 'By the end of the course, students will be able to use various Excel tools and functions to analyze and present data.', 20, '2023-10-01', '10:00:00', '2023-08-01', '2023-09-01', 20, 10, 100, 'Classroom C', 'Active', 4),
  (6, 'Introduction to Python Programming', 'Learn the basics of Python programming language', 'Develop basic programming skills in Python', 40, '2023-12-01', '09:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Online', 'Active', 1),
  (7, 'Web Development with HTML and CSS', 'Learn to build static web pages with HTML and CSS', 'Understand the basics of web development', 30, '2023-10-01', '14:00:00', '2023-05-01', '2023-06-01', 15, 5, 150, 'In-person', 'Active', 2),
  (8, 'Data Analysis with Pandas', 'Learn to manipulate and analyze data using Pandas library', 'Develop data analysis skills with Python', 50, '2023-10-01', '10:00:00', '2023-06-01', '2023-07-01', 25, 20, 200, 'Online', 'Active', 3),
  (9, 'Mobile App Development with React Native', 'Learn to build mobile apps with React Native', 'Develop mobile app development skills', 60, '2023-8-01', '18:00:00', '2023-07-01', '2023-08-01', 10, 5, 250, 'In-person', 'Active', 4),
  (10, 'Database Design with MySQL', 'Learn how to design and create databases using MySQL', 'Learn how to design and implement databases, and how to use SQL to manage data', 30, '2023-12-01', '12:00:00', '2023-09-01', '2023-10-31', 20, 20, 150, 'Online', 'Active', 1);

-- Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    (1, 'John', 'Doe', '123456789', 'john.doe@email.com', '2023-03-13', '10:00:00', 'registered', 1),
    (2, 'Jane', 'Doe', '987654321', 'jane.doe@email.com', '2023-03-14', '14:00:00', 'registered', 1),
    (3, 'Michael', 'Johnson', '456789123', 'michael.johnson@email.com', '2023-03-15', '16:00:00', 'registered', 2),
    (4, 'Sarah', 'Williams', '789123456', 'sarah.williams@email.com', '2023-03-16', '10:00:00', 'registered', 2),
    (5, 'David', 'Brown', '321654987', 'david.brown@email.com', '2023-03-17', '14:00:00', 'cancelled', 3),
    (6, 'Emily', 'Davis', '654987321', 'emily.davis@email.com', '2023-03-18', '16:00:00', 'registered', 3),
    (7, 'Daniel', 'Wilson', '987321654', 'daniel.wilson@email.com', '2023-03-19', '10:00:00', 'registered', 4),
    (8, 'Olivia', 'Moore', '321789654', 'olivia.moore@email.com', '2023-03-20', '14:00:00', 'registered', 4),
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








