CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_SIMPLE`(
    IN in_id BIGINT,
    OUT out_email VARCHAR(255),
    OUT out_login VARCHAR(255),
    OUT out_password VARCHAR(255))
BEGIN
    SELECT email, login, password
    INTO out_email, out_login, out_password
    FROM core_users where id = in_id;
END


CREATE DEFINER=`root`@`localhost` PROCEDURE `PROC_OUT_REF`()
BEGIN
  SELECT * FROM core_users;
END


CREATE DEFINER=`root`@`localhost` FUNCTION `FUNC_SIMPLE`(in_id BIGINT) RETURNS varchar(255) CHARSET utf8
READS SQL DATA
  BEGIN
    DECLARE out_name VARCHAR(255);
    SELECT concat(login, ' ', email, ' ', password)
    INTO out_name
    FROM core_users where id = in_id;
    RETURN out_name;
END