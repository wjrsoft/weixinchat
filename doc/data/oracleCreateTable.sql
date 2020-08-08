-- Create table
create table EMP
(
  EMPNO    NUMBER(4) not null,
  ENAME    VARCHAR2(10),
  JOB      VARCHAR2(9),
  MGR      NUMBER(4),
  HIREDATE DATE,
  SAL      NUMBER(7,2),
  COMM     NUMBER(7,2),
  DEPTNO   NUMBER(2)
);

-- Create table
create table DEPT
(
  DEPTNO NUMBER(2) not null,
  DNAME  VARCHAR2(14),
  LOC    VARCHAR2(13)
)