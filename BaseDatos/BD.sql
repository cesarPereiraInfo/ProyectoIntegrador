-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `farmacia` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `farmacia` ;

-- -----------------------------------------------------
-- Table `farmacia`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombre` VARCHAR(45) NULL COMMENT '',
  `apePaterno` VARCHAR(45) NULL COMMENT '',
  `apeMaterno` VARCHAR(45) NULL COMMENT '',
  `genero` INT NULL COMMENT '',
  `domicilio` VARCHAR(45) NULL COMMENT '',
  `codigoPostal` VARCHAR(5) NULL COMMENT '',
  `ciudad` VARCHAR(45) NULL COMMENT '',
  `estado` VARCHAR(30) NULL COMMENT '',
  `telefono` VARCHAR(10) NULL COMMENT '',
  `fechaNacimiento` DATE NULL COMMENT '',
  `foto` LONGTEXT NULL COMMENT '',
 
  PRIMARY KEY (`idPersona`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombre` VARCHAR(30) NULL COMMENT '',
  `contrasennia` VARCHAR(16) NULL COMMENT '',
  `rol` VARCHAR(30) NULL COMMENT '',
  PRIMARY KEY (`idUsuario`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`sucursal` (
  `idSucursal` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombre` VARCHAR(45) NULL COMMENT '',
  `titular` VARCHAR(45) NULL COMMENT '',
  `domicilio` VARCHAR(45) NULL COMMENT '',
  `ciudad` VARCHAR(45) NULL COMMENT '',
  `estado` VARCHAR(30) NULL COMMENT '',
  `codigoPostal` VARCHAR(10) NULL COMMENT '',
  `telefono` VARCHAR(45) NULL COMMENT '',
  `rfc` VARCHAR(13) NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `colonia` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`idSucursal`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`empleado` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `codigo` VARCHAR(8) NULL COMMENT '',
  `fechaIngreso` DATE NULL COMMENT '',
  `puesto` VARCHAR(45) NULL COMMENT '',
  `salario` FLOAT ZEROFILL NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `idPersona` INT NULL COMMENT '',
  `idUsuario` INT NULL COMMENT '',
  `idSucursal` INT NULL COMMENT '',
  PRIMARY KEY (`idEmpleado`)  COMMENT '',
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC)  COMMENT '',
  INDEX `fk_idPersona_empleado_idx` (`idPersona` ASC)  COMMENT '',
  INDEX `fk_idUsuario_empleado_idx` (`idUsuario` ASC)  COMMENT '',
  INDEX `fk_idSucursal_empleado_idx` (`idSucursal` ASC)  COMMENT '',
  CONSTRAINT `fk_idPersona_empleado`
    FOREIGN KEY (`idPersona`)
    REFERENCES `farmacia`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idUsuario_empleado`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `farmacia`.`usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idSucursal_empleado`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `farmacia`.`sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `correoElectronico` VARCHAR(45) NOT NULL COMMENT '',
  `fechaRegistro` DATE NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `idPersona` INT NULL COMMENT '',
  `idSucursal` INT NULL COMMENT '',
  PRIMARY KEY (`idCliente`)  COMMENT '',
  UNIQUE INDEX `correoElectronico_UNIQUE` (`correoElectronico` ASC)  COMMENT '',
  INDEX `fk_idpersona_cliente_idx` (`idPersona` ASC)  COMMENT '',
  CONSTRAINT `fk_idpersona_cliente`
    FOREIGN KEY (`idPersona`)
    REFERENCES `farmacia`.`persona` (`idPersona`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`venta` (
  `idVenta` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `fechaHora` DATETIME NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `idCliente` INT NULL COMMENT '',
  `idEmpleado` INT NULL COMMENT '',
  `idSucursal` INT NULL COMMENT '',
  PRIMARY KEY (`idVenta`)  COMMENT '',
  INDEX `fk_idEmpleado_venta_idx` (`idEmpleado` ASC)  COMMENT '',
  INDEX `fk_idCliente_venta_idx` (`idCliente` ASC)  COMMENT '',
  INDEX `fk_idSucursal_venta_idx` (`idSucursal` ASC)  COMMENT '',
  CONSTRAINT `fk_idEmpleado_venta`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `farmacia`.`empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idCliente_venta`
    FOREIGN KEY (`idCliente`)
    REFERENCES `farmacia`.`cliente` (`idCliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idSucursal_venta`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `farmacia`.`sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `fechaHoraPedido` DATETIME NULL COMMENT '',
  `estatus` INT NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `idEmpleado` INT NULL COMMENT '',
  `idSucursal` INT NULL COMMENT '',
  PRIMARY KEY (`idPedido`)  COMMENT '',
  INDEX `fk_idSucursal_pedido_idx` (`idSucursal` ASC)  COMMENT '',
  INDEX `fk_idEmpleado_pedido_idx` (`idEmpleado` ASC)  COMMENT '',
  CONSTRAINT `fk_idSucursal_pedido`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `farmacia`.`sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idEmpleado_pedido`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `farmacia`.`empleado` (`idEmpleado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`producto` (
  `codigo_barras` VARCHAR(15) NULL COMMENT '',
  `idProducto` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombreGeneral` VARCHAR(180) NULL COMMENT '',
  `nombreGenerico` VARCHAR(200) NULL COMMENT '',
  `formaFarmaceutica` VARCHAR(100) NULL COMMENT '',
  `concentracion` VARCHAR(200) NULL COMMENT '',
  `presentacion` VARCHAR(200) NULL COMMENT '',
  `precioUnidad` FLOAT NULL COMMENT '',
  `foto` LONGTEXT NULL COMMENT '',
  `rutaFoto` VARCHAR(254) NULL COMMENT '',
  `activo` INT NULL DEFAULT 1 COMMENT '',
  `unidadesEnvase` INT NULL COMMENT '',
  `principalIndicacion` VARCHAR(254) NULL COMMENT '',
  `unidadMedida` VARCHAR(25) NULL COMMENT '',
  `contraindicaciones` VARCHAR(254) NULL COMMENT '',
  PRIMARY KEY (`idProducto`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`detalleVenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`detalleVenta` (
  `idProducto` INT NULL COMMENT '',
  `idVenta` INT NULL COMMENT '',
  `cantidad` INT NULL COMMENT '',
  `precioVenta` FLOAT NULL COMMENT '',
  INDEX `fk_idProducto_dv_idx` (`idProducto` ASC)  COMMENT '',
  INDEX `fk_idVenta_dv_idx` (`idVenta` ASC)  COMMENT '',
  CONSTRAINT `fk_idProducto_dv`
    FOREIGN KEY (`idProducto`)
    REFERENCES `farmacia`.`producto` (`idProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idVenta_dv`
    FOREIGN KEY (`idVenta`)
    REFERENCES `farmacia`.`venta` (`idVenta`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`inventario` (
  `idInventario` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `idProducto` INT NULL COMMENT '',
  `idSucursal` INT NULL COMMENT '',
  `existencias` INT NULL COMMENT '',
  PRIMARY KEY (`idInventario`)  COMMENT '',
  INDEX `idProducto_idx` (`idProducto` ASC)  COMMENT '',
  INDEX `fk_idSucursal_inventario_idx` (`idSucursal` ASC)  COMMENT '',
  CONSTRAINT `fk_idProducto_inventario`
    FOREIGN KEY (`idProducto`)
    REFERENCES `farmacia`.`producto` (`idProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idSucursal_inventario`
    FOREIGN KEY (`idSucursal`)
    REFERENCES `farmacia`.`sucursal` (`idSucursal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`detallePedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`detallePedido` (
  `idPedido` INT NULL COMMENT '',
  `idProducto` INT NULL COMMENT '',
  `cantidad` INT NULL COMMENT '',
  `precioCompra` FLOAT NULL COMMENT '',
  INDEX `fk_idPedido_detalleP_idx` (`idPedido` ASC)  COMMENT '',
  INDEX `fk_idProducto_detalleP_idx` (`idProducto` ASC)  COMMENT '',
  CONSTRAINT `fk_idPedido_detalleP`
    FOREIGN KEY (`idPedido`)
    REFERENCES `farmacia`.`pedido` (`idPedido`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idProducto_detalleP`
    FOREIGN KEY (`idProducto`)
    REFERENCES `farmacia`.`producto` (`idProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
