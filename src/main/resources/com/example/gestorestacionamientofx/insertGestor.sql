-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gestorcochera
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gestorcochera` ;

-- -----------------------------------------------------
-- Schema gestorcochera
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestorcochera` DEFAULT CHARACTER SET utf8 ;
USE `gestorcochera` ;

-- -----------------------------------------------------
-- Table `tipo_contrato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_contrato` ;

CREATE TABLE IF NOT EXISTS `tipo_contrato` (
  `id_tipo_contrato` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_contrato` VARCHAR(45) NOT NULL,
  `descuento_servicio` INT(11) NOT NULL,
  `precio_base` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_tipo_contrato`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `servicio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `servicio` ;

CREATE TABLE IF NOT EXISTS `servicio` (
  `id_servicio` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion_servicio` VARCHAR(100) NOT NULL,
  `precio_base` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_servicio`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cochera`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cochera` ;

CREATE TABLE IF NOT EXISTS `cochera` (
  `id_cochera` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_entrada` DATETIME NULL DEFAULT NULL,
  `fecha_salida` DATETIME NULL DEFAULT NULL,
  `id_tipo_contrato` INT(11) NULL DEFAULT NULL,
  `estado` ENUM('DISPONIBLE', 'OCUPADO') NOT NULL DEFAULT 'DISPONIBLE',
  `codigo_cochera` INT(11) NOT NULL,
  `id_servicio` INT(11) NULL DEFAULT NULL,
  `patente` VARCHAR(45) NULL DEFAULT NULL,
  `descripcion` ENUM('Sedan', 'SUV', 'Pickup') NULL DEFAULT NULL,
  PRIMARY KEY (`id_cochera`),
  CONSTRAINT `fk_alquiler_tipo_contrato1`
    FOREIGN KEY (`id_tipo_contrato`)
    REFERENCES `tipo_contrato` (`id_tipo_contrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cochera_servicio1`
    FOREIGN KEY (`id_servicio`)
    REFERENCES `servicio` (`id_servicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facturacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facturacion` ;

CREATE TABLE IF NOT EXISTS `facturacion` (
  `id_facturacion` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_factura` DATETIME NOT NULL,
  `patente` VARCHAR(10) NOT NULL,
  `tipo_vehiculo` VARCHAR(20) NOT NULL,
  `codigo_cochera` INT(11) NOT NULL,
  `tipo_contrato` VARCHAR(20) NOT NULL,
  `fecha_ingreso` DATETIME NOT NULL,
  `fecha_egreso` DATETIME NOT NULL,
  `duracion` VARCHAR(30) NOT NULL,
  `servicio` VARCHAR(50) NOT NULL,
  `descuento` INT(11) NOT NULL,
  `monto_total` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_facturacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


INSERT INTO tipo_contrato (id_tipo_contrato, descripcion_contrato, descuento_servicio, precio_base) VALUES
(1,'Por Hora', 0, 2500.00),
(2,'Por Día', 5, 12500.00),
(3,'Por Mes', 10, 125000.00);

INSERT INTO servicio (id_servicio,descripcion_servicio, precio_base) VALUES
(1,'Ninguno', 0.00),
(2,'Lavado', 5000.00),
(3,'Lavado y Aspirado', 6000.00),
(4,'Lavado, Aspirado y Encerado', 7500.00);

INSERT INTO cochera (
    id_cochera, fecha_entrada, fecha_salida, id_tipo_contrato,
    estado, codigo_cochera, id_servicio, patente, descripcion
) VALUES
(1, '2025-07-01 08:15:00', NULL, 1, 'OCUPADO', 1, 1, 'ABC123', 'Sedan'),
(2, '2025-07-01 09:45:00', NULL, 2, 'OCUPADO', 2, 2, 'XYZ456', 'SUV'),
(3, '2025-07-01 10:30:00', NULL, 3, 'OCUPADO', 3, 3, 'JKL789', 'Pickup'),
(4, '2025-07-02 07:20:00', NULL, 1, 'OCUPADO', 4, 1, 'LMN321', 'Sedan'),
(5, '2025-07-02 11:10:00', NULL, 2, 'OCUPADO', 5, 0, 'QWE654', 'SUV'),
(6, '2025-07-02 13:05:00', NULL, 3, 'OCUPADO', 6, 2, 'RTY987', 'Pickup'),
(7, '2025-07-02 15:45:00', NULL, 1, 'OCUPADO', 7, 3, 'UIO159', 'Sedan'),
(8, '2025-07-02 18:20:00', NULL, 2, 'OCUPADO', 8, 1, 'PAS753', 'SUV'),
(9, '2025-07-02 19:50:00', NULL, 3, 'OCUPADO', 9, 2, 'GFD852', 'Pickup'),
(10, '2025-07-03 07:15:00', NULL, 1, 'OCUPADO', 10, 0, 'HJK963', 'SUV'),
(11, '2025-07-03 08:45:00', NULL, 2, 'OCUPADO', 11, 1, 'MNB741', 'Sedan'),
(12, '2025-07-03 09:30:00', NULL, 3, 'OCUPADO', 12, 2, 'ZXC258', 'Pickup'),
(13, '2025-07-03 10:55:00', NULL, 1, 'OCUPADO', 13, 3, 'VBN369', 'Sedan'),
(14, '2025-07-03 11:40:00', NULL, 2, 'OCUPADO', 14, 1, 'PLM147', 'SUV'),
(15, '2025-07-03 12:25:00', NULL, 3, 'OCUPADO', 15, 2, 'YHN951', 'Pickup'),
(16, NULL, NULL, NULL, 'DISPONIBLE', 16, NULL, NULL, NULL),
(17, NULL, NULL, NULL, 'DISPONIBLE', 17, NULL, NULL, NULL),
(18, NULL, NULL, NULL, 'DISPONIBLE', 18, NULL, NULL, NULL),
(19, NULL, NULL, NULL, 'DISPONIBLE', 19, NULL, NULL, NULL),
(20, NULL, NULL, NULL, 'DISPONIBLE', 20, NULL, NULL, NULL),
(21, NULL, NULL, NULL, 'DISPONIBLE', 21, NULL, NULL, NULL),
(22, NULL, NULL, NULL, 'DISPONIBLE', 22, NULL, NULL, NULL),
(23, NULL, NULL, NULL, 'DISPONIBLE', 23, NULL, NULL, NULL),
(24, NULL, NULL, NULL, 'DISPONIBLE', 24, NULL, NULL, NULL),
(25, NULL, NULL, NULL, 'DISPONIBLE', 25, NULL, NULL, NULL),
(26, NULL, NULL, NULL, 'DISPONIBLE', 26, NULL, NULL, NULL),
(27, NULL, NULL, NULL, 'DISPONIBLE', 27, NULL, NULL, NULL),
(28, NULL, NULL, NULL, 'DISPONIBLE', 28, NULL, NULL, NULL),
(29, NULL, NULL, NULL, 'DISPONIBLE', 29, NULL, NULL, NULL),
(30, NULL, NULL, NULL, 'DISPONIBLE', 30, NULL, NULL, NULL);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
