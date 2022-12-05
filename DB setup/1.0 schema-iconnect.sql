/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 
Source Host           : localhost:3306
Source Database       : 

Target Server Type    : MYSQL
Target Server Version : 
File Encoding         : 

Date: 2022-12-05 15:00:00
*/
CREATE DATABASE iconnectsampleapplab CHARACTER SET utf8;
use iconnectsampleapplab;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- TABLE STRUCTURE FOR `TBL_AUDIT_REV_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_AUDIT_REV_INFO`;
CREATE TABLE `TBL_AUDIT_REV_INFO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `FUNC` VARCHAR(255) DEFAULT NULL,
  `FUNC_NAME` VARCHAR(255) DEFAULT NULL,
  `TIMESTAMP` BIGINT(20) DEFAULT NULL,
  `AUDIT_TIME` DATETIME DEFAULT NULL,
  `USER_ID` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- RECORDS OF TBL_AUDIT_REV_INFO
-- ----------------------------

-- ----------------------------
-- TABLE STRUCTURE FOR `TBL_AUDIT_REV_INFO_ASS`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_AUDIT_REV_INFO_ASS`;
CREATE TABLE `TBL_AUDIT_REV_INFO_ASS` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_KEY` VARCHAR(255) DEFAULT NULL,
  `ENTITY_ID` BLOB,
  `ENTITY_NAME` VARCHAR(255) DEFAULT NULL,
  `TABLE_NAME` VARCHAR(255) DEFAULT NULL,
  `REV_ID` INT(11) DEFAULT NULL,
  `REV_TYPE` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `REV_ID` (`REV_ID`),
  CONSTRAINT `TBL_AUDIT_REV_INFO_ASS_IBFK_1` FOREIGN KEY (`REV_ID`) REFERENCES `TBL_AUDIT_REV_INFO` (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- RECORDS OF TBL_AUDIT_REV_INFO_ASS
-- ----------------------------

-- ----------------------------
-- TABLE STRUCTURE FOR `TBL_CODE_INT`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_CODE_INT`;
CREATE TABLE `TBL_CODE_INT` (
  `ID` VARCHAR(255) NOT NULL DEFAULT '',
  `CODETYPE_ID` VARCHAR(255) NOT NULL,
  `CODE_ID` VARCHAR(20) NOT NULL,
  `PARENT_CODETYPE_ID` VARCHAR(255) DEFAULT NULL,
  `PARENT_CODE_ID` VARCHAR(255) DEFAULT NULL,
  `CODE_DESC` VARCHAR(66) DEFAULT NULL,
  `CODE_SEQ` INT(11) DEFAULT NULL,
  `STATUS` CHAR(1) NOT NULL,
  `EFFECTIVE_DT` DATETIME DEFAULT NULL,
  `EXPIRY_DT` DATETIME DEFAULT NULL,
  `UPDATED_BY` VARCHAR(32) DEFAULT NULL,
  `UPDATED_DT` DATETIME DEFAULT NULL,
  `LOCALE` VARCHAR(2) NOT NULL,
  `VERSION` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_ID` (`ID`),
  UNIQUE KEY `UK_HRO55GJ2CGFV4U6V7LDL9G4NV` (`CODETYPE_ID`,`CODE_ID`,`LOCALE`),
  CONSTRAINT `FK_HOXYXJX56YTUYC8W4XFHUBE4K` FOREIGN KEY (`CODETYPE_ID`) REFERENCES `TBL_CODETYPE` (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `TBL_CODE_INT_AUD`;
CREATE TABLE TBL_CODE_INT_AUD (
       ID VARCHAR(255) NOT NULL,
        REV INTEGER NOT NULL,
        REVTYPE TINYINT,
        CODE_DESC VARCHAR(255),
        CODE_ID VARCHAR(255),
        CODE_SEQ INTEGER,
        EFFECTIVE_DT DATETIME,
        EXPIRY_DT DATETIME,
        LOCALE VARCHAR(255),
        PARENT_CODE_ID VARCHAR(255),
        PARENT_CODETYPE_ID VARCHAR(255),
        STATUS VARCHAR(255),
        UPDATED_BY VARCHAR(255),
        UPDATED_DT DATETIME,
        CODETYPE_ID VARCHAR(255),
        PRIMARY KEY (ID, REV)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- TABLE STRUCTURE FOR `TBL_CODETYPE`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_CODETYPE`;
CREATE TABLE `TBL_CODETYPE` (
  `ID` VARCHAR(255) NOT NULL,
  `APP_ID` VARCHAR(255) DEFAULT NULL,
  `CODETYPE_ID` VARCHAR(255) DEFAULT NULL,
  `EFFECTIVE_DATE` DATETIME DEFAULT NULL,
  `EXPIRE_DATE` DATETIME DEFAULT NULL,
  `CODETYPE_DESC` VARCHAR(66) DEFAULT NULL,
  `CODETYPE_TABLE` VARCHAR(66) DEFAULT NULL,
  `COL_CODETYPE_ID` VARCHAR(66) DEFAULT NULL,
  `COL_CODE_ID` VARCHAR(66) DEFAULT NULL,
  `COL_CODE_DESC` VARCHAR(66) DEFAULT NULL,
  `COL_CODE_SEQ` VARCHAR(66) DEFAULT NULL,
  `COL_STATUS` VARCHAR(66) DEFAULT NULL,
  `COL_EFFECTIVE_DT` VARCHAR(66) DEFAULT NULL,
  `COL_EXPIRY_DT` VARCHAR(66) DEFAULT NULL,
  `COL_CODE_LOCALE` VARCHAR(20) DEFAULT NULL,
  `UPDATED_BY` VARCHAR(32) DEFAULT NULL,
  `UPDATED_DT` DATETIME DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_IX3UE7WOSCL9W8SH31TBHC0G3` (`APP_ID`,`CODETYPE_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `TBL_CODETYPE_AUD`;
CREATE TABLE TBL_CODETYPE_AUD (
    ID VARCHAR(255) NOT NULL,
	REV INTEGER NOT NULL,
	REVTYPE TINYINT,
	APP_ID VARCHAR(255),
	EFFECTIVE_DATE DATETIME,
	EXPIRE_DATE DATETIME,
	CODETYPE_ID VARCHAR(255),
	CODETYPE_DESC VARCHAR(255),
	CODETYPE_TABLE VARCHAR(255),
	COL_CODE_DESC VARCHAR(255),
	COL_CODE_ID VARCHAR(255),
	COL_CODE_SEQ VARCHAR(255),
	COL_CODETYPE_ID VARCHAR(255),
	COL_EFFECTIVE_DT VARCHAR(255),
	COL_EXPIRY_DT VARCHAR(255),
	COL_STATUS VARCHAR(255),
	COL_CODE_LOCALE VARCHAR(255),
	UPDATED_BY VARCHAR(255),
	UPDATED_DT DATETIME,
	PRIMARY KEY (ID, REV)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ----------------------------
-- TABLE STRUCTURE FOR TBL_PARAM
-- ----------------------------
DROP TABLE IF EXISTS `TBL_PARAM`;
CREATE TABLE `TBL_PARAM` (
	`APP_ID` VARCHAR(255) NOT NULL,
	`PARAM_KEY` VARCHAR(255) NOT NULL,
	`PARAM_VALUE` VARCHAR(2000) NULL DEFAULT NULL,
	`PARAM_TYPE` VARCHAR(255) NULL DEFAULT NULL,
	`PARAM_DESC` VARCHAR(255) NULL DEFAULT NULL,
	`EFFECTIVE_DATE` DATETIME NULL DEFAULT NULL,
	`EXPIRE_DATE` DATETIME NULL DEFAULT NULL,
	`CREATED_BY` VARCHAR(255) NULL DEFAULT NULL,
	`CREATED_DT` DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` VARCHAR(255) NULL DEFAULT NULL,
	`UPDATED_DT` DATETIME NULL DEFAULT NULL,
	`VERSION` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`APP_ID`, `PARAM_KEY`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `TBL_PARAM_AUD`;
CREATE TABLE TBL_PARAM_AUD (
       APP_ID VARCHAR(32) NOT NULL,
        PARAM_KEY VARCHAR(255) NOT NULL,
        REV INTEGER NOT NULL,
        REVTYPE TINYINT,
        EFFECTIVE_DATE DATETIME,
        EXPIRE_DATE DATETIME,
        PARAM_DESC VARCHAR(255),
        PARAM_TYPE VARCHAR(255),
        PARAM_VALUE VARCHAR(255),
        PRIMARY KEY (APP_ID, PARAM_KEY, REV)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

ALTER TABLE TBL_PARAM_AUD ADD CONSTRAINT FKNTHXKCDV05H1NCEHVAD7PCC53 FOREIGN KEY (REV) REFERENCES TBL_AUDIT_REV_INFO (ID);
	   
DROP TABLE IF EXISTS `TBL_PERSISTENT_AUDIT_EVENT`;
DROP TABLE IF EXISTS `TBL_PERSISTENT_AUDIT_EVT_DATA`;

CREATE TABLE `TBL_PERSISTENT_AUDIT_EVENT` (
  `EVENT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` DATETIME DEFAULT NULL,
  `EVENT_TYPE` VARCHAR(255) DEFAULT NULL,
  `PRINCIPAL` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `TBL_PERSISTENT_AUDIT_EVT_DATA` (
  `EVENT_ID` BIGINT(20) NOT NULL,
  `VALUE` VARCHAR(255) DEFAULT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`EVENT_ID`,`NAME`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

ALTER TABLE TBL_PERSISTENT_AUDIT_EVT_DATA ADD CONSTRAINT FKGAO3Q7PNBMAR2RC4EHG9EGA4 FOREIGN KEY (EVENT_ID) REFERENCES TBL_PERSISTENT_AUDIT_EVENT (EVENT_ID);

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
 `next_val` bigint(20) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

Insert into hibernate_sequence(next_val) values(0);

DROP TABLE IF EXISTS `TBL_WARD`;
CREATE TABLE `TBL_WARD` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `WARD_REFERENCE_ID` VARCHAR(7) DEFAULT NULL,
  `WARD_NAME` VARCHAR(10) DEFAULT NULL,
  `WARD_CLASS_TYPE` VARCHAR(20) DEFAULT NULL,
  `WARD_LOCATION` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_ID` (`WARD_REFERENCE_ID`),
  UNIQUE KEY `UNIQUE_WARD` (`WARD_NAME`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `TBL_BED`;
CREATE TABLE `TBL_BED` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `BED_REFERENCE_ID` VARCHAR(6) DEFAULT NULL,
  `BED_NAME` VARCHAR(17) DEFAULT NULL,
  `WARD_ID` BIGINT(20) DEFAULT NULL,
  `WARD_ALLOCATION_DATE` DATETIME DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

ALTER TABLE `TBL_BED`
ADD FOREIGN KEY (WARD_ID) REFERENCES TBL_WARD(ID);
