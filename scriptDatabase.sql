-- MySQL Script generated by MySQL Workbench
-- Wed Sep  8 16:01:14 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nmtlaptop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nmtlaptop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nmtlaptop` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `nmtlaptop` ;

-- -----------------------------------------------------
-- Table `nmtlaptop`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`accounts` (
  `username` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `fullname` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(40) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `address` (`username` ASC) VISIBLE,
  CONSTRAINT `address_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `nmtlaptop`.`accounts` (`username`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`role` (
  `id` VARCHAR(20) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) CHARACTER SET 'utf8' NULL  DEFAULT NULL,
  `roleid` VARCHAR(20) NULL DEFAULT NULL ,
  Unique(username,roleid),
  PRIMARY KEY (`id`),
  CONSTRAINT `authority_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `nmtlaptop`.`accounts` (`username`),
  CONSTRAINT `authority_ibfk_2`
    FOREIGN KEY (`roleid`)
    REFERENCES `nmtlaptop`.`role` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`brand` (
  `id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `Price` DOUBLE NULL DEFAULT NULL,
  `CreateDate` DATE NULL DEFAULT NULL,
  `Available` BIT(1) NULL DEFAULT NULL,
  `Remaining` INT NULL DEFAULT NULL,
  `BrandId` VARCHAR(10) NULL DEFAULT NULL,
  `image` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `BrandId` (`BrandId` ASC) VISIBLE,
  CONSTRAINT `products_ibfk_1`
    FOREIGN KEY (`BrandId`)
    REFERENCES `nmtlaptop`.`brand` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`orders` (
  `Id` VARCHAR(10) NOT NULL,
  `Username` VARCHAR(40) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `CreateDate` DATE NULL DEFAULT NULL,
  `Address` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Username` (`Username` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`Username`)
    REFERENCES `nmtlaptop`.`accounts` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `nmtlaptop`.`orderdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nmtlaptop`.`orderdetails` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `OrderId` VARCHAR(10) NULL DEFAULT NULL,
  `ProductId` INT NULL DEFAULT NULL,
  `Price` DOUBLE NULL DEFAULT NULL,
  `Quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `ProductId` (`ProductId` ASC) VISIBLE,
  INDEX `OrderId` (`OrderId` ASC) VISIBLE,
  CONSTRAINT `orderdetails_ibfk_1`
    FOREIGN KEY (`ProductId`)
    REFERENCES `nmtlaptop`.`products` (`id`),
  CONSTRAINT `orderdetails_ibfk_2`
    FOREIGN KEY (`OrderId`)
    REFERENCES `nmtlaptop`.`orders` (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

USE `nmtlaptop` ;

-- -----------------------------------------------------
-- procedure Find_Product_Top_Seller
-- -----------------------------------------------------

DELIMITER $$
USE `nmtlaptop`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Find_Product_Top_Seller`()
begin
	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	Select  Products.Id,Products.Name,Products.image,Products.Price from Products join OrderDetails on Products.Id=OrderDetails.ProductId group by Products.Id,Products.Name,Products.image,Products.Price  
	having SUM(OrderDetails.Quantity) >2
	Limit 3;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure Find_Top_Seller
-- -----------------------------------------------------

DELIMITER $$
USE `nmtlaptop`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Find_Top_Seller`()
begin
	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	Select Products.Id,Products.Name,Products.image,Products.Price from Products join OrderDetails on Products.Id=OrderDetails.ProductId group by Products.Id,Products.Name,Products.image,Products.Price  
	having SUM(OrderDetails.Quantity) >2;
end$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET FOREIGN_KEY_CHECKS=0