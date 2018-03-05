-- ============================================================
--   Table: TEACHERS                                    
-- ============================================================
create table TEACHERS
(
    ID           	INT 				NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	VARCHAR(2)			not null,
	DOC_NUMBER		VARCHAR(11)			not null,
    NAME	      	VARCHAR(50)         not null,
	PHONE	      	VARCHAR(15)         not null,
    EMAIL       	VARCHAR(50)         not null,
    BIRTH_DATE 		DATE                not null,
    PRIMARY KEY (ID)
)
/

-- ============================================================
--   Table: COURSES                                      
-- ============================================================
create table COURSES
(
	ID           	INT 					NOT NULL AUTO_INCREMENT,
    DESCRIPTION     VARCHAR(50)           	not null,
    constraint COURSES primary key (ID)
)
/

-- ============================================================
--   Table: STUDENTS                                   
-- ============================================================
create table STUDENTS
(
    ID           	INT					NOT NULL AUTO_INCREMENT,
	DOC_TYPE	  	VARCHAR(2)			not null,
	DOC_NUMBER		VARCHAR(11)			not null,
    NAME      		VARCHAR(50)        	not null,
	PHONE	      	VARCHAR(15)         not null,
    EMAIL       	VARCHAR(50)         not null,
    BIRTH_DATE 		DATE               	not null,
    primary key (DNI)
)
/

-- ============================================================
--   Table: GROUPS                                        
-- ============================================================
create table GROUPS
(
    ID           	INT				 NOT NULL AUTO_INCREMENT,
    OPEN_YEAR	    DATE             not null,
    ID_COURSE     	INT              not null,
    primary key (ID, OPEN_YEAR),
	FOREIGN KEY (ID_COURSE) REFERENCES COURSES(id)
)
/

-- ============================================================
--   Table: STUDENTS_X_GROUP                          
-- ============================================================
create table STUDENTS_X_GROUP
(
    ID           	INT				 NOT NULL AUTO_INCREMENT,
    ID_GROUP        INT              not null,
	ID_STUDENT      INT              not null,
    MARK       		DECIMAL(4,2)     null,
    constraint STUDENTS_X_GROUP primary key (ID),
	FOREIGN KEY (ID_GROUP) REFERENCES GROUPS(id),
	FOREIGN KEY (ID_STUDENT) REFERENCES STUDENTS(id)
)
/