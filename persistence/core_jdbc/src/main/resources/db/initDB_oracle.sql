DROP TABLE "BEANS_CONFIG"."CORE_USERS";
CREATE TABLE "BEANS_CONFIG"."CORE_USERS"
("ID"       NUMBER(19, 0) NOT NULL ENABLE,
 "EMAIL"    VARCHAR2(255 CHAR),
 "LOGIN"    VARCHAR2(255 CHAR),
 "PASSWORD" VARCHAR2(255 CHAR),
  PRIMARY KEY ("ID")
    USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
    STORAGE (INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
    PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
    BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
    TABLESPACE "USERS" ENABLE
) SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE (INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

DROP TABLE "BEANS_CONFIG"."SEQUENCE_TABLE";
CREATE TABLE "BEANS_CONFIG"."SEQUENCE_TABLE"
("SEQ_NAME"  VARCHAR2(255 CHAR),
 "SEQ_COUNT" NUMBER(10, 0)
) SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
NOCOMPRESS LOGGING
STORAGE (INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "USERS";

DROP SEQUENCE "BEANS_CONFIG".CORE_USERS_SEQ;
CREATE SEQUENCE  "BEANS_CONFIG"."CORE_USERS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;

-- -- only if you will use trigger in BD
-- create trigger CORE_USERS_IDENTITY
-- BEFORE
-- insert on CORE_USERS
-- for each row
--   begin
--     :new.ID := CORE_USERS_SEQ.nextval;
--   end;
