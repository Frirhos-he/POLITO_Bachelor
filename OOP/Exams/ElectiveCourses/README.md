Requisiti

# Gestione Insegnamenti a Scelta Elective Courses Management

Sviluppare un sistema che permetta di gestire le iscrizioni a insegnamenti a scelta. Gli insegnamenti hanno con un numero di posti limitati. Gli studenti possono scegliere diversi insegnamenti (con un ordine di priorità). Il sistema effettua gli assegnamenti.

Develop a system to manage enrollments to elective courses. These courses can only accept a limited number of students. Students may choose different courses (and assign a priority to each of them according to their preference). The system then assigns students to courses.

All classes must reside in the package named **it.polito.oop.elective**.

Tutte le classi devono trovarsi nel package **it.polito.oop.elective**.

The class named **Example** in package **main** contains examples using the main methods.

La classe **Example** nel package **main** contiene esempi di uso del metodi principali.

You are free to access the [JDK documentation](https://oop.polito.it/api).

È possibile accedere liberamente alla [Documentazione JDK](https://oop.polito.it/api).

## R1: Students and Courses

The facade class for the system is **ElectiveManager** through which all operations are performed.

Courses are defined by calling the method **addCourse(String , int )** that receives as parameters the (unique) course name and the number of students it can accept (i.e., the available places). The list of the defined courses can be obtained by calling the method **getCourses()**, which returns the names of the defined courses, ordered alphabetically.

Students are registered in the system by calling the **loadStudent(String , double)** method, which receives as parameters the student ID and the grade point average. If the method is called more than once for the same student, the student grade point average is updated with the last passed value.

The **getStudents()** method returns the list of the IDs of all the registered students while the getStudents(double, double) method only returns the list of the IDs of the students whose grade point average is in the interval specified by the two input parameters (inclusive).

## R1: Corsi e Studenti

La classe principale di facciata del sistema è **ElectiveManager**, tramite essa vengono svolte tutte le operazioni.

Gli insegnamenti possono essere definiti tramite il metodo **addCourse(String , int )** il quale riceve come parametri il nome (che è univoco) e il numero di posti disponibili.

È possibile ottenere l'elenco dei corsi definiti tramite il metodo **getCourses()** che restituisce l'insieme dei nomi dei corsi definiti, in ordine alfabetico.

Gli studenti possono essere registrati presso il sistema tramite chiamate al metodo **loadStudent(String , double)** che riceve come parametri l'id dello studente e la media dei voti. Se il metodo viene chiamato più di una volta, per lo stesso studente, il risultato è quello di aggiornare la media.

Il metodo **getStudents()** restituisce l'elenco degli id degli studenti registrati. Per sapere quali studenti hanno una media compresa in un dato intervallo (estremi inclusi) si usa il metodo **getStudents(double, double)** che restituisce l'elenco degli id degli studenti.

## R2: Enrollment requests

A student wanting to enroll to elective courses specifies a list of (1 up to 3) courses ordered by preference, from the most preferred one to the least preferred one.

The request is made by calling the **requestEnroll(String,List)** method, which receives as parameters the student ID and the list of the desired courses. This method returns the number of courses included in the list. An **ElectiveException** exception is raised if the number of requested courses is not between 1 and 3 (inclusive), if the student ID does not belong to an already registered student, or if one or more of the requested courses have not been defined.

The **numberRequests()** method returns the number of student that have expressed a preference for elective courses. This method returns a map that associates each course with the number of students that have selected it as their first, second, or third choice. The map key is the name of the course, while values are lists of 3 elements. The first element in the list counts the student that have selected course (i.e., the key) as their first, second, or third choice. The map also includes courses that have not been chosen by any student (in such case the list will contain 3 zeros).

## R2: Richieste di iscrizione

Quando uno studente vuole iscriversi agli insegnamenti a scelta può indicare una lista di insegnamenti (da 1 a 3) in ordine di priorità. La richiesta viene fatta tramite il metodo **requestEnroll(String,List<String>)** che riceve come parametri l'identificatore dello studente e l'insieme degli insegnamenti desiderati in ordine di priorità, dal preferito a quello meno desiderato. Il metodo restituisce il numero di insegnamenti per cui è stata espressa una preferenza. Viene generata un'eccezione **ElectiveException** se il numero di insegnamenti espressi non è da 1 a 3, se l'id non corrisponde ad uno studente già inserito, oppure se gli insegnamenti non sono stati definiti.

È possibile conoscere il numero di studenti che hanno espresso una preferenza tramite il metodo **numberRequests()** che restituisce una mappa che associa ad ogni insegnamento il numero di studenti che l'hanno selezionato come prima, seconda e terza scelta. La mappa ha come chiave il nome dell'insegnamento e come valore una lista di tre elementi che corrispondono alla 1a, 2a e 3a scelta. La mappa deve riportare anche gli insegnamenti che non sono stati scelti da nessuno studente (in tal caso la lista conterrà tre zeri).

## R3: Making the classes

When the deadline for selecting the elective exams expires, the student office defines the list of students enrolled to each elective course. The lists are defined by calling the **makeClasses()** method, that returns the number of students not enrolled to any course.

When defining the lists, the system tries to accommodate the student preferences starting from the students with higher grade point average until no places remain in any of the defined courses.

A student that does not find a place in any of the requested courses is not enrolled to any of them. The **getAssignments()** method returns assignment to courses as a map that associates each course to the list of student ID assigned to it. Lists are ordered in decreasing order of the grade point average.

## R3: Formazione classi

Quando termina il periodo per esprimere le preferenze per gli insegnamenti a scelta, l'ufficio offerta didattica avvia la procedura di formazione delle classi. La formazione avviene tramite il metodo **makeClasses()** che restituisce il numero di studenti non assegnati ad alcun insegnamento.

Il principio seguito nell'assegnamento è che gli studenti con media più elevata vengono soddisfatti per primi, fino ad esaurimento dei posti negli insegnamenti selezionati.

Uno studente che non trova posto in nessuno degli insegnamenti viene considerato come studente non assegnato.

È possibile conoscere gli assegnamenti tramite il metodo **getAssignments()** che restituisce una mappa che associa ad ogni corso l'elenco degli id degli studenti assegnati. La lista degli id è ordinata in base alla media dei voti decrescenti.

## R4: Message Notification

The system sends notifications regarding the procedure of the assignment of elective courses.

The system uses notification objects to send messages. Objects must implement the **Notifier** interface and are registered through the **addNotifier(Notifier)** method. For instance, notifiers could send SMS or email notifications.

Developing such objects and the corresponding classes is out of the scope of the exam (assume they are provided by third parties).

When an enrollment request is made (through a call to the _requestEnroll()_ method) the system notifies all the registered notifiers with the **requestReceived(String)** method. This method receives as a parameter the student ID of the notification recipient.

After the classes have been made (trough the _makeClasses()_ method), each student enrolled in a course is notified by calling the **assignedToCourse(String,String)** method. This method receives as parameters the student ID and the name of the course the student has been assigned to.

### Hints

- The Notifier must not be implemented. The main package contains a sample class that may used to understand how Notifier work.
- To fulfill this requirement, just implement the _addNotifier(Notifier)_. Of course, you may also need to add some functionality to previously described methods.

## R4: Notifica di Messaggi

Il sistema di gestione invia delle notifiche che riguardano la procedura di gestione degli insegnamenti a scelta.

Per inviare messaggi utilizza oggetti notificatori che implementano l'interfaccia **Notifier**. Tali oggetti devono essere registrati tramite il metodo _addNotifier(Notifier)_.

Tali oggetti e le relative classi sono fuori dal contesto dell'esame (sono fornite da altri) e possono fornire, ad esempio, notifiche via email o via sms.

Ogni volta che viene specificata una richiesta di iscrizione (tramite il metodo _requestEnroll()_) il sistema notifica la presa in carico della richiesta chiamando per tutti i notificatori registrati il metodo **requestReceived(String)** che riceve come parametro l'id dello studente.

Dopo aver composto le classi (tramite il metodo _makeClasses()_), per ogni studente assegnato ad un corso (e per ogni notificatore) viene chiamato il metodo **assignedToCourse(String,String)** che riceve come parametri l'id dello studente ed il corso assegnato.

### Suggerimenti

- L'interfaccia _Notifier_ non deve essere implementata. Nel package _main_ viene fornita un'implementazione di esempio per poter osservare il funzionamento.
- Questo requisito non richiede di implementare altri metodi oltre a _addNotifier(Notifier)_, inoltre è necessario aggiungere delle funzionalità a metodi già descritti nei requisiti precedenti per inviare le notifiche.

## R5: Stats

The **successRate(int)** method returns the percentage of students that have been assigned to a course corresponding to a given preference. This method receives as parameter the preference number, between 1 and 3 (inclusive) and returns the success rate, that is a floating point number between 0 and 1. The rate is evaluated by taking into account (as denominator) all the students, including those not assigned to any course.

The **getNotAssigned()** method returns the ID of students that have not been assigned to any course.

## R5: Statistiche

È possibile sapere quale percentuale di studenti è stata assegnata all'insegnamento indicato come prima (seconda o terza) scelta tramite il metodo: **successRate(int)** che riceve come parametro il numero della scelta per cui calcolare il successo, il numero varia da 1 a 3. Il tasso di successo viene calcolato considerando (al denominatore) tutti gli studenti, anche quelli che non sono stati assegnati ad alcun insegnamento.

L'elenco degli id degli studenti non assegnati ad alcun insegnamento indicati è restituito dal metodo **getNotAssigned()**.
