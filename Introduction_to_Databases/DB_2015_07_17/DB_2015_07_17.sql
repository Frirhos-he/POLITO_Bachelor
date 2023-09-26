--Exercise 1
--BLOCK A
SELECT StudentID
FROM STUDENTS, PARTECIPATE_PHD_COMPETITION SPPC,
	 PHD_COMPETITION PC
WHERE PC.CCode = SPPC.CCode AND PC.DCode= SPPC.DCode
GROUP BY StudentID, PostingDate
HAVING COUNT (*)>=2

--MAIN BLOCK 
SELECT S.StudentID,S.Name
FROM STUDENTS
WHERE DegreeGrade>105 AND StudentID IN (BLOCK A)


--Exercise 2
A. projection S.StudentID, SName
B. left-semi-join: S.StudentId = SPPC1.StudentID
C. selection: DegreeGrade > 105
D. STUDENTS S
E. Theta-join : PC.CCode <> PC1.CCode AND SPPC.DDcode <> PC1.DDcode AND SPPC.Student = SPPC1.Student
F. Theta-join : PC.CCode = SPPC.CCode AND SPPC.DDcode = PC.DDcode
G. PHD_COMPETITION PC
H. SPPC
I. Theta-join : PC1.CCode = SPPC.Ccode AND PC1.DDcode = SPPC1.DCode
L. PHD_COMPETITION PC1
M. SPPC1

--Exercise 3
--BLOCK A
SELECT DCode
FROM PHD_COMPETITION PC
WHERE #OfOpenPosition <=6

--MAIN BLOCK 
SELECT Name, Scientificfield, COUNT(*)
FROM DEPARTMENT D, PHD_COMPETITION PC
WHERE D.DCode = PC.DCode AND PostingDate>'01/03/2014' AND D.DCode NOT IN (BLOCK A)
GROUP BY D.DCode, Name, Scientificfield

--Exercise 4
--BLOCK A
SELECT StudentID
FROM SPPC, DEPARTMENT D
WHERE SPPC.DCode = D.DCode
GROUP BY StudentID
HAVING COUNT (DISTINCT Scientificfield)>=3

--MAIN BLOCK
SELECT StudentID, Scientificfield
FROM SPPC1 DEPARTMENT D1, STUDENTS S
WHERE D1.DCode = SPPC1.DCode AND SPPC1.StudentID IN (BLOCK A)
		AND SPPC1.StudentID = S.StudentID
GROUP BY StudentID, Scientificfield
HAVING COUNT(*) = (BLOCK B)

--BLOCK B
SELECT COUNT(*)
FROM DEPARTMENT D2, PHD_COMPETITION PC
WHERE PC.DDcode = D2.DDcode AND D2.Scientificfield = D1.Scientificfield

--Exercise 4
ENTITY Project
	PrimaryKey: Code
	Attribute: title, totalbudget,start, end

BINARY-RELATIONSHIP HAS
	ACTIVITY(1,1) PROJECT(0,N)

ENTITY ACTIVITY
	Primarykey: ACode
	Attribute: Code(PROJECT)

GENERALIZATION(t,e)
	Parent-entity: Activity
	Children entity: Requirment defintion
	Children-entity: Development activity
				Attribute: CongressName(1,N)

BINARY-RELATIONSHIP HAS_S
		DEVELOPMENT_ACTIVITY(1,N) SOFT-COMPONENT(0,N)
		
ENTITY SOFT-COMPONENT
	PrimaryKey: SCode
	Attribute: Briefdescription, Name, Hard_requirment(1,N)
	
ENTITY EMPLOYER
	PrimaryKey: SSN
	Attribute: name, hiringDate, URL(*)


BINARY-RELATIONSHIP IS_COORDINATOR
	EMPLOYER(0,N) PROJECT(1,1)
	
	
ENTITY TIME
	Primarykey: StartDate
	Attribute: EndDate
	
TERNARY-RELATIONSHIP INVOLED
	EMPLOYER(0,N) TIME(1,N), ACTIVITY(1,N)

ENTITY AUDITOR
	Primarykey: ACode
	Attribute: Name, qualification
	
ENTITY ASSESSMENT
	Primarykey: StartDate
	ForeignKey: ACode(Auditor)
	Attribute: 	endDate, judgment

BINARY-RELATIONSHIP WHICH
	ACTIVITY(1,N) ASSESSMENT(1,1)
	
BINARY-RELATIONSHIP WHO
	ASSESSMENT(1,1) AUDITOR(0,N)
	
ENTITY ACCOUNT_STATMENT
	Primarykey: year, 3monthtimeperiod,
	ForeignKey: Code(Project)
	Attribute: 	expenses

BINARY-RELATIONSHIP WHEN
	ACCOUNT_STATMENT(1,1) PROJECT(0,N)
	
	
		
	
--FOREIGN_KEY EXE

 PROJECT (SSN) REFERENCES EMPLOYEES (SSN)
 ASSESSMENT (Code) REFERENCES Audition (Code)
 ACCOUNT-STATMENT(CodP) REFERENCES PROJECT(CodP)


--Exercise 5

PROJECT(Code_,Title, totalbudget, start,end,SSN)
ACTIVITY(ACode_, Code)
REQUIRMENT_DEFINITION(ACode_)
DEVELOPMENT_ACTIVITY(ACode)
DISSEMINATION_ACTIVITY(ACode_)
HAS_CONGRESS(ACode_, CongressName)
HAS_S(Scode_, ACode)
SOFT-COMPONENT(SCode_, description, name)
HAS-H(SCode_, H-Code_)
EMPLOYER(SSN_, Name, hiringDate, URL*)
INVOLVED(SSN_, ACode_, StartDate_, enddate)
AUDITOR(ACode_, Name, qualification)
ASSESSMENT(StartDate_, ACode_, endDate, judgment, ACode)
ACCOUNT-STATMENT(Year_, 3monthstimeperiod_, expenses, Code_)
