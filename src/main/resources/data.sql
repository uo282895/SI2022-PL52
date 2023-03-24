--Datos para carga inicial de la base de datos
delete from Teacher;
delete from Course;
delete from Invoice;
delete from Registration;
delete from Payment;

-- Insertar datos en la tabla Teacher
INSERT INTO Teacher (teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email)
VALUES (1, 'Juan', 'Perez Garcia', '123456789', 'juan.perez@gmail.com'),
       (2, 'Maria', 'Lopez Fernandez', '987654321', 'maria.lopez@hotmail.com');

-- Insertar datos en la tabla Course
INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, course_start_period, course_end_period, total_places, available_places, course_fee, place, course_state, teacher_id)
VALUES (1, 'Programacion en Java', 'Aprende a programar en Java desde cero', 'Conocer los conceptos fundamentales de la programacion orientada a objetos y las caracteristicas del lenguaje Java', 40, '2023-05-15', '14:00:00', '2023-03-15', '2023-04-30', 20, 20, 100, 'Sala 101', 'Activo', 1),
       (2, 'Diseno grafico', 'Aprende a disenar graficos para la web', 'Conocer los principios del diseno grafico y las herramientas para la creacion de graficos', 20, '2023-04-30', '16:00:00', '2023-03-20', '2023-04-10', 15, 15, 50, 'Sala 102', 'Activo', 2),
       (3, 'Solo una plaza', 'Ejemplo de solo una plaza', 'probar el modelo de inscripcion', 2, '2023-04-30', '16:00:00', '2023-03-20', '2023-04-10', 1, 1, 100, 'Sala 103', 'Activo', 2);

-- Insertar datos en la tabla Invoice
INSERT INTO Invoice (invoice_id, invoice_number, invoice_date, invoice_quantity, invoice_state, teacher_id, course_id)
VALUES (1, 10001, '2023-03-25', 1, 'Pendiente', 1, 1),
       (2, 10002, '2023-04-01', 2, 'Pagada', 2, 2);

-- Insertar datos en la tabla Registration
INSERT INTO Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)
VALUES (1, 'Pedro', 'Martinez Lopez', '555555555', 'pedro.martinez@gmail.com', '2023-03-20', '10:00:00', 'Pendiente', 1),
       (2, 'Ana', 'Gonzalez Sanchez', '666666666', 'ana.gonzalez@hotmail.com', '2023-03-25', '16:00:00', 'Pagado', 2);

-- Insertar datos en la tabla Payment
INSERT INTO Payment (payment_id, amount, payment_date, payment_time, payment_type, invoice_id, reg_id)
VALUES (1, 100, '2023-03-30', '12:00:00', 'Tarjeta de crédito', 1, null),
       (2, 50, '2023-04-01', '18:00:00', 'Paypal', null, 2);
