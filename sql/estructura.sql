-- /**
--  * Emilio Rojas 2016-02-18
--  */
CREATE DATABASE  IF NOT EXISTS `VideoClub` DEFAULT CHARACTER SET latin1;
USE `VideoClub`;
SET TIME_ZONE='-06:00';

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` int(11) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido1` VARCHAR(255) NOT NULL,
  `apellido2` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `direccion` longtext NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `uqCedula` (`cedula`),
  UNIQUE KEY `uqEmail` (`email`),
  UNIQUE KEY `uqTelefono` (`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `configuracion`;
CREATE TABLE `configuracion` (
  `idPar` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `tipo` VARCHAR(255) NOT NULL,
  `valor` VARCHAR(255) NOT NULL,
      `descripcion` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`idPar`),
  UNIQUE KEY `uqNombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO
    configuracion(`nombre`, `valor`, `tipo`, `descripcion`)
VALUES
    ('LIMITE_PELICULAS','1000','int', 'Máximo de películas en el sistema.'),
    ('COSTO_RENTA','1000','int', 'Costo de la renta de una película.'),
    ('MULTA','300','int', 'El costo de la multa por día extra sin devolver la película.'),
    ('MAX_PELICULAS_CLIENTE','5','int', 'Máximo películas que puede tener en renta un cliente.'),
    ('MAX_DIAS_RENTA','8','int', 'La cantidad máxima de dias antes de comenzar a cobrarse multa.');

DROP TABLE IF EXISTS `peliculas`;
CREATE TABLE `peliculas` (
  `idPelicula` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `produccion` VARCHAR(255) NOT NULL,
  `ano` int(11) NOT NULL,
  `genero` VARCHAR(255) NOT NULL,
  `duracion` int(11) NOT NULL,
  `sinopsis` longtext NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idPelicula`),
  UNIQUE KEY `uqTitulo` (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `prestamos`;
CREATE TABLE `prestamos` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `salida` date NOT NULL,
  `devolucion` date NOT NULL,
  `devuelta` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE prestamos
ADD CONSTRAINT fkClientes
FOREIGN KEY fkClientes(idCliente)
REFERENCES clientes(idCliente)
ON UPDATE CASCADE
ON DELETE RESTRICT;

ALTER TABLE prestamos
ADD CONSTRAINT fkPeliculas
FOREIGN KEY fkPeliculas(idPelicula)
REFERENCES peliculas(idPelicula)
ON UPDATE CASCADE
ON DELETE RESTRICT;
