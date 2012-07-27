SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `leaderboard` ;
CREATE SCHEMA IF NOT EXISTS `leaderboard` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `leaderboard` ;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_user` (
  `user_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `email_address` VARCHAR(45) NOT NULL ,
  `profile_handle` VARCHAR(45) NULL ,
  `first_name` VARCHAR(45) NULL ,
  `surname` VARCHAR(45) NULL ,
  `location` VARCHAR(45) NULL ,
  `handicap` VARCHAR(45) NULL ,
  PRIMARY KEY (`user_id`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `email_idx` ON `leaderboard`.`t_user` (`email_address` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `profile_handle_UNIQUE` ON `leaderboard`.`t_user` (`profile_handle` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `email_address_UNIQUE` ON `leaderboard`.`t_user` (`email_address` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `leaderboard`.`t_user` (`user_id` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_golf_course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_golf_course` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_golf_course` (
  `golf_course_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL DEFAULT NULL ,
  `par` VARCHAR(45) NULL DEFAULT NULL ,
  `location` VARCHAR(45) NULL DEFAULT NULL ,
  `hole_one_blue` INT NULL DEFAULT NULL ,
  `hole_one_white` INT NULL DEFAULT NULL ,
  `hole_one_green` INT NULL DEFAULT NULL ,
  `hole_one_red` INT NULL DEFAULT NULL ,
  `hole_one_par` INT NULL DEFAULT NULL ,
  `hole_one_index` INT NULL DEFAULT NULL ,
  `hole_two_blue` INT NULL DEFAULT NULL ,
  `hole_two_white` INT NULL DEFAULT NULL ,
  `hole_two_green` INT NULL DEFAULT NULL ,
  `hole_two_red` INT NULL DEFAULT NULL ,
  `hole_two_par` INT NULL DEFAULT NULL ,
  `hole_two_index` INT NULL DEFAULT NULL ,
  `hole_three_blue` INT NULL DEFAULT NULL ,
  `hole_three_white` INT NULL DEFAULT NULL ,
  `hole_three_green` INT NULL DEFAULT NULL ,
  `hole_three_red` INT NULL DEFAULT NULL ,
  `hole_three_par` INT NULL DEFAULT NULL ,
  `hole_three_index` INT NULL DEFAULT NULL ,
  `hole_four_blue` INT NULL DEFAULT NULL ,
  `hole_four_white` INT NULL DEFAULT NULL ,
  `hole_four_green` INT NULL DEFAULT NULL ,
  `hole_four_red` INT NULL DEFAULT NULL ,
  `hole_four_par` INT NULL DEFAULT NULL ,
  `hole_four_index` INT NULL DEFAULT NULL ,
  `hole_five_blue` INT NULL DEFAULT NULL ,
  `hole_five_white` INT NULL DEFAULT NULL ,
  `hole_five_green` INT NULL DEFAULT NULL ,
  `hole_five_red` INT NULL DEFAULT NULL ,
  `hole_five_par` INT NULL DEFAULT NULL ,
  `hole_five_index` INT NULL DEFAULT NULL ,
  `hole_six_blue` INT NULL DEFAULT NULL ,
  `hole_six_white` INT NULL DEFAULT NULL ,
  `hole_six_green` INT NULL DEFAULT NULL ,
  `hole_six_red` INT NULL DEFAULT NULL ,
  `hole_six_par` INT NULL DEFAULT NULL ,
  `hole_six_index` INT NULL DEFAULT NULL ,
  `hole_seven_blue` INT NULL DEFAULT NULL ,
  `hole_seven_white` INT NULL DEFAULT NULL ,
  `hole_seven_green` INT NULL DEFAULT NULL ,
  `hole_seven_red` INT NULL DEFAULT NULL ,
  `hole_seven_par` INT NULL DEFAULT NULL ,
  `hole_seven_index` INT NULL DEFAULT NULL ,
  `hole_eight_blue` INT NULL DEFAULT NULL ,
  `hole_eight_white` INT NULL DEFAULT NULL ,
  `hole_eight_green` INT NULL DEFAULT NULL ,
  `hole_eight_red` INT NULL DEFAULT NULL ,
  `hole_eight_par` INT NULL DEFAULT NULL ,
  `hole_eight_index` INT NULL DEFAULT NULL ,
  `hole_nine_blue` INT NULL DEFAULT NULL ,
  `hole_nine_white` INT NULL DEFAULT NULL ,
  `hole_nine_green` INT NULL DEFAULT NULL ,
  `hole_nine_red` INT NULL DEFAULT NULL ,
  `hole_nine_par` INT NULL DEFAULT NULL ,
  `hole_nine_index` INT NULL DEFAULT NULL ,
  `hole_ten_blue` INT NULL DEFAULT NULL ,
  `hole_ten_white` INT NULL DEFAULT NULL ,
  `hole_ten_green` INT NULL DEFAULT NULL ,
  `hole_ten_red` INT NULL DEFAULT NULL ,
  `hole_ten_par` INT NULL DEFAULT NULL ,
  `hole_ten_index` INT NULL DEFAULT NULL ,
  `hole_eleven_blue` INT NULL DEFAULT NULL ,
  `hole_eleven_white` INT NULL DEFAULT NULL ,
  `hole_eleven_green` INT NULL DEFAULT NULL ,
  `hole_eleven_red` INT NULL DEFAULT NULL ,
  `hole_eleven_par` INT NULL DEFAULT NULL ,
  `hole_eleven_index` INT NULL DEFAULT NULL ,
  `hole_twelve_blue` INT NULL DEFAULT NULL ,
  `hole_twelve_white` INT NULL DEFAULT NULL ,
  `hole_twelve_green` INT NULL DEFAULT NULL ,
  `hole_twelve_red` INT NULL DEFAULT NULL ,
  `hole_twelve_par` INT NULL DEFAULT NULL ,
  `hole_twelve_index` INT NULL DEFAULT NULL ,
  `hole_thirteen_blue` INT NULL DEFAULT NULL ,
  `hole_thirteen_white` INT NULL DEFAULT NULL ,
  `hole_thirteen_green` INT NULL DEFAULT NULL ,
  `hole_thirteen_red` INT NULL DEFAULT NULL ,
  `hole_thirteen_par` INT NULL DEFAULT NULL ,
  `hole_thirteen_index` INT NULL DEFAULT NULL ,
  `hole_fourteen_blue` INT NULL DEFAULT NULL ,
  `hole_fourteen_white` INT NULL DEFAULT NULL ,
  `hole_fourteen_green` INT NULL DEFAULT NULL ,
  `hole_fourteen_red` INT NULL DEFAULT NULL ,
  `hole_fourteen_par` INT NULL DEFAULT NULL ,
  `hole_fourteen_index` INT NULL DEFAULT NULL ,
  `hole_fifteen_blue` INT NULL DEFAULT NULL ,
  `hole_fifteen_white` INT NULL DEFAULT NULL ,
  `hole_fifteen_green` INT NULL DEFAULT NULL ,
  `hole_fifteen_red` INT NULL DEFAULT NULL ,
  `hole_fifteen_par` INT NULL DEFAULT NULL ,
  `hole_fifteen_index` INT NULL DEFAULT NULL ,
  `hole_sixteen_blue` INT NULL DEFAULT NULL ,
  `hole_sixteen_white` INT NULL DEFAULT NULL ,
  `hole_sixteen_green` INT NULL DEFAULT NULL ,
  `hole_sixteen_red` INT NULL DEFAULT NULL ,
  `hole_sixteen_par` INT NULL DEFAULT NULL ,
  `hole_sixteen_index` INT NULL DEFAULT NULL ,
  `hole_seventeen_blue` INT NULL DEFAULT NULL ,
  `hole_seventeen_white` INT NULL DEFAULT NULL ,
  `hole_seventeen_green` INT NULL DEFAULT NULL ,
  `hole_seventeen_red` INT NULL DEFAULT NULL ,
  `hole_seventeen_par` INT NULL DEFAULT NULL ,
  `hole_seventeen_index` INT NULL DEFAULT NULL ,
  `hole_eighteen_blue` INT NULL DEFAULT NULL ,
  `hole_eighteen_white` INT NULL DEFAULT NULL ,
  `hole_eighteen_green` INT NULL DEFAULT NULL ,
  `hole_eighteen_red` INT NULL DEFAULT NULL ,
  `hole_eighteen_par` INT NULL DEFAULT NULL ,
  `hole_eighteen_index` INT NULL DEFAULT NULL ,
  PRIMARY KEY (`golf_course_id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_competition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_competition` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_competition` (
  `competition_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `golf_course_id` INT ZEROFILL NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`competition_id`) ,
  CONSTRAINT `fk_t_competition_t_golf_course1`
    FOREIGN KEY (`golf_course_id` )
    REFERENCES `leaderboard`.`t_golf_course` (`golf_course_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_golf_course_id` ON `leaderboard`.`t_competition` (`golf_course_id` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `leaderboard`.`t_competition` (`name` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `competition_id_UNIQUE` ON `leaderboard`.`t_competition` (`competition_id` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_scorecard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_scorecard` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_scorecard` (
  `scorecard_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `golf_course_id` INT ZEROFILL NOT NULL ,
  `user_id` INT ZEROFILL NOT NULL ,
  `tee_played_off` VARCHAR(45) NULL ,
  `hole_one_shots` INT NULL ,
  `hole_two_shots` INT NULL ,
  `hole_three_shots` INT NULL ,
  `hole_four_shots` INT NULL ,
  `hole_five_shots` INT NULL ,
  `hole_six_shots` INT NULL ,
  `hole_seven_shots` INT NULL ,
  `hole_eight_shots` INT NULL ,
  `hole_nine_shots` INT NULL ,
  `hole_ten_shots` INT NULL ,
  `hole_eleven_shots` INT NULL ,
  `hole_twelve_shots` INT NULL ,
  `hole_thirteen_shots` INT NULL ,
  `hole_fourteen_shots` INT NULL ,
  `hole_fifteen_shots` INT NULL ,
  `hole_sixteen_shots` INT NULL ,
  `hole_seventeen_shots` INT NULL ,
  `hole_eighteen_shots` INT NULL ,
  `round_notes` VARCHAR(4000) NULL ,
  PRIMARY KEY (`scorecard_id`) ,
  CONSTRAINT `fk_t_scorecard_golf_course`
    FOREIGN KEY (`golf_course_id` )
    REFERENCES `leaderboard`.`t_golf_course` (`golf_course_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_scorecard_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `leaderboard`.`t_user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_golf_course_id` ON `leaderboard`.`t_scorecard` (`golf_course_id` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_user_id` ON `leaderboard`.`t_scorecard` (`user_id` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_golf_society`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_golf_society` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_golf_society` (
  `golf_society_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `golf_society_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`golf_society_id`) )
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `golf_society_id_UNIQUE` ON `leaderboard`.`t_golf_society` (`golf_society_id` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `golf_society_name_UNIQUE` ON `leaderboard`.`t_golf_society` (`golf_society_name` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_society_membership`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_society_membership` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_society_membership` (
  `society_member_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `golf_society_id` INT ZEROFILL NOT NULL ,
  `user_id` INT ZEROFILL NOT NULL ,
  PRIMARY KEY (`society_member_id`) ,
  CONSTRAINT `fk_society_membership_golf_society`
    FOREIGN KEY (`golf_society_id` )
    REFERENCES `leaderboard`.`t_golf_society` (`golf_society_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_society_membership_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `leaderboard`.`t_user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_golf_society_id` ON `leaderboard`.`t_society_membership` (`golf_society_id` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_user_id` ON `leaderboard`.`t_society_membership` (`user_id` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `leaderboard`.`t_competition_entry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leaderboard`.`t_competition_entry` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `leaderboard`.`t_competition_entry` (
  `competition_entry_id` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `competition_id` INT ZEROFILL NOT NULL ,
  `scorecard_id` INT ZEROFILL NOT NULL ,
  `society_member_id` INT ZEROFILL NOT NULL ,
  `society_id` INT ZEROFILL NOT NULL ,
  PRIMARY KEY (`competition_entry_id`) ,
  CONSTRAINT `fk_t_competition_entry_t_competition1`
    FOREIGN KEY (`competition_id` )
    REFERENCES `leaderboard`.`t_competition` (`competition_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_competition_entry_t_scorecard1`
    FOREIGN KEY (`scorecard_id` )
    REFERENCES `leaderboard`.`t_scorecard` (`scorecard_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_competition_entry_t_society_membership1`
    FOREIGN KEY (`society_member_id` )
    REFERENCES `leaderboard`.`t_society_membership` (`society_member_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_competition_entry_t_golf_society1`
    FOREIGN KEY (`society_id` )
    REFERENCES `leaderboard`.`t_golf_society` (`golf_society_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_competition_id` ON `leaderboard`.`t_competition_entry` (`competition_id` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_scorecard_id` ON `leaderboard`.`t_competition_entry` (`scorecard_id` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_society_member_id` ON `leaderboard`.`t_competition_entry` (`society_member_id` ASC) ;

SHOW WARNINGS;
CREATE INDEX `fk_society_id` ON `leaderboard`.`t_competition_entry` (`society_id` ASC) ;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
