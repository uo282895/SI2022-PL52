delete from Course;
delete from Registration;
delete from Payment;

insert into Course values(1, "Software Engineering Fundamentals", "It is a course specially designed for professionals", "To learn the MEDEPA methodology", 3, "2023-03-14", "2023-03-14", "2023-01-13", "2023-02-26", 50, 700, "Active", 1, 1500);
insert into Course values(2, "MBA", "Business administration master", "To learn how to deal with money", 7, "2021-07-23", "2021-07-23", "2021-05-23", "2021-06-29", 75,350, "Cancelled", 2, 1000);
insert into Course values(3, "Azure Cloud certifications", "A one-day formative action oriented to cloud developers", "To learn a new fixture introduced by Microsoft in its Azure servers", 5, "2023-04-12", "2023-04-12", "2023-02-23", "2023-03-23", 50, 500, "Active", 3, 2300);

-- Insert 5 random entries into Course table

---INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_start_date, course_time, course_start_period, course_end_period, total_places, course_fee, place, course_state, teacher_id)
--VALUES 
 -- (4, 'English Conversation', 'Improve your spoken English with this course.', 'By the end of the course, students will be able to engage in conversations on various topics.', 20, '2023-12-01', '10:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Online', 'Active', 1),
 -- (5, 'Introduction to Python', 'Learn the basics of Python programming language.', 'By the end of the course, students will be able to write simple programs using Python.', 30, '2023-12-01', '14:00:00', '2023-05-01', '2023-06-01', 30, 20, 150, 'Classroom A', 'Active', 2),
 -- (6, 'Digital Marketing', 'Learn the basics of digital marketing strategies and tools.', 'By the end of the course, students will be able to develop a digital marketing plan for a business.', 25, '2023-12-01', '16:00:00', '2023-06-01', '2023-07-01', 25, 15, 200, 'Online', 'Active', 3),
 -- (7, 'Spanish for Beginners', 'Learn the basics of the Spanish language.', 'By the end of the course, students will be able to introduce themselves and hold simple conversations in Spanish.', 40, '2023-12-01', '18:00:00', '2023-07-01', '2023-08-01', 40, 30, 120, 'Classroom B', 'Active', 2),
 -- (8, 'Data Analysis with Excel', 'Learn how to use Excel to analyze and visualize data.', 'By the end of the course, students will be able to use various Excel tools and functions to analyze and present data.', 20, '2023-10-01', '10:00:00', '2023-08-01', '2023-09-01', 20, 10, 100, 'Classroom C', 'Active', 3);

--INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, course_start_period, course_end_period, total_places, course_fee, course_state, teacher_id, teacher_remuneration)
--VALUES 
--  (4, 'English Conversation', 'Improve your spoken English with this course.', 'By the end of the course, students will be able to engage in conversations on various topics.', 20, '2023-12-01', '10:00:00', '2023-04-01', '2023-05-01', 20, 10, 100, 'Active', 1, 2000),
 -- (5, 'Introduction to Python', 'Learn the basics of Python programming language.', 'By the end of the course, students will be able to write simple programs using Python.', 30, '2023-12-01', '14:00:00', '2023-05-01', '2023-06-01', 30, 20, 150, 'Active', 2, 3000),
-- (6, 'Digital Marketing', 'Learn the basics of digital marketing strategies and tools.', 'By the end of the course, students will be able to develop a digital marketing plan for a business.', 25, '2023-12-01', '16:00:00', '2023-06-01', '2023-07-01', 25, 15, 200, 'Active', 3, 4000),
--  (7, 'Spanish for Beginners', 'Learn the basics of the Spanish language.', 'By the end of the course, students will be able to introduce themselves and hold simple conversations in Spanish.', 40, '2023-12-01', '18:00:00', '2023-07-01', '2023-08-01', 40, 30, 120, 'Active', 2, 2500),
--  (8, 'Data Analysis with Excel', 'Learn how to use Excel to analyze and visualize data.', 'By the end of the course, students will be able to use various Excel tools and functions to analyze and present data.', 20, '2023-10-01', '10:00:00', '2023-08-01', '2023-09-01', 20, 10, 100, 'Active', 3, 2000);



insert into Registration values(1,"Juan", "Núñez Pérez", "64156721", "jnupe@gmail.com", "2023-01-30", "17:33:23", "Confirmed", 1);
insert into Registration values(2,"Lucas", "Palacios Miguélez", "64156721", "lpalacios@gmail.com", "2023-02-02", "08:39:10", "Received", 1);
insert into Registration values(3,"Marta", "Álvarez Pérez", "64156721", "martalv@hotmail.com", "2021-06-18", "07:56:49", "Received", 2);
insert into Registration values(4,"Miguel", "Roque Arcos", "64156721", "migroque@hotmail.com", "2023-03-01", "22:59:12", "Received", 3);

INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email, fiscal_number, teacher_address, fiscal_number) 
VALUES (1, 'John', 'Doe', '123456789', 'johndoe@email.com', "17819", "Calle Uría",'ABC123');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email, fiscal_number, teacher_address, fiscal_number) 
VALUES (2, 'Mary', 'Smith', '987654321', 'marysmith@email.com', "181712", "Calle Uría", 'ABC123');
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email, fiscal_number, teacher_address, fiscal_number) 
VALUES (3, 'Peter', 'Johnson', '555555555', 'peterjohnson@email.com', "182772", "Calle Uría", 'ABC123');


-- Insert 5 random entries into Teacher table
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email, fiscal_number, teacher_address, fiscal_number)
VALUES 
 (4, 'John', 'Smith', '555123456', 'john.smith@example.com', "12891","Calle Uría",'ABC123'),
 (5, 'Mary', 'Johnson', '555987654', 'mary.johnson@example.com', "19128", "Calle Uría",'ABC123'),
 (6, 'David', 'Brown', '555456789', 'david.brown@example.com', "190192", "Calle Uría",'ABC123'),
 (7, 'Sarah', 'Taylor', '555234567', 'sarah.taylor@example.com', "19910", "Calle Uría",'ABC123'),
 (8, 'James', 'Miller', '555345678', 'james.miller@example.com', "188127", "Calle Uría", 'ABC123');


-- Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    (5, 'John', 'Doe', '123456789', 'john.doe@email.com', '2023-03-13', '10:00:00', 'Confirmed', 1),
    (6, 'Jane', 'Doe', '987654321', 'jane.doe@email.com', '2023-03-14', '14:00:00', 'Confirmed', 1),
    (7, 'Michael', 'Johnson', '456789123', 'michael.johnson@email.com', '2023-03-15', '16:00:00', 'Confirmed', 2),
    (8, 'Sarah', 'Williams', '789123456', 'sarah.williams@email.com', '2023-03-16', '10:00:00', 'Confirmed', 2),
    (9, 'David', 'Brown', '321654987', 'david.brown@email.com', '2023-03-17', '14:00:00', 'Confirmed', 3),
    (10, 'Emily', 'Davis', '654987321', 'emily.davis@email.com', '2023-03-18', '16:00:00', 'Confirmed', 3),
    (11, 'Daniel', 'Wilson', '987321654', 'daniel.wilson@email.com', '2023-03-19', '10:00:00', 'Cancelled', 4),
    (12, 'Olivia', 'Moore', '321789654', 'olivia.moore@email.com', '2023-03-20', '14:00:00', 'Confirmed', 4);


-- Insert 5 random entries into Teacher table
--INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email)
--VALUES 
--  (9, 'John', 'Smith', '555123456', 'john.smith@example.com'),
--  (10, 'Mary', 'Johnson', '555987654', 'mary.johnson@example.com'),
--  (11, 'David', 'Brown', '555456789', 'david.brown@example.com'),
--  (12, 'Sarah', 'Taylor', '555234567', 'sarah.taylor@example.com'),
--  (13, 'James', 'Miller', '555345678', 'james.miller@example.com');

-- Insert 5 random entries into Course table
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_start_date, course_end_date, course_start_period, course_end_period, total_places, course_fee, course_state, teacher_id, teacher_remuneration)
VALUES 
  (9, 'Mobile App Development with React Native', 'Learn to build mobile apps with React Native', 'Develop mobile app development skills', 60, '2023-8-01', '2023-8-01', '2023-07-01', '2023-08-01', 10, 250, 'Active', 4, 1500),
  (10, 'Database Design with MySQL', 'Learn how to design and create databases using MySQL', 'Learn how to design and implement databases, and how to use SQL to manage data', 30, '2023-12-01', '2023-12-01', '2023-09-01', '2023-10-31', 20, 150, 'Active', 1, 2000);

--Insert 5 random entries into Registration table
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES 
    
    (13, 'Isabella', 'Wilson', '321654987', 'isabella.wilson@email.com', '2023-03-25', '14:00:00', 'Received', 7),
    (14, 'Liam', 'Davis', '654987321', 'liam.davis@email.com', '2023-03-26', '16:00:00', 'Registered', 7),
    (15, 'Mason', 'Anderson', '987321654', 'mason.anderson@email.com', '2023-03-27', '10:00:00', 'Registered', 8),
    (16, 'Evelyn', 'Moore', '321789654', 'evelyn.moore@email.com', '2023-03-28', '14:00:00', 'Registered', 8),
    (17, 'Noah', 'Martin', '123456789', 'noah.martin@email.com', '2023-03-29', '10:00:00', 'Registered', 9),
    (18, 'Charlotte', 'Clark', '987654321', 'charlotte.clark@email.com', '2023-03-30', '14:00:00', 'Registered', 9),
    (19, 'Amelia', 'Rodriguez', '456789123', 'amelia.rodriguez@email.com', '2023-03-31', '16:00:00', 'Registered', 10),
    (20, 'Sophia', 'Garcia', '789123456', 'sophia.garcia@email.com', '2023-04-01', '10:00:00', 'Registered', 10);


-- Payment corresponding to the invoice
INSERT INTO Payment (payment_id, amount, payment_date, payment_type, invoice_id, reg_id)
VALUES
  (1, 700, '2023-03-01', 'Professional registration', null, 1),
-- (2, 700, '2023-03-02', 'Professional registration', null, 2),
  (3, 80, '2023-03-03', 'Professional registration', null, 3),
 -- (4, 120, '2023-03-04', 'Professional registration', null, 4),
  (5, 700, '2023-03-05','Professional registration', null, 5),
  (6, 700, '2023-03-06', 'Professional registration', null, 6),
  (7, 110, '2023-03-07', 'Professional registration', null, 7),
  (8, 70, '2023-03-08', 'Professional registration', null, 8),
  (9, 500, '2023-03-09', 'Professional registration', null, 9),
  (10, 500, '2023-03-10', 'Professional registration', null, 10);

-- Insert teachers
-- Insert teachers
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email, teacher_address, fiscal_number)
VALUES
(1000, 'John', 'Doe', '123456789', 'john.doe@example.com', 'Calle Puerto de Tarna', 'ABC123'),
(1001, 'Jane', 'Doe', '987654321', 'jane.doe@example.com', 'Calle Puerto de Tarna', 'DEF456'),
(1002, 'Bob', 'Smith', '555555555', 'bob.smith@example.com', 'Calle Puerto de Tarna', 'GHI789');


-- Insert courses
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_start_date, course_end_date, course_start_period, course_end_period, total_places, course_fee, course_state, teacher_id, teacher_remuneration)
VALUES
(1000, 'Introduction to Programming', 'An introduction to computer programming', 'To learn programming basics', 60, '2023-12-01', '2023-12-01',  '2023-04-01', '2023-06-01', 20, 1000, 'Active', 1000, 500),
(1001, 'Advanced Programming', 'A course on advanced programming concepts', 'To learn advanced programming techniques', 60, '2023-12-01', '2023-12-01',  '2023-06-01', '2023-07-01', 15, 1500, 'Active', 1001, 750),
(1002, 'Web Development', 'A course on web development', 'To learn web development basics', 60, '2023-07-01', '2023-07-01', '2023-03-01', '2023-06-01', 25, 1200, 'Active', 1002, 600);

-- Insert invoices
--INSERT INTO Invoice (invoice_id, invoice_number, invoice_date, invoice_quantity, invoice_state, teacher_id, course_id)
--VALUES
--(1000, 1, '2023-05-05', 1000, 'Pending', null, 1000),
--(1001, 2, '2023-06-05', 1500, 'Paid', null, 1001),
--(1002, 3, '2023-07-05', 1200, 'Pending', 1002, 1002);

-- Insert registrations
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES
(1000, 'Alice', 'Smith', '555111111', 'alice.smith@example.com', '2023-05-03', '14:00:00', 'Pending', 1000),
(1001, 'Bob', 'Johnson', '555222222', 'bob.johnson@example.com', '2023-05-04', '14:00:00', 'Paid', 1000),
(1002, 'Charlie', 'Brown', '555333333', 'charlie.brown@example.com', '2023-05-05', '14:00:00', 'Paid', 1000);

--INSERT INTO Payment (payment_id, amount, payment_date, payment_type, invoice_id, reg_id)
--VALUES
--  (1000, 100, '2023-03-01', 'Bank transfer', 1000, 1000),
--  (1001, 50, '2023-03-02', 'Bank transfer', 1001, 1001),
--  (1002, 80, '2023-03-03', 'Bank transfer', 1002, 1002);

INSERT INTO Session (session_id, session_location, session_date, session_hour, session_hours, course_id)
VALUES (1, 'Room A', '2023-05-01', '10:00', 2, 1);

