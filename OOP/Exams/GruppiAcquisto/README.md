Gestione di gruppi d'acquisto @media print { /\* adjusted to print the html to a single-page pdf \*/ body { font-size: 9.2pt; }

# Gestione di gruppi d'acquisto

Si scriva un programma per la gestione di gruppi d'acquisto. Le classi si trovano nel package **groups**; la classe principale è **GroupHandling**. La classe **Example** nel package **main** presenta esempi di uso dei metodi principali ed esempi dei controlli richiesti. Le eccezioni sono di tipo **GroupHandlingException**.

È disponibile la [JDK documentation](http://softeng.polito.it/courses/docs/api/index.html).

## R1: Products and suppliers

Il metodo **addProduct** (String productTypeName, String... supplierNames) lancia un'eccezione se il nome del tipo del prodotto è duplicato; altrimenti definisce un tipo di prodotto e gli associa i suoi fornitori. Inoltre il metodo definisce i fornitori dati i loro nomi, se non sono già stati definiti in una chiamata precedente.

Il metodo **getProductTypes** (String supplierName) dà la lista ordinata alfabeticamente dei nomi dei tipi di prodotti forniti dal fornitore indicato.

## R2: Gruppi

Il metodo **addGroup** (String name, String productName, String... customerNames) definisce un gruppo d'acquisto (group) specificandone: il nome, il nome del tipo di prodotto al quale si riferisce e i nomi dei clienti che compongono il gruppo. Lancia un'eccezione se il nome del gruppo è duplicato o se il tipo di prodotto non è stato definito. Nota: un cliente può partecipare a diversi gruppi.

Il metodo **getGroups** (String customerName) dà la lista ordinata alfabeticamente dei nomi dei gruppi ai quali partecipa il cliente dato.

## R3: Fornitori e offerte

Il metodo **setSuppliers** (String groupName, String... supplierNames) stabilisce i fornitori del gruppo indicati per nome. Se un fornitore risulta indefinito oppure non tratta il tipo di prodotto richiesto dal gruppo il metodo lancia un'eccezione e non associa alcun fornitore al gruppo.

Il metodo **addBid** (String groupName, String supplierName, int price) definisce un'offerta (bid) per il gruppo indicando il fornitore e il prezzo. Lancia un'eccezione se il fornitore non è collegato al gruppo.

Il metodo **getBids** (String groupName) fornisce una stringa contenente le offerte ordinate per prezzo crescente (e a parità di prezzo per nome del fornitore in ordine alfabetico). Le offerte sono separate da virgole e contengono il nome del fornitore e il prezzo separati da ":". Un esempio è il seguente: _"s2:100,s1:120,s3:120"_.

## R4: Votazioni

Il metodo **vote**(String groupName, String customerName, String supplierName) consente ad un cliente di scegliere un'offerta associata al gruppo dato: l'offerta è quella presentata dal fornitore indicato. Il metodo lancia un'eccezione se il cliente non partecipa al gruppo o se il fornitore non ha presentato un'offerta per il gruppo.

Il metodo **getVotes** (String groupName) fornisce una stringa contenente i risultati per fornitore con i fornitori ordinati alfabeticamente. I risultati sono separati da virgole e contengono il nome del fornitore e il numero dei voti separati da ":". Compaiono soltanto i fornitori che hanno ricevuto almeno un voto.

Il metodo **getWinningBid** (String groupName) dà l'offerta vincente del gruppo. L'offerta vincente è quella che ha il maggior numero di voti e a parità di voti quella con prezzo inferiore. L'offerta vincente è indicata con la stringa contenente il nome del fornitore e il numero dei voti separati da ":". Se non ci sono offerte il metodo dà _null_.

## R5: Statistiche

Il metodo **maxPricePerProductType** dà il prezzo massimo delle offerte per tipo di prodotto; i tipi di prodotto sono ordinati alfabeticamente. I tipi di prodotto privi di offerte non sono considerati. Nota: il tipo di prodotto di un'offerta (bid) è quello del gruppo a cui si riferisce l'offerta.

Il metodo **suppliersPerNumberOfBids** dà la lista dei fornitori (ordinati alfabeticamente per nome) per numero decrescente di offerte; i fornitori senza offerte non sono considerati.

Il metodo **numberOfCustomersPerProductType** dà il numero di clienti per tipo di prodotto; i tipi di prodotti sono ordinati alfabeticamente (quelli privi di clienti non sono considerati). Nota: I tipi di prodotti di un cliente sono quelli dei gruppi ai quali partecipa.
