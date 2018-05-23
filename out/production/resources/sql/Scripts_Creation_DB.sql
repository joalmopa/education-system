DROP TABLE STUDENTS_X_GROUP;
DROP TABLE GROUPS;
DROP TABLE STUDENTS;
DROP TABLE COURSES;
DROP TABLE TEACHERS;

CREATE TABLE TEACHERS
(
  ID           	  INT 				  NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	  VARCHAR(2)		NOT NULL,
	DOC_NUMBER		  VARCHAR(11)		NOT NULL,
  NAME	      	  VARCHAR(50)   NOT NULL,
	PHONE	      	  VARCHAR(15)   NOT NULL,
  EMAIL       	  VARCHAR(50)   NOT NULL,
  BIRTH_DATE 		  DATE          NOT NULL,
  GENDER          VARCHAR(1)    NOT NULL,
  PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: COURSES
-- ============================================================
CREATE TABLE COURSES
(
	ID           	  INT 					NOT NULL AUTO_INCREMENT,
  DESCRIPTION     VARCHAR(50)   NOT NULL,
  CONSTRAINT COURSES PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: STUDENTS
-- ============================================================
CREATE TABLE STUDENTS
(
  ID                INT					  NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	    VARCHAR(2)		NOT NULL,
	DOC_NUMBER		    VARCHAR(11)		NOT NULL,
  NAME      		    VARCHAR(50)   NOT NULL,
	PHONE	      	    VARCHAR(15)   NOT NULL,
  EMAIL       	    VARCHAR(50)   NOT NULL,
  BIRTH_DATE 		    DATE         	NOT NULL,
  GENDER          VARCHAR(1)    NOT NULL,
  PRIMARY KEY (ID)
);

-- ============================================================
--   TABLE: GROUPS
-- ============================================================
CREATE TABLE GROUPS
(
  ID           	INT				      NOT NULL AUTO_INCREMENT,
  OPEN_YEAR	    VARCHAR(4)      NOT NULL,
  ID_COURSE     INT             NOT NULL,
  PRIMARY KEY (ID),
	FOREIGN KEY (ID_COURSE) REFERENCES COURSES(ID)
);

-- ============================================================
--   TABLE: STUDENTS_X_GROUP
-- ============================================================
CREATE TABLE STUDENTS_X_GROUP
(
  ID           	INT				      NOT NULL AUTO_INCREMENT,
  ID_GROUP      INT             NOT NULL,
	ID_STUDENT    INT             NOT NULL,
  MARK       		DECIMAL(4,2)    NULL,
  CONSTRAINT STUDENTS_X_GROUP PRIMARY KEY (ID),
	FOREIGN KEY (ID_GROUP) REFERENCES GROUPS(ID),
	FOREIGN KEY (ID_STUDENT) REFERENCES STUDENTS(ID)
);