create database todolist;
use todolist;

create table list_item (
    id int primary key not null auto_increment,
    title varchar(50),
    content text,
    state tinyint(1)
);