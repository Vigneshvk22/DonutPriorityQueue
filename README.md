# DonutPriorityQueue
 Web service that accepts the orders and provides a list of items to deliver to the pickup counter.
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Execution](#execution)

## General info
This project is designed to satisfy the following requirements : 
* Allows the customer to place orders in Jim's donut shop through webservice.
* Allows the customer to check his/her queue position and waiting time.
* Allows the Manager of Jim's Donut shop to see all the entries in the queue with average waiting time.
* Allows Jim to retrieve the next order to be placed in the cart.
* Allows the customer to cancel the order.

## Technologies
Project is created with:
* Java 
* Spring Boot
* Swagger
* Maven
* IDE : Eclipse

## Prerequisites
Before you continue, ensure you have met the following requirements:
* You have installed the latest version of Java(preferably Java 8).
* You are using a  Windows or Linux or Mac OS machine.
* IDE of your choice(Entire application was developed with the help of eclipse).

## Installation
* Download the donutpriorityqueue.zip and extract the files to your local system.
* Open IDE and import the extracted project(In Eclipse, choose File->Import->Maven->Existing Maven Project).
* Browse donutpriorityqueue project where it has been extracted earlier and click OK.
* Wait for sometime,until the IDE builds the project.

## Execution
* Goto IDE and run DonutPriorityQueueApplication.
* Once the application has started.Open the swagger url - http://localhost:8080/swagger-ui.html in your favourite browser.
* Expand priority-queue-controller.
* Following endpoints can be seen:
   1)CancelOrder.
   2)CheckEntries.
   3)CheckOrderStatus.
   4)PlaceOrder.
   5)RetrieveNextOrder.
   
# Cancel Order
* Allows the customers to cancel the previously made orders.
* Click try it out.
* Enter the Client id and click execute.
* If the customer has made any orders before,corresponding order will be cancelled.
* If the customer hasn't made any orders, corresponding message will be displayed.

# Check Entries
* Allows the Manager to check the entries in the queue.
* Click try it out and execute.
* Orders made by the customers will be displayed along with approximate waiting time.
* If there are no orders in the queue, corresponding message will be displayed.

# Check Order Status
* Allows the customers to check the status of their orders.
* Click try it out.
* Enter the Client id and click execute.
* Customer's position in the queue along with approximate waiting time will be displayed.
* If there are no orders with respect to the customer, corresponding message will be displayed.

# Place Order
* Allows the customer to place the order.
* Click try it out.
* Enter the Client id, required number of donuts and click execute.
* A success message will be displayed after placing the order.
* If the customer id is invalid, corresponding error message will be displayed.
* If the customer who has already made an order,try to place a new order before
  cancelling a corresponding error message will be displayed.
  
# Retrieve Next Order
* Allows Jim to check the next order to be placed in the cart.
* Click try it out and execute.
* The order to be placed in the cart will be displayed.
* If there are no orders, corresponding message will be displayed.
  








