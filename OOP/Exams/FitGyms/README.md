Gyms @media print { /\* adjusted to print the html to a single-page pdf \*/ body { font-size: 7pt; }

# Fit

Write a program that allows you to manage the reservation system for the Fit gyms chain. All classes can be found in package **it.polito.oop.fit**. The main class is **Fit**. Class **TestApp** in package example contains an example. Exceptions are thrown through class **FitException**.

The [JDK documentation](https://oop.polito.it/api/index.html) is on the local server.

## R1: Gyms

The Fit chain has several gyms, each identified by its name.

To add a gym, use method **addGymn(String)**. It receives as a parameter the name of the gym (e.g. MacFit Polito). If the gym already exists the method must throw an exception.

The number of gyms registered is given by method **getNumGymns()**.

## R2: Lessons

Each gym organizes, week by week, its daily activities in lessons dedicated to a specialty (e.g. aerobics). Each lesson occupies a slot of one hour from 8 a.m. to 9 p.m. (in total there are 13 slots per day).

You can add a series of lessons to the weekly calendar of a gym by using method **addLessons(String , String , int , String , String ...)**. It receives as parameters the name of the gym in which the lessons are held, the specialty (e.g. Aerobics), the maximum number of participants in the lessons, a string that encodes when lessons are held (the encoding of the string is as follows: _"day1.slot1,day2.slot2, ..."_) and finally the list of the names of the instructors associated with the lessons.  
Days are indicated with numbers from 1 (Monday) to 7 (Sunday); slots are coded with numbers from 8 (8:00 - 9:00) to 20 (20:00 - 21:00).

The method must through an exception if:

- the name of the gym is wrong
- the day of the week or the slot are not in the predetermined ranges
- not all the slots requested are available in the gym indicated (i.e. some of them may already be occupied).

## R3: Customers

A new customer is added using the **addCustomer(String )** method. It receives the name of the customer (a string) and returns the customer code, which is an incremental integer number starting from 1 included.

You can find a customer via the **getCustomer(int)** method. It receives the customer code and returns the customer name. If the customer code is not found the method must throw an exception.

## R4: Reservations

Customers can sign up for a lesson in a gym by using the **placeReservation(int, String, int, int)** method.

Its parameters specify the customer code, the name of the gym, the day of the week and the slot of the lesson.

The reservation is successful if:

- the customer code is valid
- the name of the gym is valid
- the day and the slot are valid
- there are still available seats for the lesson
- the customer has not already signed up for the lesson.

In all other cases, the method must throw an exception.

Method **int getNumLessons(int)** receives a customer code and returns the number of lessons the customer has signed up for (notice that a customer is not restricted to go to just one gym).

## R5: Lessons given

Method **addLessonGiven (String, int, int, String)** indicates who gave a given lesson. Its parameters are the name of the gym, the day and slot of the lesson and the name of the instructor.

The method must throw an exception if:

- The gym does not exist
- The day and the slot are not valid
- The instructor is not among those associated with the lesson.
  .

Method **getNumLessonsGiven (String, String)** receives the name of a gym and the name of an instructor and returns the number of lessons given by the instructor in the gym. The method generates an exception if the gym does not exist.

## R6: Statistics

The **mostActiveGymn()** method provides the name of the gym with the most lessons.

The **totalLessonsPerGymn()** method provides for each gym the number of lessons.

The **TreeMap<Integer, List<String>slotsPerNofParticipants(String)** method receives the name of a gym and provides the gym slots grouped by increasing number of participants (those who have signed up for them). Slots are indicated with _day.slot_.

Example: _8 = \[6.18\], 10 = \[1.8,3.10\]_. It shows that there are 8 participants in slot 18:00-19:00 on Saturday (day 6) and 10 participants in slots 8:00-9:00 on Monday (day 1) and 10:00-11:00 on Wednesday (day 3).
