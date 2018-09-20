--  -------------------------------------------------- 
--  Scrip bd - el cafetal
--   -------------------------------------------------

-- Create user
CREATE USER 'elcafetal'@'localhost' IDENTIFIED BY 'elcafetal';
GRANT ALL PRIVILEGES ON * . * TO 'elcafetal'@'localhost';

-- Create data base
CREATE DATABASE  IF NOT EXISTS `elcafetalbd`;
USE `elcafetalbd`;

--  Drop table
DROP TABLE IF EXISTS PRODUCT;

--  Create table 
CREATE TABLE PRODUCT
(
	id INT NOT NULL AUTO_INCREMENT COMMENT 'Identificacion del producto.',
	product_name VARCHAR(50) NOT NULL COMMENT 'Nombre del producto.',
	unit_value DECIMAL(7,2) NOT NULL COMMENT 'Valor unitario del producto.',
	is_perishable BOOLEAN NOT NULL COMMENT 'Indica si el producto es o no perecedero. Ejemplo: TRUE = 1 (es perecedero), FALSE = 0 (no es perecedero).',
	date_purchase DATE NOT NULL COMMENT 'Fecha de compra del producto.',
	PRIMARY KEY (id)
)  COMMENT='Contiene informacion basica de los productos del cafetal.';

-- Initial dates
INSERT INTO `PRODUCT` VALUES 
(1,'Croissant',1500, TRUE, '2018-09-02'),
(2,'Pastel de pollo',4000, TRUE, '2018-09-02'),
(3,'Empanada',3500, TRUE, '2018-09-02'),
(4,'Coca cola 350 mlt',1500, FALSE, '2018-09-02'),
(5,'Malta 350 mlt',2000, TRUE, '2018-09-02');

COMMIT;
