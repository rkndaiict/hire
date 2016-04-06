
-- START CHANGE SCRIPT #100000000040: 100000000040_DML_JOB_SEEKER_TABLE.sql

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



INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (100000000040, CURRENT_TIMESTAMP, USER, '100000000040_DML_JOB_SEEKER_TABLE.sql');

COMMIT;

-- END CHANGE SCRIPT #100000000040: 100000000040_DML_JOB_SEEKER_TABLE.sql

