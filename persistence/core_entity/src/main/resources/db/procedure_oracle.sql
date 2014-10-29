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


create or replace PROCEDURE PROC_OUT_REF(users OUT sys_refcursor)
AS
  BEGIN
    open users for select * FROM CORE_USERS;
END;


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