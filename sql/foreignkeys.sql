/**
 * Emilio Rojas
 */
-- ALTER TABLE prestamos
-- DROP FOREIGN KEY fkClientes;
--
-- ALTER TABLE prestamos DROP FOREIGN KEY fkClientes;

-- ALTER TABLE prestamos
-- DROP FOREIGN KEY fkPeliculas;
--
-- ALTER TABLE prestamos DROP FOREIGN KEY fkPeliculas;

-- ALTER TABLE prestamos
-- ADD CONSTRAINT fkClientes
-- FOREIGN KEY fkClientes(idCliente)
-- REFERENCES clientes(idCliente)
-- ON UPDATE CASCADE
-- ON DELETE RESTRICT;
--
-- ALTER TABLE prestamos ADD CONSTRAINT fkClientes FOREIGN KEY fkClientes(idCliente) REFERENCES clientes(idCliente) ON UPDATE CASCADE ON DELETE RESTRICT;

-- ALTER TABLE prestamos
-- ADD CONSTRAINT fkPeliculas
-- FOREIGN KEY fkPeliculas(idPelicula)
-- REFERENCES peliculas(idPelicula)
-- ON UPDATE CASCADE
-- ON DELETE RESTRICT;
--
-- ALTER TABLE prestamos ADD CONSTRAINT fkPeliculas FOREIGN KEY fkPeliculas(idPelicula) REFERENCES peliculas(idPelicula) ON UPDATE CASCADE ON DELETE RESTRICT;
