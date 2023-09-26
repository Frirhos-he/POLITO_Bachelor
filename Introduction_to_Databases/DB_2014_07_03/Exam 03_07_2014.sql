EXERCISE1
A. projection: E.CodI, Name
B. theta-join: E.CodI = CE.CodI
C. INGEGNERE E
D. theta-join: A1.CodI = A2.CodI AND A1.CodA != a2.CodA
E. natural join
F. natural join
G. selection: TipologiaAzione = 'media impresa'
H. Consulenza-effettuata CEI
I. Azienda A1
L. selection: TipologiaAzienda = 'media impresa'
M. Azienda A2
N. Consulenza_effettuata CE2

EXERCISE2
SELECT NameI, COUNT(*)
FROM INGEGNERE E, CONSULENZE_EFFETTUATE CE
WHERE E.Città = 'Torino' AND E.CodI NOT IN (SELECT E1.CodI
						FROM SETTORE S, CONSULENZE_EFFETTUATE CE
WHERE S.CodS= CE.CodS AND NomeSettore = 'Edilizia')


EXERCISE3
SELECT NomeA, NomeI
FROM AZIENDA A, INGEGNERE E, CONSULENZA_EFFETTUATA
WHERE EC1.CodT = E.CodI AND A.CodA= EC1.CodA
GROUPING BY NomeA, NomeI, E.CodI, A.CodA
HAVING COUNT(*) BLOCK B

BLOCK B
SELECT MAX(*)
FROM BLOCK A 
WHERE A.ACode = Tablenum.ACode
BLOCK A 
(SELECT E.CodI, E.CodA, COUNT(*) AS Num
FROM Consulenza_effettuate CE
WHERE Data = 2013
GROUPING BY ECodI, ECodA) AS Tablenum

ENTITY: BABY_PARKING
	InternalIdentified: Nome
	Attribute: indirizzo, indicazione
BINARY_RELATIONSHIP DISPONIBILE
	BABY_PARKING(1,N) SALE(1,1)
ENTITY: SALE
	InternalIdentifier: Codice
	ForeignKey: Name
	Attribute; NomeSala, dimensione
BINARY_RELATIONSHIP ORGANIZZANO
	BABY_PARKING(0,N) ATTIVITA'(1,1)
ENTITY ATTIVITA'
	InternalIdentifier: ACode
	ForeignKey: Codice
	Attribute; nomeAttivit, etaminima, etamassima
GENERALIZATION (p,e)
	ParentEntity: Attività
	ChildrenEntity: Laboratori
		Attribute: tipologia, oggetti(0,N), Luogo
ENTITY: DIPENDENTI
	PrimaryKey: CodiceFiscale
	Attribute: Nome, DataDiNascita, cellulare, email(0,1)
GENERALIZATION (t,e)
	Parent-entity: DIPENDENTI
	ChildrenEntity: Educatori
		Attribute: TitolodiStato
	ChildreEntity: Personale_Ausiliario
	ChildrenEntity: Amministratitvi
	Attribute: mansione
BINARY-RELATIONSHIP PRESTA
	PERSONALE_AUSILIARIO(0,N) WEEK(1,N)
ENTITY: WEEK
	PrimaryKey: Giorno
ENTITY: BAMBINO
	PrimaryKey: CodiceFiscale
	Attribute: name, dataDiNasciata, recapiti(1,N)
BINARY-RELATIONSHIP: EFFETTUA
	BAMBINO(1,N) ISCRIZIONE (1,1)
ENTITY: ISCRIZIONE
	PrimaryKey: NumeroProgressivo, Anno
BINARY-RELATIONSHIP DOVE
	ISCRIZIONE(1,1) BabyParking(0,N)
BINARY RELATIONSHIP COORDINARE
	EDUCATORE(0,N) PROGRAMMA(1,1)
ENTITY: PROGRAMMA
	PrimaryKey: OraInizio, Data
	Attribute: oraFine, Elenco(1,N)
	ForeignKey: CodiceFiscale(DIPENDENTE)
BINARY-RELATIONSHIP TEMPO
TIME(1,N) PROGRAMMA (1,N)

BABYPARKING(Nome_, Indirizzo, indicazione)
SALE(Codice_, Nome, NomeSala, dimensione)
ATTIVITA'(ACode_, Nome, nomeAttività, etàminima, etàmassima)
LABORATORI(ACode_, tipologia, Luogo)
USA(Oggetto,_ ACode_)
OGGETTO(Oggetto_)
DIPENDENTI(CodiceFiscale_, Nome, datadiNascita, cellulare, email(0,1))
EDUCATORI(CodiceFfiscale_, titolodiStudio)
PERSONALE_AUSILIARIO(CodiceFiscale_)
AMMINISTRATIVI(CodiceFiscale_, mansione)
PRESTA(CodiceFIscale_, week_)
BAMBINO(CodiceFiscale_, nome, datadinascita)
RECAPITI(CodiceFiscale_, reapity_)
ISCRIZIONE(NumeroProgressivo_, Anno_, CodiceFiscaleNome)
PROGRAMMA(CodiceFiscale_, Data_, OraInizio_, oraFino)
HA(SSN_, Data_, OraInizio_, CodiceFiscale_)
