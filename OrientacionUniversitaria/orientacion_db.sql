-- Script de Base de Datos para Orientación Universitaria Argentina
CREATE DATABASE IF NOT EXISTS orientacion_universitaria_bd;
USE orientacion_universitaria_bd;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    dni INT NOT NULL,
    interes_principal VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS facultades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    sigla VARCHAR(20) NOT NULL,
    universidad VARCHAR(150) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    sitio_web VARCHAR(255),
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS carreras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    duracion_anos INT NOT NULL,
    area_estudio VARCHAR(100) NOT NULL,
    modalidad VARCHAR(50) NOT NULL,
    descripcion TEXT,
    facultad_id INT,
    FOREIGN KEY (facultad_id) REFERENCES facultades(id) ON DELETE CASCADE
);

-- Datos de prueba iniciales
INSERT INTO facultades (nombre, sigla, universidad, ubicacion, sitio_web, descripcion) VALUES
('Facultad de Ciencias Exactas y Naturales', 'FCEN', 'Universidad de Buenos Aires (UBA)', 'Ciudad Autónoma de Buenos Aires', 'https://exactas.uba.ar', 'Excelencia en investigación de ciencias básicas, computación, matemática y biología.'),
('Facultad de Ingeniería', 'FIUNCUYO', 'Universidad Nacional de Cuyo (UNCUYO)', 'Mendoza, Argentina', 'https://fing.uncuyo.edu.ar', 'Formación de ingenieros líderes en tecnología, petróleo, civil, mecatrónica y ciencias de la computación.'),
('Facultad de Ciencias Exactas, Físicas y Naturales', 'FCEFN', 'Universidad Nacional de Córdoba (UNC)', 'Córdoba, Argentina', 'https://fcefyn.unc.edu.ar', 'Referente académico del centro del país con amplia oferta académica de grado y posgrado.');

INSERT INTO carreras (nombre, duracion_anos, area_estudio, modalidad, descripcion, facultad_id) VALUES
('Licenciatura en Ciencias de la Computación', 5, 'Tecnología e Informática', 'PRESENCIAL', 'Formación integral en algoritmos, inteligencia artificial, desarrollo de software y sistemas distribuidos.', 1),
('Ingeniería Industrial', 5, 'Ingeniería y Producción', 'PRESENCIAL', 'Gestión de procesos productivos, logística, economía y optimización de recursos empresariales.', 2),
('Ingeniería Mecatrónica', 5, 'Ingeniería y Tecnología', 'PRESENCIAL', 'Integración de ingeniería mecánica, electrónica y control computarizado.', 2),
('Licenciatura en Ciencias Biológicas', 5, 'Ciencias Naturales', 'PRESENCIAL', 'Estudio profundo de los seres vivos, genética, biotecnología y conservación ambiental.', 1),
('Ingeniería en Agrimensura', 5, 'Ingeniería y Geodesia', 'PRESENCIAL', 'Medición del territorio, delimitación parcelaria, sistemas de información geográfica y cartografía.', 3);
