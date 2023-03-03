--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
--drop table Carreras;
--create table Carreras (id int primary key not null, inicio date not null, fin date not null, fecha date not null, descr varchar(32), check(inicio<=fin), check(fin<fecha));

drop table Payment;
drop table Registration;
drop table Invoice;
drop table Course;
drop table Teacher;

create table Teacher (teacher_id int primary key not null, teacher_name varchar(50) not null, teacher_surnames varchar(50) not null, teacher_phone varchar (9) not null, teacher_email varchar(100) not null, teacher_bank_account varchar(50) not null);
create table Course (course_id int primary key not null, course_name varchar(50) not null, description varchar (300), objectives varchar(200) not null, course_hours int not null, course_date date not null, course_start_period date not null, course_end_period date not null, total_places int not null, available_places int not null, course_fee int not null, place varchar(50) not null, state varchar(50) not null, check(course_start_period <= course_end_period), check (course_end_period < course_date));
create table Invoice (invoice_id int primary key not null, invoice_number int not null, invoice_date date not null, invoice_quantity int not null, invoice_state varchar(50) not null, teacher_id int, course_id int UNIQUE, foreign key(teacher_id) references Teacher(teacher_id), foreign key(course_id) references Course(course_id));
create table Registration (reg_id int primary key not null, reg_name varchar(50) not null, reg_surnames varchar(50) not null, reg_phone varchar(9) not null, reg_email varchar(100) not null, reg_bank_account varchar(50) not null, reg_date_time text not null ,reg_state varchar(50) not null, course_id int, foreign key(course_id) references Course(course_id));
create table Payment (payment_id int primary key not null, amount int not null, payment_date_time text not null, payment_type varchar(50) not null, invoice_id int, reg_id int, foreign key (invoice_id) references Invoice(invoice_id), foreign key (reg_id) references Registration(reg_id));