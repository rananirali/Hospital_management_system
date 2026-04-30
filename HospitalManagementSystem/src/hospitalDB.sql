CREATE DATABASE hospitalDB;
USE hospitalDB;
show tables;
select * from PatientsData;
select * from DoctorsData;
select * from AdminsData;
insert into AdminsData 
(name, email, contact, password)
values
('Nirali', 'n@gmail.com', '9687354650', '150506'),
('Twinkal', 't@gmail.com', '1234567890', '201205');
CREATE DATABASE test;
USE test;
create table Employee(empid varchar(50), ename varchar(50), email varchar(50), age int);
select * from Employee;

drop table Employee;

delete from DoctorsData where doctor_id = 16;

delete from PatientsData where patient_id = 8;
 