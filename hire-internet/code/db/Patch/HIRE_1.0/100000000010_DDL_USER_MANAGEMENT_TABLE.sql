/*
	Tables for User management
*/

create table ADDRESS(
ID NUMBER(20) NOT NULL ,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
street_Line_1 varchar2(255),
street_Line_2 varchar2(255),
city varchar2(255),
state varchar2(100),
pin_code varchar2(100),
PRIMARY KEY (id)
)
/

create table MASTER_ORGANIZATION(
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
ORGANIZATION_NAME varchar2(50),
PRIMARY KEY (id)
)
/


create table user_data(
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
USER_IDENTIFIER varchar(255),
USER_NAME varchar2(255),
PRIMARY KEY (id)
)
/


create table USER_ADDRESS(
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
USER_ID NUMBER(20),
PRIMARY_ADDRESS_ID NUMBER(20),
SECONDARY_ADDRESS_ID NUMBER(20),
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user_data (id),
	FOREIGN KEY (PRIMARY_ADDRESS_ID) REFERENCES Address (id),
	FOREIGN KEY (SECONDARY_ADDRESS_ID) REFERENCES Address (id)
)
/

create table User_Organization(
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
master_Organization_ID number(20),
	PRIMARY KEY (id),
	FOREIGN KEY (master_Organization_ID) REFERENCES master_Organization (id)
)
/


create table User_Organization_List (
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
USER_ID number(20),
USER_ORGANIZATION_ID number(20),
	PRIMARY KEY (id),
	FOREIGN KEY (USER_ID) REFERENCES USER_DATA (id),
	FOREIGN KEY (USER_ORGANIZATION_ID) REFERENCES USER_ORGANIZATION (id)	
)
/

create table USER_PASSWORD(
ID NUMBER(20) NOT NULL,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
password varchar2(255),
user_id number(20),
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES USER_DATA (id)	
)
/

COMMIT
/

--//@UNDO
drop table USER_PASSWORD
/
drop table User_Organization_List
/
drop table User_Organization
/
drop table USER_ADDRESS
/
drop table user_data
/
drop table MASTER_ORGANIZATION
/
drop table ADDRESS
/
