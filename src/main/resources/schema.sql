DROP TABLE IF EXISTS ACCOUNT;
DROP SEQUENCE IF EXISTS ACCOUNT_ID_SEQ;

create table ACCOUNT
(
    ID          bigserial primary key,
    NAME        varchar(1024) not null,
    BALANCE     integer,
    PIN         varchar(1024) not null
);