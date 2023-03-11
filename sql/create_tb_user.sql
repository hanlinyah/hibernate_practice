create database if not exists hibernate;
use hibernate;

drop table if exists tb_user;
create table tb_user(
    id int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    gender char(1),
    addr varchar(30)
);

insert into tb_user values (1,'name1','password1','A','add1');
insert into tb_user values (2,'name2','password2','B','add2');
insert into tb_user values (3,'name3','password3','C','add3');
insert into tb_user values (5,'name5','password5','E','add5');
delete from  tb_user where username='name5';
select * from tb_user;

