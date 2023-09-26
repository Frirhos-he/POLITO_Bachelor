 <!-- body { margin: 0.5cm; /\* font-size: 13px; \*/ } p { margin-top: 0.1em; margin-bottom: 0.5em; } b { font-family: "Courier New"; color: MidnightBlue; background: #f8f8f8; border-radius: 2pt; padding: 1pt; /\* font-size=85%; border: 1pt dotted silver; \*/ } i { color: Navy; font-family: "Courier New"; } h1 { font-family: sans-serif; text-align: center; line-height: 1.1em; } h2 { color: RoyalBlue; font-family: sans-serif; border-top: 1px dotted gray; } .hint { color:LightSlateGray; margin-left:10ch; margin-right:10ch; text-indent:-5ch; } .hint b { font-family: inherit; color: MidnightBlue; background: inherit; border-radius: inherit; padding: inherit; } .hint i { color: gray; } @media print { body { font-size: 10pt; } h2 { float:left; margin-top: 0; padding: 5px 15px 5px 0px; line-height: 0.8em; } } table { float: right; margin: 20px; border-collapse:collapse; border-bottom: 1px solid Navy; border-top: 1px solid Navy; } td, th { padding: 1px 5px 1px 5px; } tr:nth-child(even) { background-color: PowderBlue; margin: 1pt solid PowderBlue; } --> Product Procurement

# Product Procurement

Develop an application to manage a warehouse.  
All the classe must be in the package **warehouse**.

All [JDK documentation](http://softeng.polito.it/courses/docs/api/index.html) is available on a local server.

## R1 - Product Definition

The system main interface is represented by class **Warehouse**.

The method **newProduct()** that accepts as arguments code and description of products, creates and object of class **Product** and records it in the warehouse. Class **Product**provides the method getter methods **getCode()** and **getDescription()**.

To define the quantity of products in the warehouse the method **setQuantity()** can be used, it accepts as argument an integer; in addition the method **decreaseQuantity()** decrements the quantity of a product by one. The method **getQuantity()** allow reading the current quantity.

To retrieve the list of products we can use the method **products()** that returns a collection of _Prodotto_ objects. A specific product can be retrieve by means of the method **findProduct()** that accepts as argument the code and returns the relative _Product_ object.

## R2 - Suppliers Definition

Products are provided by suppliers. To define a supplier we can use the method **newSupplier()** of class _Warehouse_ that accepts as arguments the code and name of the supplier; such method returns a **Supplier** object. Class _Supplier_ offers the getter methods **getCode()** e **getName()**.

To retrieve a given supplier it is possible to use the the methods **findSupplier()** of class _Warehouse_ that accepts the code of the supplier.

To define which products are provided by a given supplier we can use the method **newSupply()** of class _Supplier_ that accepts as argument a _Product_ object.

It is possible to know what products are provided by a given supplier through the method **supplies()** of the _Supplier_ class that returns a list of _Product_ objects sorted alphabetically by description. Conversely, it is possible to know which suppliers provide a given product using the method **suppliers()** of class _Product_ that returns a collection of _Supplier_ objects sorted by name.

## R3 - Placing orders

When required, an employee can place an order. Orders a places by means of the method **issueOrder()** of the class _Warehouse_, that accepts as arguments the product, the supplier, and the required quantity, it returns an object of class **Order**.

If the supplier is not among those providing that type of product an **InvalidSupplier** exception is thrown.

The warehouse generates an unique alphanumeric code with the format _"ORDn"_ where _n_ is a progressive number starting from 1. Class _Order_ provides the getter method **getCode()**.

To retrieve a specific order we can use the method **findOrder()** of class _Warehouse_ that accepts the code and returns the relative _Order_ object.

Every order can be converted into a string using the method **toString()** that returns a string with the following format:

_Order <order code> for <quantity> of <product code> : <product description> from <supplier name>_

For instance: _"Order ORD2 for 100 of BNN : Banana from Del Monte"_

## R4 - Pending orders

When orders are first placed they are in the non-delivered state. To check the delivery state of an order we can use the method **delivered()** that returns a boolean value.

When products are received, we can invoke the method **setDelivered()** that sets the delivered status to _true_ and increments the quantity of the products available in the warehouse by the order quantity. If the order was already delivered a **MultipleDelivery** exception is thrown.

Class _Warehouse_ provides the method **pendingOrders()** that returns a list of orders not yet delivered, sorted by product code.

Also class _Product_ provides a method **pendingOrders()** that returns the list of yet to be delivered orders for that product, sorted by decreasing quantity.

## R5 - Statistics

A few statistics are required concernign the working of the warehouse, in particular:

- the method **ordersByProduct()** returns a map grouping all the orders (both pending and delivered) by product code.
- the method **orderNBySupplier()** returns the count of the delivered orders for each supplier (sorted by name in alphabetic order)
- the method **countDeliveredByProduct()** returns a list of string each containing the product and the number of delivered orders separated by _" - "_ (es. _"BNN - 10"_) sorted by decreasing number of orders.
