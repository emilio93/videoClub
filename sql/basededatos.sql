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
  PRIMARY KEY (`idPrestamo`),
  KEY `fkClientes` (`idCliente`),
  KEY `fkPeliculas` (`idPelicula`),
  CONSTRAINT `fkClientes` FOREIGN KEY (`idCliente`) REFERENCES clientes(`idCliente`) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT `fkPeliculas` FOREIGN KEY (`idPelicula`) REFERENCES peliculas(`idPelicula`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--  /***************************************************************************
--   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
--   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
--   * PROCEDIMIENTOS ALMACENADOS  * * * * * * * * * * * * * * * * * * * * * * *
--   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
--   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
--   **************************************************************************/
DELIMITER ;;
CREATE PROCEDURE `addCliente`(
	IN pcedula INT,
	IN pnombre VARCHAR(255),
	IN papellido1 VARCHAR(255),
    IN papellido2 VARCHAR(255),
    IN ptelefono INT,
    IN pemail VARCHAR(255),
    IN pdireccion LONGTEXT
)
BEGIN
	INSERT INTO clientes(cedula, nombre, apellido1, apellido2, telefono, email, direccion)
	values (pcedula, pnombre, papellido1, papellido2, ptelefono, pemail, pdireccion);
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `addConfiguracion`(
    IN pnombre VARCHAR(255),
    IN pvalor VARCHAR(255),
    IN ptipo VARCHAR(255),
    IN pdescripcion VARCHAR(255)
)
    BEGIN
    INSERT INTO configuracion(nombre, valor, tipo, descripcion)
    VALUES (pnombre, pvalor, ptipo, pdescripcion);
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `addPelicula`(
	IN ptitulo VARCHAR(255),
	IN pdireccion VARCHAR(255),
    IN pproduccion VARCHAR(255),
    IN pano INT,
    IN pgenero VARCHAR(255),
    IN pduracion INT,
    IN psinopsis LONGTEXT,
    IN pcantidad INT
)
BEGIN
	INSERT INTO peliculas(titulo, direccion, produccion, ano, genero, duracion, sinopsis, cantidad)
	VALUES (ptitulo, pdireccion, pproduccion, pano, pgenero, pduracion, psinopsis, pcantidad);
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `addPrestamo`(
	IN pidCliente INT,
    IN pidPelicula INT,
    IN psalida VARCHAR(255),
    IN pdevolucion VARCHAR(255)
)
BEGIN
	INSERT INTO prestamos(idCliente, idPelicula, salida, devolucion)
    VALUES (pidCliente, pidPelicula, psalida, pdevolucion);
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `contarPrestamosCliente`(
    IN pidCliente INT
)
BEGIN
	SELECT COUNT(*) AS prestamos
    FROM prestamos
    INNER JOIN clientes ON prestamos.idCliente = clientes.idCliente
    WHERE prestamos.devuelta = 0;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `deleteCliente`(
    IN pidCliente INT
)
BEGIN
	DELETE FROM clientes
    WHERE idCliente = pidCliente;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `deletePelicula`(
    IN pidPelicula INT
)
BEGIN
	DELETE FROM clientes
    WHERE idPelicula = pidPelicula;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `esMoroso`(
    IN pidCliente INT
)
BEGIN
	-- Indica la cantidad de películas en mora de un cliente.
	DECLARE moras INT DEFAULT 0;
	SELECT COUNT(*)
    INTO moras
    FROM prestamos
    INNER JOIN clientes ON prestamos.idCliente = clientes.idCliente
    WHERE clientes.idCliente = pidCLiente AND NOW() > prestamos.devolucion;
	SELECT moras;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `finalizarPrestamo`(
    IN pidPrestamo INT
)
BEGIN
	UPDATE prestamos
    SET devuelta = 1
    WHERE idPrestamo = pidPrestamo;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getCliente`(
    IN pidCliente INT
)
BEGIN
	SELECT * FROM clientes WHERE idCliente = pidCliente LIMIT 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getClientes`(
    IN cant INT,
    IN pag INT
)
BEGIN
	-- Si una página contiene cant cantidad de registros de clientes,
    -- se obtiene la pag-esima página con clientes.
    --
    -- Números negativos seran convertidos a positivos.
    --
    -- Solo se permiten los casos que cant y pag sean
    --     cero, o ambos distintos de 0.
    --
    -- Si cant y pag son 0, selecciona todos los clientes.
    DECLARE minLim INT DEFAULT 0;
    SET cant = ABS(cant);
    SET pag = ABS(pag);

    IF cant = 0 AND pag = 0 THEN
		SELECT * FROM clientes;
	END IF;

    IF cant != 0 AND pag != 0 THEN
		SET minLim = cant*(pag-1);
		SELECT * FROM clientes LIMIT cant OFFSET minLim ;
	END IF;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getClientesMorosos`()
BEGIN
	-- Obtiene los clientes con mora en algún préstamo.
	SELECT * FROM clientes
    INNER JOIN prestamos ON cliente.idCliente = prestamos.idCliente
    WHERE prestamos.devolucion > NOW();
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getConfiguracion`(
    IN pnombre VARCHAR(255)
)
BEGIN
	-- Obtiene el valor, tipo y descripcion para la configuración solicitada.
	SELECT valor, tipo, descripcion FROM configuracion
    WHERE nommbre = pnombre LIMIT 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getIdFromCedula`(
    IN pcedula INT
)
BEGIN
	SELECT idCliente FROM clientes WHERE cedula = pcedula LIMIT 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getNombrePeliclas`()
BEGIN
	SELECT nombre FROM peliculas;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getPelicula`(
    IN pidPelicula INT
)
BEGIN
	SELECT * FROM peliculas WHERE idPelicula=pidPelicula LIMIT 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getPeliculas`(
    IN cant INT,
    IN pag INT
)
BEGIN
	-- Si una página contiene cant cantidad de registros de peliculas,
    -- se obtiene la pag-esima página con peliculas.
    --
    -- Números negativos seran convertidos a positivos.
    --
    -- Solo se permiten los casos que cant y pag sean
    --     cero, o ambos distintos de 0.
    --
    -- Si cant y pag son 0, selecciona todas las peliculas.
    DECLARE minLim INT DEFAULT 0;
    SET cant = ABS(cant);
    SET pag = ABS(pag);

    IF cant = 0 AND pag = 0 THEN
		SELECT * FROM peliculas;
	END IF;

    IF cant != 0 AND pag != 0 THEN
		SET minLim = cant*(pag-1);
		SELECT * FROM peliculas LIMIT cant OFFSET minLim;
	END IF;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getPrestamosCliente`(
    IN pidCliente INT,
    pdevuelta TINYINT
)
BEGIN
	-- Obtiene todos los prestamos de un cliente.
    -- El parametro pdevuelta indica si se seleccionan los
    -- prestamos devueltos, los activos o bien todos según
    -- lo siguiente:
    --     0: activos
    --     1: inactivos
    --     2: todos

    IF pdevuelta = 0 THEN
		SELECT
			prestamos.salida, prestamos.devolucion, prestamos.devuelta,
			clientes.cedula, clientes.nombre, clientes.apellido1, clientes.apellido2, clientes.telefono, clientes.email,
			peliculas.titulo, peliculas.direccion, peliculas.produccion, peliculas.ano
        FROM prestamos
		INNER JOIN clientes ON clientes.idCliente = prestamos.idCliente
        RIGHT OUTER JOIN peliculas ON peliculas.idPelicula = prestamos.idPelicula
		WHERE prestamos.idCliente = pidCliente AND prestamos.devuelta = 0;
	END IF;

    IF pdevuelta = 1 THEN
		SELECT
			prestamos.salida, prestamos.devolucion, prestamos.devuelta,
			clientes.cedula, clientes.nombre, clientes.apellido1, clientes.apellido2, clientes.telefono, clientes.email,
			peliculas.titulo, peliculas.direccion, peliculas.produccion, peliculas.ano
        FROM prestamos
		INNER JOIN clientes ON clientes.idCliente = prestamos.idCliente
        RIGHT OUTER JOIN peliculas ON peliculas.idPelicula = prestamos.idPelicula
		WHERE prestamos.idCliente = pidCliente AND prestamos.devuelta = 1;
	END IF;

    IF pdevuelta = 2 THEN
		SELECT
			prestamos.salida, prestamos.devolucion, prestamos.devuelta,
			clientes.cedula, clientes.nombre, clientes.apellido1, clientes.apellido2, clientes.telefono, clientes.email,
			peliculas.titulo, peliculas.direccion, peliculas.produccion, peliculas.ano
        FROM prestamos
		INNER JOIN clientes ON clientes.idCliente = prestamos.idCliente
        RIGHT OUTER JOIN peliculas ON peliculas.idPelicula = prestamos.idPelicula
		WHERE prestamos.idCliente = pidCliente;
	END IF;

END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getPrestamosClientePelicula`(
    IN pidCliente INT,
    pidPelicula INT
)
BEGIN
	-- Obtiene los valores relevantes de prestamo, cliente y pelicula
    -- al mostrar un prestamo según la pelicula y cliente solicitados.
    -- Obtiene únicamente los préstamos activos.
	SELECT
		prestamos.salida, prestamos.devolucion,
        cliente.cedula, cliente.nombre, cliente.apellido1, cliente.apellido2, cliente.telefono, cliente.email,
        pelicula.titulo, pelicula.direccion, pelicula.produccion, pelicula.ano
    FROM prestamos
    INNER JOIN clientes ON clientes.idCliente = prestamos.idCliente
    INNER JOIN peliculas ON peliculas.idPelicula = prestamos.idPelicula
    WHERE
		peliculas.idPelicula = pidPelicula AND
        prestamos.idCliente = pidCliente AND
        prestamos.devuelto = 1
	ORDER BY salida DESC;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `getPrestamosPelicula`(
    IN pidPelicula INT
)
BEGIN
	SELECT * FROM peliculas
	INNER JOIN prestamos ON peliculas.idPelicula = prestamos.idPelicula
    WHERE prestamos.idPelicula = pidPelicula AND prestamo.devuelta = 1;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `reactivarPrestamo`(
    IN pidPrestamo INT
)
BEGIN
	UPDATE prestamos
    SET devuelta = 0
    WHERE idPrestamo = pidPrestamo;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `updateCliente`(
	IN pidCliente INT,
    IN pcedula INT,
    IN pnombre VARCHAR(255),
    IN papellido1 VARCHAR(255),
    IN papellido2 VARCHAR(255),
    IN ptelefono INT,
    IN pemail VARCHAR(255),
    IN pdireccion LONGTEXT
)
BEGIN
	UPDATE clientes
	SET
		cedula = pcedula,
        nombre = pnombre,
        apellido1 = papellido1,
        apellido2 = papellido2,
        telefono = ptelefono,
        email = pemail,
        direccion = pdireccion
	WHERE idCliente = pidCliente;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `updateConfiguracion`(
	IN pnombre VARCHAR(255),
    IN pvalor VARCHAR(255),
    IN ptipo VARCHAR(255),
    IN pdescripcion VARCHAR(255)
)
BEGIN
	UPDATE configuracion
	SET
        valor = pvalor,
        tipo = ptipo,
        descripcion = pdescripcion
	WHERE nombre = pnombre;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE PROCEDURE `updatePelicula`(
    IN pidPelicula INT,
    IN ptitulo INT,
    IN pdireccion VARCHAR(255),
    IN pproduccion VARCHAR(255),
    IN pano INT,
    IN pgenero VARCHAR(255),
    IN pduracion INT,
    IN psinopsis LONGTEXT,
    IN pcantidad INT
)
BEGIN
	UPDATE peliculas
	SET
		idPelicula = pidPelicula,
		titulo = ptitulo,
		direccion = pdireccion,
		produccion = pproduccion,
		ano = pano,
		genero = pgenero,
		duracion = pduracion,
		sinopsis = psinopsis,
		cantidad = pcantidad
	WHERE idPelicula = pidPelicula;
END ;;
DELIMITER ;
