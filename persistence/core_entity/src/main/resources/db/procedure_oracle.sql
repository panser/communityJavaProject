-- PROC_SIMPLE
create or replace PROCEDURE PROC_SIMPLE
(
  IN_ID IN NUMBER
, OUT_EMAIL OUT VARCHAR2
, OUT_LOGIN OUT VARCHAR2
, OUT_PASSWORD OUT VARCHAR2
)
AS
BEGIN
  SELECT EMAIL, LOGIN, PASSWORD
  INTO OUT_EMAIL, OUT_LOGIN, OUT_PASSWORD
  FROM CORE_USERS WHERE id = IN_ID;
  COMMIT;
END;


-- PROC_OUT_REF
create or replace PROCEDURE PROC_OUT_REF(users OUT sys_refcursor)
AS
  BEGIN
    open users for select * FROM CORE_USERS;
END;

-- FUNC_SIMPLE
create or replace FUNCTION FUNC_SIMPLE
  (
    IN_ID IN NUMBER
  )
  RETURN VARCHAR2
AS
  out_name VARCHAR2(255);
  BEGIN
    SELECT CONCAT(CONCAT(CONCAT(CONCAT(login, ' '), email), ' '), password)
    INTO out_name
    FROM core_users where id = IN_ID;
    RETURN out_name;
END;



-- PROC_IN_ARRAY
create or replace TYPE
  user_id_array IS VARRAY(20) OF NUMBER;
--  user_id_array IS VARRAY(20) OF VARCHAR2(10);

create or replace TYPE VARCHAR2_ARRAY
AS VARRAY(200) OF VARCHAR2(255);

CREATE OR REPLACE PROCEDURE PROC_IN_ARRAY (
--  in_user_ids IN dbms_sql.varchar2s
   in_user_ids IN user_id_array
  ,result_proc OUT NUMBER
--  ,out_user_emails OUT VARCHAR2_ARRAY
--  ,out_user_emails OUT dbms_sql.varchar2s
--  ,out_user_emails OUT dbms_sql.varchar2_table
)
AS
--   CURSOR c_customers is SELECT  id FROM core_users;
  BEGIN
    FOR i IN 1..in_user_ids.count loop
      dbms_output.put_line(in_user_ids(i));
--    open users for SELECT * FROM CORE_USERS WHERE id = in_user_ids(i);
--    DELETE FROM CORE_USERS WHERE id = in_user_ids(i);
--    SELECT EMAIL BULK COLLECT INTO out_user_emails FROM CORE_USERS WHERE id = in_user_ids(i);
    END LOOP;
    result_proc := 0;
--  COMMIT;
  END PROC_IN_ARRAY;