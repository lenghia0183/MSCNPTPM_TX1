# Building-SpringBoot-MicroServices
This repository deals with the highlighting of the importance of Microservices in real world scenarios by building them from scratch to building Apache Kafka and RabbitMq  to handle real streaming data

## Introduction
### What are Microservices? 
Microservices allow large applications to be split into smaller pieces that operate independently. Each 'piece' has its responsibilities and can carry them out regardless of what the other components are doing.  In other words it can also be defined as small, independent components that you can combine to build larger applications. These components can be rescaled, debugged and built independently which increases the rescalability and debugging , testing processes of traditional Monolithic applications will be light weighted. 

### Differences between Microservice Architecture and Monolithic Architectures
![Image showing differences between MicroService and Monolithic Architecture](https://github.com/srinathsai/Building-SpringBoot-MicroServices/blob/main/differences%20between%20monolithic%20and%20micoservices.png)

## Features of Microservices that were explored :
  - Load Balancing between using Spring Service Registry.
  - Routing of APIs with Spring cloud's API gateway.
  - Externalization of configuration with the help of Spring config server.
  - Distributed tracing of an API request from starting to ending of it using Spring Micrometer.
  - The 3 ways called RestTemplate, Spring cloud openFeign and webclient are analyzed for handling the APIs request between Microservices.
  - Minimization of sending Post request of refresh by using Spring bus.
  - Apache Kafka and RabbitMQ message brokers are tested for handling real live streaming data.

## Requirements :
  - Intellij Idea or any Java IDE.
  - Postman Client.
  - Docker Desktop, Docker Hub.
  - JDK with a version of 17 and 17+ installed in pc.
  - MySQL.

## Implementation :
### Initial process - 
  - At first required number of independent springboot projects which will be independent microservices are built with necessary dependencies from Spring initializer.  In my case Employee service, organization service and department service are 3 individual springBoot Microservices that were developed and explored all the features of Micorservices on these 3 core springboot projects.
  - Create 3 seperate databases one for each in MySQL. (Because each independent Microservice can have it's own database).
  - Configure all these applications.properties files to respective dbs that were created in above step.
  - For each of 3 springboot projects develop necessary Rest APis . I have developed 2 api request methods for each one is for saving entity in database (Push request), next is get request. These 2 api request methods are built fro each springboot project using DTO and mappers.
  - Remember all these 3 springboot server.ports in application.properties must be different because being different servers allows us to run them simultaneously.
  - Check your logic of your api requesting methods using postMan for GET requests if you designed any or check for records in 3 databases for POST requests if you designed any.
  - Once the logic of all your api request methods are correct, now we can explore all the features of the Microservices which are as below :

### Implementation of Microservices features :-
  a) **Load Balancing using Spring Service Registry**: </br>
    Initially a seperate Springboot project with spring-cloud-starter-netflix-eureka-server dependency is imported and made as parent module and remaining above modules as child modules in project structure of IntellijIdea. After this you need to add the same dependency to all the microservices and configure respective application properties with a common property that is "eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/" (The spring service registry will be on default 8761 port ). After this is done run all the microservices simultaneously. And go to the url localhost:8761 in a web browser, after few seconds you will be able to see which Microservices are running on which ports with names that were mentioned in application.properties files. I have made department-service a jar copy with different server port and all the microservices that are running will be shown in this interface. </br>
    The interface looks something like this :-
   ![Interface of Service Registry](https://github.com/srinathsai/Building-SpringBoot-MicroServices/blob/main/Spring%20Service%20registry%20interface.png).

   ***The Main advantage of this feature is : if multiple microservices are running on different ports and if any of the microservice failed then working of application won't be disturbed  because it does load blanacing and finds the same working type microservice running on another port and directs any api call to it. If there is no same working microservice available we handle this case in circuit breaker using resilence4j. </br>***

b) **Communication between Microservices** : </br>
Let's say there is a situation of retriving a record storing in another database of another microservice from current microservice. So a api call between Microservices is required. This can be implemented by 3 ways which are using Webclient, RESTTemplate and Spring cloud Open Feigns. But before that we need to add the attribute of another microservice to the microservice entity class at which main api call matches the port and you need to write the logic for retriving the record of another microservice based on this attribute  that was added in the main microservice . After this you save few records by sending POST requests. </br>
Architecture used here :-
![Architecture of communication between microservices](https://github.com/srinathsai/Building-SpringBoot-MicroServices/blob/main/Communication%20between%20microservices.png).

So in my application Employee Service microservice receives the base REST API. So therefore added 2 attributes called departmentcode (a department service microservice attribute which was written a logic to retrieve records based on this attribute in department microservice) and organizationcode (a organization service microservice attribute which was written a logic to retrieve records based on this attribute in organization microservice). I also added APIresponseDTO class which has 3 instances of Employee object, department object and organization object. so this APIresponseDTO is used to give output of controller layer and due to @ResponseBody annotation this obect will be converted as list of json objects of records employee , organization and department records from their respective databases. </br>
Next step is connecting employee service to organization microservice  and department service for communication of api. For this to happen we can use any of the following 3 methods: </br>
  1) via RESTTemplate : first A RESTTemplate spring bean is created in EmployeeService main class. Next in sevice layer RESTTemplate has inbuilt function called getForEntity whose parameters you need to send the url and entity class of organization Microservice or department service.
  2) via WebClient : Same as RESTTemplate a springbean of WebClient is created in EmployeeService main class. But here a url of rest api call to department service or a url of restapi call to organization service is just enough . this will retrieve the specific record in Monotype and we need to convert into java object.
  3) via Spring cloud open Feign : Here you need to create a interface with normal spring @GetMapping abstract method. Annotate this interface with @FeignCloud() and in this parameter type the microservice name same as format in service registry . That's it this interface method implementation doesn't need to be bothered as spring cloud will create implementation of this method and retrieve record matching departmentcode or organizationcode dynamically which was given in the rest api url as pathVariable.


c) **Externalizing Configurations of Microservices** </br>
Imagine there are 100s of Microservices and there is a need to change in configurations of all these. So in that position we need to restart entire microservices which is a cumbersome and tiring process. Thus the feature of Microservices that comes into place is this feature in which all the configurations are externalized to one source and just sending actuator/refresh post request would make effect of whatever changes being made in configurations files which are altogether stored in external source. In this way we can eliminate restarting of Microservices every time. So I chose Git as external source to store the application.properties files of 3 microservices. this is repository where you can find the application.properties files of my 3 microservices [repo](https://github.com/srinathsai/config-server-repo). All the changes in configurations are made here and in microservice application.properties files you need to include this git link and all you need is sending a POST request of actuator/refresh. to get these changes in effect. 

d) **Using of Spring Bus to reduce the frequency of sending actuator/refresh post request** </br>
In the above case we can see that even though we skipped the process of restarting microservices there is still need of us to send actuator/refresh POST request every time we make a change. So to reduce this I have used a concept of Spring cloud called Spring Bus with external message broker called RabbitMq from docker and made all the microservices subscribe to this message broker. So only one time if we send the actuator/refresh POST request to the message broker it will broadcast the changes to all the microservices that are subscribed to it.  For this to happen we must include the RabbitMq's host, port and username in the application.properties files of Microservices.

e) **Api gateway using Spring Cloud gateway** </br>
Another Microservice called API gateway is created with a dependency Spring cloud starter gateway. This dependency makes this microservice as api gateway implies all the apis first will be directed to it. This microservice internally acts as a load balancer and routes apis to specific microservice based on the requested api matching particularly microservice port. This routing of apis is done in application.properties files of API gateway and can be done in 2 ways. </br>
  They are as follows :- </br>
    1) Manual routing </br>
    2) Automatic routing. </br>
    I sticked to manual routing because if any new user saw my code manual routing can be helpful to understand better as it includes manual ports of respective microservice with urls.</br>
    
   Architecture used :- </br> 
   ![API Gateway Architecture](https://github.com/srinathsai/Building-SpringBoot-MicroServices/blob/main/Api%20gateway.png)

f) **Circuit Breaker** </br>
Of all the microservices there might be a possibility of connection breaking between 2 microservices . So A api call which involves these 2 microservices will show a entire bad result even though other microservices are running perfectly. So to avoid this rollback error I have implemented a circuit breaker using resilence4J . So I have implemented between Employee microservice and organization microservice and purposely switched off Employee Microservice. So whenevr the api call that should go from Employee microservice to organization microservice usually gets a rollback error but I have implemented circuit breaker using Resilence4j which will give a message to the client .</br>

Architecture used :- </br>
![Circuit Breaker Architecture](https://github.com/srinathsai/Building-SpringBoot-MicroServices/blob/main/circuit%20breaker.png)

g) **Distributed Tracing** </br>
Distributed Tracing is a mechanism of Microserives that enables to analyze which microservice is taking more time to process an api request and which microservice is having an issue. This mechanism will give a unique span id for each microservice and will give a detailed flow chat of API request indicating which microservice is taking how much time. I implemented this feature using Spring Micrometer dependency. Usually Spring cloud provides zipkin or sleuth but these are limited for only Spring 3 version but above Spring 3 version you can use spring Micrometer.
#   M S C N P T P M _ T X 1  
 #   M S C N P T P M _ T X 1  
 