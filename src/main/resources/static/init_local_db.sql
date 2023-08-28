/**
 * Author:  devel
 * Created: 30 июл. 2023 г.
 */
create database validator character set utf8 collate utf8_general_ci;
create user 'validator'@'localhost' identified by 'validator';
grant all privileges on validator.* to 'validator'@'localhost';
