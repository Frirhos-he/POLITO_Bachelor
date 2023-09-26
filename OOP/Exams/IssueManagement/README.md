Requirements @media print { /\* adjusted to print the html to a single-page pdf \*/ body { font-size: 8pt; } h2 { font-size: 120%; margin-bottom: 5pt; line-height: 110%; } .noprint { display: none; } } .en { display: none; }

# Issue Management

Develop a program to manage the issues that a company receive for the components of a system it owns. The main class is **IssueManager**; all the classes classes are in the package **ticketing**. The class **Example** contains a sample code that illustrates the usage of the main methods.  
The exceptions thrown by the program are of type **TicketException**.

You can access a copy of the [JDK documentation](https://oop.polito.it/api) on a local server.

## R1: Users

The system can be used by two classes of users: _Reporter_ and _Maintainer_. A single user can belong to one or both classes.

The method **createUser()** accepts a username and the set of **UserClass**es the user belongs to.  
There are two variants of this method one accepting a _Set_ and the other accepting a variable list of arguments.  
The method throws an exception if the username has already been created or if no user class has been specified.

Given a user name it is possible to retrieve the set of user classes for the user by means of the method **getUserClasses()**

## R2: Components

The issues are related to components of the system under control. Components can recursively contain sub-components.

The method **defineComponent()** creates a new top level component and adds it to the system, the method accepts a name and throws an exception if a component with the same name already exists.

svg text { font-size:50px; font-family:sans-serif; } svg ellipse { stroke: purple; fill: rgb(250,240,230); stroke-width: 4px; } svg line { stroke: purple; stroke-width: 4px; } svg .new { stroke: orange; stroke-dasharray: 5,5; fill: white; } System Sub A Sub B Sub C Sub B.1 Sub B.2

The method **defineSubComponent()** creates a new sub-component; the method accepts the name of the new component and the path of components and sub-components to the parent component. It throws an exception if the the parent component does not exist or if a sub-component of the same parent exists with the same name.  
Example: given the system shown on the right, to add the new sub-component _SubC_ the following invocation is required: _tm.defineSubComponent("SubC","/System");_, while to add the sub-component _SubB.2_ the following code is required: _tm.defineSubComponent("SubB.2","/System/SubB");_.

Given a (sub-)component it is possible to retrieve the set of sub-components names with **getSubComponents()** and the parent component path with **getParentComponent()**, this latter method return _null_ if the component has no parent.

Please note that a path starts with _'/'_ and contains the list of (sub-)components, starting from the top-level component, separated by _'/'_.

## R3: Ticket opening

When a malfunction of some component is detected, a user can open a new ticket that contains the details about the malfunction.

A ticket is opened through the method **openTicket()** that requires as arguments the user name, the path of the malfunctioning (sub-)component, a description of the failure, and the **Severity** of the failure. The method return a unique id (a positive integer starting from 1) for the ticket.  
The methods throws an exception if the user name is not valid, the path does not correspond to a defined component, or the user does not belong to the _Reporter_ user class.

Using method **getTicket()** returns a **Ticket** object for the specific ticket id (or null if the id is not valid), while the collection of tickets can be retrieved using the method **getAllTickets()** that returns the list of tickets sorted by severity (from _Blocking_ to _Cosmetic_, see hint below)

The class _Ticket_ provides the getter methods **getDescription()**, **getId()**, **getSeverity()**, **getComponent()**, and **getAuthor()** returning description, id, severity, (sub-)component path, and user name respectively.

**Hint**: By constructions the _enum_ values are objects that implement _Comparable_; as a consequence, a natural ordering is provided which corresponds to the order the enumerated values are declared (e.g. _Blocking_ precedes _Major_, that means _Ticket.Severity.Blocking.compareTo(Ticket.Severity.Major)_ will return a negative value )

## R4: Ticket lifecycle

A ticket can be in three possible states: _Open_, _Assigned_, _Closed_. When initially opened a ticket is in placed in state _Open_.

The method **assignTicket()** accepts a ticket id and a username, changes the ticket state to _Assigned_ and set the corresponding user as the assignee.  
The method throws an exception if the ticket is in state _Closed_, the ticket id or the username are not valid, or the user does not belong to the _Maintainer_ user class.

The method **closeTicket()** accepts a ticket id and a description of the solution and sets the state of the ticket to _Closed_.  
The method throws an exception if the ticket is not in state _Assigned_.

Class _Ticket_ provides the getter methods **getState()** to retrieve the current state of the ticket, and **getSolutionDescription()** for the description of the solution; it throws an exception if the ticket is not in the _Closed_ state.

## R5: Statistics

The method **countBySeverityOfState()** accepts a ticket state and returns a sorted map (keys sorted in natural order) with the number of tickets per Severity, considering only the tickets with the specific state or all tickets if the argument is _null_.

The method **topMaintainers()** returns a list strings formatted as _"username:###"_ where _username_ is the user name and _###_ is the number of closed tickets; the list is sorter by descending number of closed tickets and then by username.
