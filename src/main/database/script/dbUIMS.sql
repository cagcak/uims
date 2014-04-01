SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `dbUIMS` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbUIMS` ;

-- -----------------------------------------------------
-- Table `dbUIMS`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`USERS` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`USERS` (
  `idnumber` VARCHAR(11) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  PRIMARY KEY (`idnumber`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`general_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`general_info` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`general_info` (
  `faculty` VARCHAR(45) NULL ,
  `department` VARCHAR(45) NULL ,
  `group_name` VARCHAR(45) NULL ,
  `supervisor` VARCHAR(45) NULL ,
  `education` VARCHAR(45) NULL ,
  `registration` VARCHAR(45) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_general_info_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`current_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`current_info` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`current_info` (
  `fullname` VARCHAR(45) NULL ,
  `current_year` VARCHAR(45) NULL ,
  `current_semester` VARCHAR(45) NULL ,
  `current_month` VARCHAR(45) NULL ,
  `current_exam` VARCHAR(45) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_current_info_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`accounting_status_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`accounting_status_info` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`accounting_status_info` (
  `registration` VARCHAR(45) NULL ,
  `midterm` VARCHAR(10) NULL ,
  `final` VARCHAR(10) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_accounting_status_info_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`trascript`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`trascript` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`trascript` (
  `id_transcript` INT NOT NULL AUTO_INCREMENT ,
  `subject_code` VARCHAR(10) NULL ,
  `subject_name` VARCHAR(45) NULL ,
  `semester` VARCHAR(7) NULL ,
  `year` VARCHAR(10) NULL ,
  `credits` DECIMAL(2) NULL ,
  `average` VARCHAR(3) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`id_transcript`) ,
  INDEX `fk_trascript_USERS1` (`USERS_idnumber` ASC) ,
  CONSTRAINT `fk_trascript_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`success_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`success_report` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`success_report` (
  `id_success` INT NOT NULL AUTO_INCREMENT ,
  `subject_name` VARCHAR(45) NULL ,
  `hours` DECIMAL(2) NULL ,
  `midterm` DECIMAL(3) NULL ,
  `final` VARCHAR(3) NULL ,
  `average` DECIMAL(3) NULL ,
  `attandance` DECIMAL(3) NULL ,
  `semester` VARCHAR(10) NULL ,
  `academic_year` VARCHAR(10) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`id_success`) ,
  INDEX `fk_success_report_USERS1` (`USERS_idnumber` ASC) ,
  CONSTRAINT `fk_success_report_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`information_diploma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`information_diploma` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`information_diploma` (
  `firstname` VARCHAR(45) NULL ,
  `lastname` VARCHAR(45) NULL ,
  `middlename` VARCHAR(45) NULL ,
  `fullname_ru` VARCHAR(45) NULL ,
  `current_address` VARCHAR(45) NULL ,
  `passport_no` VARCHAR(45) NULL ,
  `birthday` VARCHAR(10) NULL ,
  `phone_number` VARCHAR(45) NULL ,
  `thesis_project_en` VARCHAR(45) NULL ,
  `thesis_project_ru` VARCHAR(45) NULL ,
  `thesis_project_kg` VARCHAR(45) NULL ,
  `year_of_school_graduation` YEAR NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_information_diploma_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`registration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`registration` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`registration` (
  `id_registration` INT NOT NULL AUTO_INCREMENT ,
  `subject_code` VARCHAR(10) NULL ,
  `subject_name` VARCHAR(45) NULL ,
  `semester` VARCHAR(7) NULL ,
  `year` VARCHAR(10) NULL ,
  `hours` DECIMAL(2) NULL ,
  `credits` DECIMAL(2) NULL ,
  `registration_status` INT NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`id_registration`) ,
  INDEX `fk_registration_USERS1` (`USERS_idnumber` ASC) ,
  CONSTRAINT `fk_registration_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbUIMS`.`applications_forms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`applications_forms` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`applications_forms` (
  `reference_type` VARCHAR(45) NULL ,
  `language` VARCHAR(2) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_applications_forms_USERS1`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `dbUIMS`.`ROLES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`ROLES` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`ROLES` (
  `user_role` VARCHAR(5) NULL ,
  `USERS_idnumber` VARCHAR(11) NOT NULL ,
  INDEX `fk_ROLES_USERS1` (`USERS_idnumber` ASC) ,
  PRIMARY KEY (`USERS_idnumber`) ,
  CONSTRAINT `fk_ROLES_USERS10`
    FOREIGN KEY (`USERS_idnumber` )
    REFERENCES `dbUIMS`.`USERS` (`idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `dbUIMS`.`PERMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbUIMS`.`PERMS` ;

CREATE  TABLE IF NOT EXISTS `dbUIMS`.`PERMS` (
  `permission` VARCHAR(10) NOT NULL ,
  `ROLES_USERS_idnumber` VARCHAR(11) NOT NULL ,
  PRIMARY KEY (`ROLES_USERS_idnumber`) ,
  CONSTRAINT `fk_PERMS_ROLES1`
    FOREIGN KEY (`ROLES_USERS_idnumber` )
    REFERENCES `dbUIMS`.`ROLES` (`USERS_idnumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`USERS`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`USERS` (`idnumber`, `password`) VALUES ('08010101865', '00011011');
INSERT INTO `dbUIMS`.`USERS` (`idnumber`, `password`) VALUES ('09010102241', 'husenurfan');
INSERT INTO `dbUIMS`.`USERS` (`idnumber`, `password`) VALUES ('09010102234', '09010102234');
INSERT INTO `dbUIMS`.`USERS` (`idnumber`, `password`) VALUES ('00000000001', '00000000001');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`general_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`general_info` (`faculty`, `department`, `group_name`, `supervisor`, `education`, `registration`, `USERS_idnumber`) VALUES ('New Technologies', 'Computer Engineering', 'COM-09', 'Mustafa Simsek', 'active', 'Not Registered', '08010101865');
INSERT INTO `dbUIMS`.`general_info` (`faculty`, `department`, `group_name`, `supervisor`, `education`, `registration`, `USERS_idnumber`) VALUES ('New Technologies', 'Computer Engineering', 'COM-09', 'Mustafa Simsek', 'active', 'Not Registered', '09010102241');
INSERT INTO `dbUIMS`.`general_info` (`faculty`, `department`, `group_name`, `supervisor`, `education`, `registration`, `USERS_idnumber`) VALUES ('Economy and Administration', 'Management', 'MAN-09', 'Myrzagul Zakirova', 'active', 'Registered', '09010102234');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`current_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`current_info` (`fullname`, `current_year`, `current_semester`, `current_month`, `current_exam`, `USERS_idnumber`) VALUES ('Cagri Cakir', '2013-2014', 'spring', 'February', 'Midterm', '08010101865');
INSERT INTO `dbUIMS`.`current_info` (`fullname`, `current_year`, `current_semester`, `current_month`, `current_exam`, `USERS_idnumber`) VALUES ('Hakan Guner', '2013-2014', 'spring', 'February', 'Midterm', '09010102241');
INSERT INTO `dbUIMS`.`current_info` (`fullname`, `current_year`, `current_semester`, `current_month`, `current_exam`, `USERS_idnumber`) VALUES ('Remzi Ulker', '2013-2014', 'spring', 'February', 'Midterm', '09010102234');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`accounting_status_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`accounting_status_info` (`registration`, `midterm`, `final`, `USERS_idnumber`) VALUES ('released', 'OK', 'OK', '08010101865');
INSERT INTO `dbUIMS`.`accounting_status_info` (`registration`, `midterm`, `final`, `USERS_idnumber`) VALUES ('not released', 'OK', 'OK', '09010102241');
INSERT INTO `dbUIMS`.`accounting_status_info` (`registration`, `midterm`, `final`, `USERS_idnumber`) VALUES ('released', 'WBI', 'WBI', '09010102234');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`trascript`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`trascript` (`id_transcript`, `subject_code`, `subject_name`, `semester`, `year`, `credits`, `average`, `USERS_idnumber`) VALUES (1, 'COM 154', 'Diploma Project', 'spring', '2013-2014', 0, 'IP', '08010101865');
INSERT INTO `dbUIMS`.`trascript` (`id_transcript`, `subject_code`, `subject_name`, `semester`, `year`, `credits`, `average`, `USERS_idnumber`) VALUES (2, 'MAT 123', 'Discrete Structure', 'spring', '2013-2014', 0, 'IP', '09010102241');
INSERT INTO `dbUIMS`.`trascript` (`id_transcript`, `subject_code`, `subject_name`, `semester`, `year`, `credits`, `average`, `USERS_idnumber`) VALUES (NULL, 'COM 153', 'Web Tech', 'spring', '2012-2013', 2, '78', '08010101865');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`success_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`success_report` (`id_success`, `subject_name`, `hours`, `midterm`, `final`, `average`, `attandance`, `semester`, `academic_year`, `USERS_idnumber`) VALUES (1, 'Diploma Project', 0, 0, '0', 0, 0, 'spring', '2013-2014', '08010101865');
INSERT INTO `dbUIMS`.`success_report` (`id_success`, `subject_name`, `hours`, `midterm`, `final`, `average`, `attandance`, `semester`, `academic_year`, `USERS_idnumber`) VALUES (2, 'Discrete Structure', 0, 0, '0', 0, 0, 'spring', '2013-2014', '09010102241');
INSERT INTO `dbUIMS`.`success_report` (`id_success`, `subject_name`, `hours`, `midterm`, `final`, `average`, `attandance`, `semester`, `academic_year`, `USERS_idnumber`) VALUES (3, 'Information Technologies', 3, 0, '0', 0, 0, 'spring', '2013-2014', '09010102234');
INSERT INTO `dbUIMS`.`success_report` (`id_success`, `subject_name`, `hours`, `midterm`, `final`, `average`, `attandance`, `semester`, `academic_year`, `USERS_idnumber`) VALUES (4, 'Conflict Management', 3, 0, '0', 0, 0, 'spring', '2013-2014', '09010102234');
INSERT INTO `dbUIMS`.`success_report` (`id_success`, `subject_name`, `hours`, `midterm`, `final`, `average`, `attandance`, `semester`, `academic_year`, `USERS_idnumber`) VALUES (NULL, 'Control Theory', 4, 0, '0', 0, 12, 'spring', '2013-2014', '08010101865');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`information_diploma`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`information_diploma` (`firstname`, `lastname`, `middlename`, `fullname_ru`, `current_address`, `passport_no`, `birthday`, `phone_number`, `thesis_project_en`, `thesis_project_ru`, `thesis_project_kg`, `year_of_school_graduation`, `USERS_idnumber`) VALUES ('Cagri', 'Cakir', NULL, 'фыва', 'asd', 'TR-V 950134', NULL, '0700871990', 'UIMSMobile', 'выа', 'ыв', 2006, '08010101865');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`registration`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`registration` (`id_registration`, `subject_code`, `subject_name`, `semester`, `year`, `hours`, `credits`, `registration_status`, `USERS_idnumber`) VALUES (1, 'COM 402', 'Control Theory (Automation Theory)', 'spring', '4', 4, 4, 3, '08010101865');

COMMIT;

-- -----------------------------------------------------
-- Data for table `dbUIMS`.`PERMS`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbUIMS`;
INSERT INTO `dbUIMS`.`PERMS` (`permission`, `ROLES_USERS_idnumber`) VALUES ('user_perm', '08010101865');
INSERT INTO `dbUIMS`.`PERMS` (`permission`, `ROLES_USERS_idnumber`) VALUES ('admin_perm', '00000000001');
INSERT INTO `dbUIMS`.`PERMS` (`permission`, `ROLES_USERS_idnumber`) VALUES ('user_perm', '09010102241');

COMMIT;
