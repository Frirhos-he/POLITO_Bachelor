
--Exercise 1
A. projection CC1.CodOrganizzatore, O.NameO
B. Theta-join: O.CodOrganizzatore =CC1.CodOrganizzatore 
C. Theta-join: C.CodCasa!= C2.CodCasa AND C1.CodOrganizzatore = CC2. CodOrganizzatore AND CC1. Città = CC2.Città 
	AND CC1.Categoria = CC2.Categoria
D. Organizzatore O
E. Corsa_campestre CC1
F. Corsa_campestre CC2


--Exercise 2
--BLOCK A
SELECT CodOrganizzatore
FROM CORSA_CAMPESTRE CC1
WHERE Regione = 'Piemonte' AND Categoria = 'Under 20' 

--MAIN BLOCK 
SELECT O.Name, O.CodOrganizzatore
FROM ORGANIZZATORE O, CORSA_CAMPESTRE CC2
WHERE Regione = 'Piemonte' AND O.CodOrg = CC2.CodOrg AND O.CodOrganizzatore NOT IN (BLOCK A)
		AND CC2.Categoria = 'Under 14'


--Exercise 3
--BLOCK A
SELECT CP.CodConcorrente
FROM Concorrente_Partecipa... CP
WHERE Posizione = 1

--BLOCK B
SELECT COUNT(*)
FROM CORSA_CAMPESTRE C2
WHERE C2.Regione = C3.Regione

--MAIN BLOCK
SELECT NameC
FROM Concorrente C3, Concorrente_Partecipa CP1, CORSA_CAMPESTRE CP2
WHERE CP1. CodConcorrente = C3. CodConcorrente AND C3.CodConcorrente NOT IN BLOCK A
		AND C3.Regione = CP2.Regione AND CP1.CodCorsa = CP2.CodCorsa 
GROUP BY C3.CodConcorrente, C3.Name
HAVING COUNT (*) = BLOCK A



--Exercise 4
ENTITY Università
	PrimaryKey: SCode
	Attribute: Nome, Indirizzo ,città , stato

BINARY-RELATIONSHIP DISPONIBILI
	UNIVERSITA''(0,N) SERVIZIO(1,1)

ENTITY SERVIZIO
	Primarykey: SSCode
	ForeignKey: SCode(Università)
	Attribute: Nome, descrizione


BINARY-RELATIONSHIP QUANDO
		SERVIZIO(0,N) GIORNO(0,N)
		Attribute: OraInizio, OraFine	
		
ENTITY GIORNO
	PrimaryKey: Giorno
	
ENTITY COMITATO
	PrimaryKey: NomeC
	Attribute: webpage, attività(1,N)


BINARY-RELATIONSHIP HA
	Università(0,N) COMITATO(1,1)
	
	
ENTITY EVENTO
	Primarykey: Nome, Data
	Attribute: ora, luogo, numMax*
	
BINARY-RELATIONSHIP Organizzato
	EVENTO(1,N) COMITATO(0,N)

ENTITY Studente
	Primarykey: CodiceFiscale
	Attribute: nome, cognome, sesso, età, numero
	
BINARY-RELATIONSHIP PROVIENE
	Studente(1,1) Università(0,N)
	
GENERALIZATION(t,o)
	Parent-entity: Studente
	Children entity: Organizzatore
				Attribute: Campoexp, data
	Children-entity: Ospiti
				Attribute: Intolleranza

BINARY-RELATIONSHIP APPARTIENE
	ORGANIZZATORE(1,1) COMITATO(0,N)
	

BINARY-RELATIONSHIP PARTECIPA
	Ospiti(1,N) SCAMBIO(1,1)
	

ENTITY SCAMBIO
	Primarykey: DataInizio
	ForeignKey: CodiceFiscale(Ospiti)
	Attribute: 	FineScambio


ENTITY ACCOUNT_STATMENT
	Primarykey: year, 3monthtimeperiod,
	ForeignKey: Code(Project)
	Attribute: 	expenses

BINARY-RELATIONSHIP DOVE
	SCAMBIO(1,1) UNIVERSITA(0,N)
	
	
		
	
--FOREIGN_KEY EXE

 HA (NomeC) REFERENCES COMITATO
 HA (SCode) REFERENCES UNIVERSITA

ORGANIZZATO (NomeE) REFERENCES EVENTO
ORGANIZZATO (NomeC) REFERENCES COMITATO

QUANDO (SSCode) REFERENCES SERVIZIO
QUANDO (Giorno) REFERENCES GIORNO




--Exercise 5

Università (Scode_, nome, indirizzo, città, stato)
SERVIZIO (SSCode_, Scode_, nome, descrizione)
DISPONIBILI(Scode_, SSCode_)
QUANDO (SSCode_, Giorno_, oraInizio, oraFine)
GIORNO (Giorno_)
COMITATO(NomeC_, webpage, Scode)
HAVEATTIV(NomeC_, ATV_)
HA(NomeC_, SCode_)
EVENTO(NomeE_,data_, ora, luogo, numax)
ORGANIZZATO(NomeE_, NomeC_)
STUDENTE(CodFiscale_, nome, cognome, sesso, età ì, numero,Scode)
ORGANIZZATORE(CodFiscale_, campoex, data, NomeC)
OSPITI(CodFiscale_, Intolleranza)
SCAMBIO(DataInizio_, CodiceFiscale_, Scode, FineScambio)
