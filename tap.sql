DROP DATABASE IF EXISTS demoapp;

CREATE DATABASE demoapp;

USE demoapp;

CREATE TABLE household (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type ENUM('landed', 'condominium', 'hdb')
);

CREATE TABLE person (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender ENUM('male', 'female') NOT NULL,
    marital_status ENUM('single', 'married', 'divorced', 'separated', 'widowed') NOT NULL,
    occupation ENUM('unemployed', 'employed', 'student') NOT NULL,
    income INT NOT NULL,
    dob DATE NOT NULL,
    spouse INT(6) UNSIGNED,
    household INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (spouse) references person (id),
    FOREIGN KEY (household) references household (id) ON DELETE CASCADE
);