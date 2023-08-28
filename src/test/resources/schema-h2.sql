-- MySQL dump 10.13  Distrib 5.7.31, for macos10.14 (x86_64)
--
-- Host: localhost    Database: validator
-- ------------------------------------------------------
-- Server version	5.7.31


--
-- Table structure for table country_code
--

DROP TABLE IF EXISTS "COUNTRY_CODE";
CREATE TABLE "COUNTRY_CODE" (
  "ID" bigint AUTO_INCREMENT NOT NULL PRIMARY KEY,
  "CODE" varchar(255) DEFAULT NULL,
  "COUNTRY" varchar(255) DEFAULT NULL
);


