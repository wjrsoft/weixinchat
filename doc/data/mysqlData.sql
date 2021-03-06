DROP TABLE dept;
CREATE TABLE dept (DEPTNO int(2) NOT NULL, DNAME varchar(14), LOC varchar(13), PRIMARY KEY (DEPTNO)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO dept (DEPTNO, DNAME, LOC) VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept (DEPTNO, DNAME, LOC) VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO dept (DEPTNO, DNAME, LOC) VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO dept (DEPTNO, DNAME, LOC) VALUES (40, 'OPERATIONS', 'BOSTON');
DROP TABLE emp;
CREATE TABLE emp (empno int(4) NOT NULL, ename varchar(10), job varchar(9), mgr int(4), hiredate datetime, sal float(7,2), comm float(7,2), deptno float, PRIMARY KEY (empno)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17 00:00:00', 3000.0, null, 20.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20 00:00:00', 1600.0, 300.0, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22 00:00:00', 1250.0, 500.0, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02 00:00:00', 2975.0, null, 20.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28 00:00:00', 1250.0, 1400.0, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01 00:00:00', 2850.0, null, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09 00:00:00', 2450.0, null, 10.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19 00:00:00', 3000.0, null, 20.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7839, 'KING', 'PRESIDENT', null, '1981-11-17 00:00:00', 5000.0, null, 10.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08 00:00:00', 1500.0, 0.0, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-05-23 00:00:00', 1100.0, null, 20.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03 00:00:00', 950.0, null, 30.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03 00:00:00', 3000.0, null, 20.0);
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7934, 'MILLER', 'CLERK', 7782, '2017-12-01 21:40:51', 1300.0, null, 10.0);

create table WX_USER(openid varchar(128),nickname varchar(128),pwd varchar(32));