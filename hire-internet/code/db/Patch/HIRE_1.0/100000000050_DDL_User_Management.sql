/* Tables for User management
*/


/*All User's are stored in APP_USER table*/
create table APP_USER (
ID NUMBER(20) NOT NULL,
USER_IDENTIFIER varchar(255) NOT NULL,
USER_NAME varchar2(255) NOT NULL,
password VARCHAR(100) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name  VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL,
status VARCHAR(30) NOT NULL, 
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),   
PRIMARY KEY (id),
UNIQUE (USER_IDENTIFIER, USER_NAME)
)
/
  
/* USER_PROFILE table contains all possible roles */
create table USER_PROFILE(
   id NUMBER(20) NOT NULL,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
)
/
  
/* JOIN TABLE for MANY-TO-MANY relationship*/ 
CREATE TABLE APP_USER_USER_PROFILE (
    user_id NUMBER(20) NOT NULL,
    user_profile_id NUMBER(20) NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
)
/


--//@UNDO
DROP TABLE APP_USER_USER_PROFILE
/
DROP TABLE USER_PROFILE
/
DROP TABLE APP_USER
/