# Security Schema

# --- !Ups

CREATE TABLE User (
	id bigint(20) not null AUTO_INCREMENT ,
	email varchar(255) not null unique ,
	fullname varchar(255) not null ,
	password varchar(255) not null ,
	salt varchar(255) not null ,
	createdAt datetime not null ,
	deactivated boolean ,
	deactivatedAt datetime ,
	CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE SecureGroup (
	id bigint(20) not null AUTO_INCREMENT ,
	name varchar(255) not null unique ,
	createdAt datetime not null ,
	deactivated boolean ,
	deactivatedAt datetime ,
	CONSTRAINT pk_securegroup PRIMARY KEY (id)
);

CREATE TABLE UserSecureGroup (
	id bigint(20) not null AUTO_INCREMENT ,
	userId bigint(20) not null ,
	secureGroupId bigint(20) not null ,
	CONSTRAINT pk_usrsgrp PRIMARY KEY (id) ,
	CONSTRAINT fk_usrsgrp_user_id FOREIGN KEY (userId) REFERENCES User(id) ,
	CONSTRAINT fk_usrsgrp_securegroup_id FOREIGN KEY (secureGroupId) REFERENCES SecureGroup (id)
);

# --- !Downs

DROP TABLE User ;
DROP TABLE SecureGroup ;
DROP TABLE UserSecureGroup ;