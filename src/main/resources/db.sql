create database student_manager;
use student_manager;

create table clazz (
                       id int primary key auto_increment,
                       name varchar(100)
);

create table student (
                         id int primary key auto_increment,
                         name varchar(100),
                         score double
);
insert into clazz(name)
values  ('12A'),
        ('12B'),
        ('12C');

insert into student(name, score)
values ('Nguyễn Văn A', 9.2),
       ('Nguyễn Văn B', 8.4);