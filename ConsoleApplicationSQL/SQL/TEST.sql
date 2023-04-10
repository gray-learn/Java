INSERT INTO regions ( region_id, region_name) VALUES ( 900, 'Humber')
delete regions where region_id = 900
select * from regions where region_id = 900
select COUNTRY_ID, COUNTRY_NAME, AREA from countries where COUNTRY_ID = 60
update countries set AREA = 329750 where COUNTRY_ID = 60
update countries set AREA = 100 where COUNTRY_ID = 60 
--ATTENTION "no ; ==> ORA-00933: SQL command not properly ended"