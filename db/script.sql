CREATE DATABASE primerproyecto_db;


USE primerproyecto_db;


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL
);
SELECT * FROM usuarios;
