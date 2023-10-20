DROP TABLE IF EXISTS ACCOUNT;
DROP SEQUENCE IF EXISTS ACCOUNT_ID_SEQ;
DROP TABLE IF EXISTS TRANSACTION;
DROP SEQUENCE IF EXISTS TRANSACTION_ID_SEQ;

create table ACCOUNT
(
    ID          bigserial primary key,
    NAME        varchar(1024) not null,
    BALANCE     integer,
    PIN         varchar(1024) not null
);

create table TRANSACTION
(
    ID          bigserial primary key,
    ID_ACCOUNT  bigint not null,
    SUM         integer,
    OPERATION   varchar(1024) not null
);