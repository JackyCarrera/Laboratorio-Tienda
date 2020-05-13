CREATE DATABASE Tienda;
USE Tienda;
CREATE TABLE usuarios
(
id int(45) primary key auto_increment,
usuario varchar(45),
password varchar(45),
nombre varchar(45),
email varchar(45),
last_sesion varchar(45),
idTipo int(11)
)Engine=InnoDB;

select * from usuarios; 
