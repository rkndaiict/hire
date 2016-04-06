insert into master_organization(id, ORGANIZATION_NAME)
values(hibernate_sequence.nextval, 'INDIVIDUAL')
/
insert into master_organization(id, ORGANIZATION_NAME)
values(hibernate_sequence.nextval, 'AGENCY')
/
insert into master_organization(id, ORGANIZATION_NAME)
values(hibernate_sequence.nextval, 'COMPANY')
/

COMMIT
/

--//@UNDO
delete from master_organization
/