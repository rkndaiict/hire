SET DEFINE OFF
spool on
spool hire_Create_DB_log.txt

-- Common  Module
@../common-module-db/oracle/ddl/createtable.sql;

commit;

spool off;
disconnect;
exit;
