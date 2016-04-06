/* Tables for JobSeeker
*/
create table job_seeker(
ID NUMBER(20) NOT NULL ,
owner_user_id NUMBER(20),
created_by varchar2(50),
created_date TIMESTAMP  DEFAULT sysdate,
updated_by varchar2(50),
updated_date TIMESTAMP,
deleted number(1),
USER_ID varchar2(255),
USER_ORGANIZATION_ID varchar2(255),
jobSeeker_Identifier varchar2(255),
facebook_Account_ID varchar2(255),
linkedIn_Account_ID varchar2(255),
twitter_Account_ID varchar2(255),
	PRIMARY KEY (id)
)
/

COMMIT
/

--//@UNDO
DROP TABLE job_seeker
/