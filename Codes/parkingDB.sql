create database parking;
show databases;
use parking;

create table parking_slots (slot_no varchar(10), available int, id integer primary key auto_increment);
show tables;

insert into parking_slots(slot_no,available) values ('P1', 1);
insert into parking_slots(slot_no,available) values ('P2', 1);
insert into parking_slots(slot_no,available) values ('P3', 1);
insert into parking_slots(slot_no,available) values ('P4', 1);
insert into parking_slots(slot_no,available) values ('P5', 1);
insert into parking_slots(slot_no,available) values ('P6', 1);
insert into parking_slots(slot_no,available) values ('P7', 1);
insert into parking_slots(slot_no,available) values ('P8', 1);
insert into parking_slots(slot_no,available) values ('P9', 1);
insert into parking_slots(slot_no,available) values ('P10', 1);
insert into parking_slots(slot_no,available) values ('P11', 1);
insert into parking_slots(slot_no,available) values ('P12', 1);
insert into parking_slots(slot_no,available) values ('P13', 1);
insert into parking_slots(slot_no,available) values ('P14', 1);
insert into parking_slots(slot_no,available) values ('P15', 1);


select * from parking_slots;
