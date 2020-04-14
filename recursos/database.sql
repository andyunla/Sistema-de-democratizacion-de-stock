-- MySQL Script generated by MySQL Workbench
-- mar 14 abr 2020 17:05:51 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bd-sistema-de democratización-de-stock
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bd-sistema-de democratización-de-stock` ;

-- -----------------------------------------------------
-- Schema bd-sistema-de democratización-de-stock
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd-sistema-de democratización-de-stock` DEFAULT CHARACTER SET utf8 ;
USE `bd-sistema-de democratización-de-stock` ;

-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`persona` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`persona` (
  `idPersona` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `dni` INT NULL,
  `fechaNacimiento` DATE NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`empleado` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`empleado` (
  `idEmpleado` INT(11) NOT NULL,
  `legajo` INT NULL,
  `horarioDesde` TIME NULL,
  `horarioHasta` TIME NULL,
  `sueldoBasico` DOUBLE NULL,
  `idLocal` INT(11) NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  INDEX `fk_empleado_local1_idx` (`idLocal` ASC),
  CONSTRAINT `fk_empleado_persona1`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_local1`
    FOREIGN KEY (`idLocal`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`local`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`local` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`local` (
  `idLocal` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreLocal` VARCHAR(45) NULL,
  `latitud` DOUBLE NULL,
  `longitud` DOUBLE NULL,
  `direccion` DOUBLE NULL,
  `telefono` INT NULL,
  `gerente_idEmpleado` INT(11) NOT NULL,
  PRIMARY KEY (`idLocal`),
  INDEX `fk_local_empleado1_idx` (`gerente_idEmpleado` ASC),
  CONSTRAINT `fk_local_empleado1`
    FOREIGN KEY (`gerente_idEmpleado`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`cliente` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`cliente` (
  `idCliente` INT(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nroCliente` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `fk_cliente_persona`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`producto` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`producto` (
  `idProducto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `talle` INT NOT NULL,
  PRIMARY KEY (`idProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`pedidostock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`pedidostock` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`pedidostock` (
  `idPedidostock` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `aceptado` TINYINT(1) NOT NULL,
  `solicitante_idEmpleado` INT(11) NOT NULL,
  `oferente_idEmpleado` INT(11) NOT NULL,
  `idProducto` INT(11) NOT NULL,
  PRIMARY KEY (`idPedidostock`),
  INDEX `fk_pedidostock_empleado1_idx` (`solicitante_idEmpleado` ASC),
  INDEX `fk_pedidostock_empleado2_idx` (`oferente_idEmpleado` ASC),
  INDEX `fk_pedidostock_producto1_idx` (`idProducto` ASC),
  CONSTRAINT `fk_pedidostock_empleado1`
    FOREIGN KEY (`solicitante_idEmpleado`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidostock_empleado2`
    FOREIGN KEY (`oferente_idEmpleado`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedidostock_producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`chango`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`chango` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`chango` (
  `idChango` INT(11) NOT NULL,
  `idPedidoStock` INT(11) NOT NULL,
  `idLocal` INT(11) NOT NULL,
  PRIMARY KEY (`idChango`),
  INDEX `fk_chango_pedidostock1_idx` (`idPedidoStock` ASC),
  INDEX `fk_chango_local1_idx` (`idLocal` ASC),
  CONSTRAINT `fk_chango_pedidostock1`
    FOREIGN KEY (`idPedidoStock`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`pedidostock` (`idPedidostock`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chango_local1`
    FOREIGN KEY (`idLocal`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`factura` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`factura` (
  `idFactura` INT(11) NOT NULL AUTO_INCREMENT,
  `fechaFactura` DATE NOT NULL,
  `costeTotal` DOUBLE NOT NULL,
  `idLocal` INT(11) NOT NULL,
  `idEmpleado` INT(11) NOT NULL,
  `idCliente` INT(11) NOT NULL,
  `idChango` INT(11) NOT NULL,
  INDEX `fk_factura_local1_idx` (`idLocal` ASC),
  INDEX `fk_factura_cliente1_idx` (`idCliente` ASC),
  INDEX `fk_factura_empleado1_idx` (`idEmpleado` ASC),
  PRIMARY KEY (`idFactura`),
  INDEX `fk_factura_chango1_idx` (`idChango` ASC),
  CONSTRAINT `fk_factura_local1`
    FOREIGN KEY (`idLocal`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_empleado1`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_chango1`
    FOREIGN KEY (`idChango`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`chango` (`idChango`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`lote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`lote` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`lote` (
  `idLote` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidadInicial` INT NOT NULL,
  `cantidadActual` INT NOT NULL,
  `fechaIngreso` DATE NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT 1,
  `idProducto` INT(11) NOT NULL,
  `idLocal` INT(11) NOT NULL,
  PRIMARY KEY (`idLote`),
  INDEX `fk_lote_producto1_idx` (`idProducto` ASC),
  INDEX `fk_lote_local1_idx` (`idLocal` ASC),
  CONSTRAINT `fk_lote_producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lote_local1`
    FOREIGN KEY (`idLocal`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`local` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd-sistema-de democratización-de-stock`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd-sistema-de democratización-de-stock`.`item` ;

CREATE TABLE IF NOT EXISTS `bd-sistema-de democratización-de-stock`.`item` (
  `idItem` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `idChango` INT(11) NOT NULL,
  `idProducto` INT(11) NOT NULL,
  PRIMARY KEY (`idItem`),
  INDEX `fk_item_chango1_idx` (`idChango` ASC),
  INDEX `fk_item_producto1_idx` (`idProducto` ASC),
  CONSTRAINT `fk_item_chango1`
    FOREIGN KEY (`idChango`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`chango` (`idChango`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `bd-sistema-de democratización-de-stock`.`producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
