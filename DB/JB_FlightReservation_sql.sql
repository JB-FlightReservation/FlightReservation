-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jb_flight_reservation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jb_flight_reservation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jb_flight_reservation` DEFAULT CHARACTER SET utf8mb3 ;
USE `jb_flight_reservation` ;

-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`admin` (
  `admin_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `admin_id` VARCHAR(45) NOT NULL,
  `admin_pw` VARCHAR(45) NOT NULL,
  `admin_name` VARCHAR(45) NOT NULL,
  `admin_email` VARCHAR(45) NOT NULL,
  `admin_eno` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`admin_no`),
  UNIQUE INDEX `admin_eno_UNIQUE` (`admin_eno` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`airline_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`airline_info` (
  `al_no` INT UNSIGNED NOT NULL,
  `af_no` INT UNSIGNED NOT NULL,
  `al_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`al_no`),
  INDEX `af_no_idx` (`af_no` ASC) VISIBLE,
  CONSTRAINT `al_af_no`
    FOREIGN KEY (`af_no`)
    REFERENCES `jb_flight_reservation`.`airflight_info` (`af_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`airflight_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`airflight_info` (
  `af_no` INT UNSIGNED NOT NULL,
  `af_name` VARCHAR(45) NOT NULL,
  `al_no` INT UNSIGNED NOT NULL,
  `af_depart` VARCHAR(45) NOT NULL,
  `af_land` VARCHAR(45) NOT NULL,
  `af_economy` INT UNSIGNED NULL DEFAULT NULL,
  `af_business` INT UNSIGNED NULL DEFAULT NULL,
  `af_depart_ap` VARCHAR(45) NOT NULL,
  `af_land_ap` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`af_no`),
  INDEX `al_no_idx` (`al_no` ASC) VISIBLE,
  CONSTRAINT `af_al_no`
    FOREIGN KEY (`al_no`)
    REFERENCES `jb_flight_reservation`.`airline_info` (`al_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`airport_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`airport_info` (
  `ap_no` INT UNSIGNED NOT NULL,
  `af_no` INT UNSIGNED NOT NULL,
  `ap_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ap_no`),
  INDEX `af_no_idx` (`af_no` ASC) VISIBLE,
  CONSTRAINT `ap_af_no`
    FOREIGN KEY (`af_no`)
    REFERENCES `jb_flight_reservation`.`airflight_info` (`af_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`coupon` (
  `cp_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `admin_no` INT UNSIGNED NOT NULL,
  `cp_name` VARCHAR(45) NOT NULL,
  `cp_start` DATE NOT NULL,
  `cp_end` DATE NOT NULL,
  `cp_discount` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`cp_no`),
  INDEX `admin_no_idx` (`admin_no` ASC) VISIBLE,
  CONSTRAINT `cp_admin_no`
    FOREIGN KEY (`admin_no`)
    REFERENCES `jb_flight_reservation`.`admin` (`admin_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`owned_coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`owned_coupon` (
  `oc_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cp_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`oc_no`),
  INDEX `oc_cp_no_idx` (`cp_no` ASC) VISIBLE,
  CONSTRAINT `oc_cp_no`
    FOREIGN KEY (`cp_no`)
    REFERENCES `jb_flight_reservation`.`coupon` (`cp_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`customer` (
  `ct_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ct_id` VARCHAR(45) NOT NULL,
  `ct_pw` VARCHAR(45) NOT NULL,
  `ct_name` VARCHAR(45) NOT NULL,
  `ct_email` VARCHAR(45) NOT NULL,
  `ct_phone` VARCHAR(45) NOT NULL,
  `ct_birth` DATE NOT NULL,
  `ct_gender` CHAR(1) NOT NULL,
  `ct_create` DATE NOT NULL,
  `oc_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ct_no`),
  UNIQUE INDEX `customer_id_UNIQUE` (`ct_id` ASC) VISIBLE,
  UNIQUE INDEX `oc_no_UNIQUE` (`oc_no` ASC) VISIBLE,
  CONSTRAINT `ct_oc_no`
    FOREIGN KEY (`oc_no`)
    REFERENCES `jb_flight_reservation`.`owned_coupon` (`oc_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`inquiry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`inquiry` (
  `i_no` INT UNSIGNED NOT NULL,
  `ct_no` INT UNSIGNED NOT NULL,
  `i_name` VARCHAR(45) NOT NULL,
  `i_content` VARCHAR(45) NOT NULL,
  `i_is_secret` TINYINT UNSIGNED NOT NULL,
  `i_pw` VARCHAR(45) NOT NULL,
  `i_answer_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`i_no`),
  UNIQUE INDEX `i_answer_no_UNIQUE` (`i_answer_no` ASC) VISIBLE,
  INDEX `ct_no_idx` (`ct_no` ASC) VISIBLE,
  CONSTRAINT `i_ct_no`
    FOREIGN KEY (`ct_no`)
    REFERENCES `jb_flight_reservation`.`customer` (`ct_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`inquiry_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`inquiry_answer` (
  `ia_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `i_no` INT UNSIGNED NOT NULL,
  `admin_no` INT UNSIGNED NOT NULL,
  `ia_content` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ia_no`),
  INDEX `i_no_idx` (`i_no` ASC) VISIBLE,
  INDEX `admin_no_idx` (`admin_no` ASC) VISIBLE,
  CONSTRAINT `ia_admin_no`
    FOREIGN KEY (`admin_no`)
    REFERENCES `jb_flight_reservation`.`admin` (`admin_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `ia_i_no`
    FOREIGN KEY (`i_no`)
    REFERENCES `jb_flight_reservation`.`inquiry` (`i_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`passenger_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`passenger_info` (
  `pg_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pg_first_name` VARCHAR(45) NOT NULL,
  `pg_last_name` VARCHAR(45) NOT NULL,
  `pg_gender` TINYINT UNSIGNED NOT NULL,
  `pg_birth` VARCHAR(45) NOT NULL,
  `pg_country` VARCHAR(45) NOT NULL,
  `pg_baggage` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`pg_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`price` (
  `pr_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pg_no` INT UNSIGNED NOT NULL,
  `pr_airfare` INT UNSIGNED NOT NULL COMMENT '항공 운임비',
  `pr_fuel_surcharge` INT UNSIGNED NOT NULL COMMENT '유류할증료',
  `pr_airport_usage_fee` INT UNSIGNED NOT NULL COMMENT '공항시설 사용료',
  `pr_discount` INT UNSIGNED NOT NULL,
  `pr_total` INT UNSIGNED NOT NULL COMMENT 'total = airface + feul_surcharge + airport_usage_fee - discount\\\\n총액 = (항공 운임비) + (유류할증료) + (공항시설 사용료) - (할인)',
  PRIMARY KEY (`pr_no`),
  INDEX `pg_no_idx` (`pg_no` ASC) VISIBLE,
  CONSTRAINT `pr_pg`
    FOREIGN KEY (`pg_no`)
    REFERENCES `jb_flight_reservation`.`passenger_info` (`pg_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`reservation` (
  `rv_no` INT UNSIGNED NOT NULL,
  `ct_no` INT UNSIGNED NOT NULL,
  `rv_pet_accompanying` TINYINT(1) NOT NULL,
  `rv_phone` VARCHAR(45) NOT NULL,
  `rv_email` VARCHAR(45) NOT NULL,
  `rv_is_ticketed` TINYINT(1) NOT NULL,
  `rv_date` DATE NOT NULL,
  `rv_mature_adult` INT UNSIGNED NOT NULL,
  `rv_mature_teen` INT UNSIGNED NOT NULL,
  `rv_mature_baby` INT UNSIGNED NOT NULL,
  `rv_depart_ap` VARCHAR(45) NOT NULL,
  `rv_land_ap` VARCHAR(45) NOT NULL,
  `next_rv_no` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`rv_no`),
  INDEX `customer_no_idx` (`ct_no` ASC) VISIBLE,
  CONSTRAINT `rv_ct_no`
    FOREIGN KEY (`ct_no`)
    REFERENCES `jb_flight_reservation`.`customer` (`ct_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`reserve_airflight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`reserve_airflight` (
  `rv_af_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `af_no` INT UNSIGNED NOT NULL,
  `rv_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`rv_af_no`),
  INDEX `reservation_no_idx` (`rv_no` ASC) VISIBLE,
  INDEX `af_no_idx` (`af_no` ASC) VISIBLE,
  CONSTRAINT `rv_af_af_no`
    FOREIGN KEY (`af_no`)
    REFERENCES `jb_flight_reservation`.`airflight_info` (`af_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `rv_af_rv_no`
    FOREIGN KEY (`rv_no`)
    REFERENCES `jb_flight_reservation`.`reservation` (`rv_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `jb_flight_reservation`.`reserve_passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jb_flight_reservation`.`reserve_passenger` (
  `rv_no` INT UNSIGNED NOT NULL,
  `pg_no` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`rv_no`),
  UNIQUE INDEX `passenger_id_UNIQUE` (`pg_no` ASC) VISIBLE,
  CONSTRAINT `rv_pg_pg_no`
    FOREIGN KEY (`pg_no`)
    REFERENCES `jb_flight_reservation`.`passenger_info` (`pg_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `rv_pg_rv_no`
    FOREIGN KEY (`rv_no`)
    REFERENCES `jb_flight_reservation`.`reservation` (`rv_no`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
