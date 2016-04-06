@Echo off

:START

@echo off
echo "Enter your SysDba Credentials for Login"
set /p p1="User_Name:" %=% 
set /p p2="Password:" %=%
set /p p3="Database_Name:" %=%

@echo -------------------------------------------------------------------
@echo        WARNING: Don't Enter SysDba in the new schema name
@echo -------------------------------------------------------------------

echo "Please Enter Schema Name & Password for Single Schema Creation"
@echo "If the Schema does not exit,It will be created automatically"
@echo "If the Schema Already exists,It will be dropped and recreated automatically"

pause

set /p p5="Schema_Name:" %=% 
set /p p6="Password:" %=%

@echo drop user %p5% cascade ; | sqlplus %p1%/%p2%@%p3% 

@echo create user %p5% identified by %p6% ; | sqlplus %p1%/%p2%@%p3% 

@echo grant all privileges to %p5%; | sqlplus %p1%/%p2%@%p3% 

sqlplus %p5%/%p6% @"../Create_DB_Single_Script/hire_Create_DB.sql"

pause 

exit