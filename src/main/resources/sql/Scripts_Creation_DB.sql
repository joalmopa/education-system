DROP TABLE SUBJECT_X_GROUP_X_TEACHER;
DROP TABLE STUDENTS_X_GROUP;
DROP TABLE GROUPS;
DROP TABLE STUDENTS;
DROP TABLE SUBJECTS;
DROP TABLE TEACHERS;
-- DROP SCHEMA 'educationsystem';

-- CREATE SCHEMA 'educationsystem';

CREATE TABLE TEACHERS
(
	ID           	  INT 			NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	  VARCHAR(2)	NOT NULL,
	DOC_NUMBER		  VARCHAR(11)	NOT NULL,
	NAME	      	  VARCHAR(50)   NOT NULL,
	PHONE	      	  VARCHAR(15)   NOT NULL,
	EMAIL       	  VARCHAR(50)   NOT NULL,
	BIRTH_DATE 		  DATE          NOT NULL,
	GENDER            VARCHAR(1)    NOT NULL,
  PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: SUBJECTS
-- ============================================================
CREATE TABLE SUBJECTS
(
	ID           	INT 			NOT NULL AUTO_INCREMENT,
	DESCRIPTION     VARCHAR(50)   	NOT NULL,
	CONSTRAINT SUBJECTS PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: STUDENTS
-- ============================================================
CREATE TABLE STUDENTS
(
	ID                	INT					  NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	    VARCHAR(2)		NOT NULL,
	DOC_NUMBER		    VARCHAR(11)		NOT NULL,
	NAME      		    VARCHAR(50)   NOT NULL,
	PHONE	      	    VARCHAR(15)   NOT NULL,
	EMAIL       	    VARCHAR(50)   NOT NULL,
	BIRTH_DATE 		    DATE         	NOT NULL,
	GENDER          	VARCHAR(1)    NOT NULL,
	PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: GROUPS
-- ============================================================
CREATE TABLE GROUPS
(
  ID           		INT				NOT NULL AUTO_INCREMENT,
  DESCRIPTION     	VARCHAR(50)   	NOT NULL,
  OPEN_YEAR	    	VARCHAR(4)      NOT NULL,
  PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: STUDENTS_X_GROUP
-- ============================================================
CREATE TABLE STUDENTS_X_GROUP
(
	ID           	INT				NOT NULL AUTO_INCREMENT,
	ID_GROUP      	INT             NOT NULL,
	ID_STUDENT    	INT             NOT NULL,
	MARK       		DECIMAL(4,2)    NULL,
	IDPERIODO     INT              NOT NULL,
  CONSTRAINT STUDENTS_X_GROUP PRIMARY KEY (ID),
	FOREIGN KEY (ID_GROUP) REFERENCES GROUPS(ID),
	FOREIGN KEY (ID_STUDENT) REFERENCES STUDENTS(ID),
	FOREIGN KEY (IDPERIODO) REFERENCES PERIOD(IDPERIODO);
);


-- ============================================================
--   TABLE: SUBJECT_X_GROUP_X_TEACHER
-- ============================================================
CREATE TABLE SUBJECT_X_GROUP_X_TEACHER
(
	ID           	INT				NOT NULL AUTO_INCREMENT,
	ID_SUBJECT    	INT             NOT NULL,
    ID_GROUP      	INT             NOT NULL,
    ID_TEACHER    	INT             NOT NULL,
  CONSTRAINT SUBJECT_X_GROUP_X_TEACHER PRIMARY KEY (ID),
	FOREIGN KEY (ID_SUBJECT) REFERENCES SUBJECTS(ID),
    FOREIGN KEY (ID_GROUP) REFERENCES GROUPS(ID),
	FOREIGN KEY (ID_TEACHER) REFERENCES TEACHERS(ID)
);


-- ============================================================
--   TABLE: PERIOD
-- ============================================================
create table PERIOD
(
  IDPERIODO           	INT 				NOT NULL AUTO_INCREMENT,
	NUM_PERIODO	  	INT		not null,
	PORCENTAGE		DECIMAL(4,2) 		not null,
  PRIMARY KEY (IDPERIODO)
)
