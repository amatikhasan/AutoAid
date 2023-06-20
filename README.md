# AutoAid

This project focuses on identifying the most common causes of breakdowns, developing a plan for responding to breakdowns quickly, and creating a system that can connect drivers with nearby assistance.

# Technologies used in the project
>Spring Boot,
>Java,
>H2 Database,
>Netflix Eureka,
>Feign Client,
>Bootstrap,
>Thymeleaf,
>Maps,
>Git

# Run Instruction:
Final test was done in a macbook. To successfully run the project, Please run the services in the following sequence:
1. ConfigService
2. DiscoveryService
3. User Service
4. Breakdown Service
5. Aid Service
6. Payment Service
   
*Please Generate a demo breakdown at first from the nav bar to test the project*

# Implementations:
1. Breakdown Service by *Atik Hasan*
2. Aid Service by *Md Shariful Islam*
3. Payment Service by *Sadman Sakib*
4. User Service by *Farhana Binta Shaheed*

In this project, there are overall 6 services including 2 configService and discoveryService.

# Description of Services:
1. ConfigService:
   Main functionality of this service is to provide the service configuration of all services from remote server.
   
3. Discovery service:
   It allows services to register themselves with the discovery service and enables other services to discover and communicate with     
   each other dynamically in the microservice architecture.
   
5. User Service:
   In this service, it is used for registration and login of the users. Both car driver and service provider can register and login   
   using this service. After successfull login users are redirected to there respective services/dashboard. Other services get the user 
   information from this api using Feign Client.
   
7. Breakdown Service:
   Here in this service, car breakdowns are generated. It is possible send request to all the service providers initially, also there 
   will be a list of nearby service providers with contacts. In another section user can see their sent service request and updates. 
   Feign Client is being used in this service as well to make interaction with Aid Service and vice varca.
   
9. Aid Service:
   This service is basically for the Aid Request management by the service provider. Service providers can see all requests for service 
   and they can accept which request they willing to provide services. It is also possible to view the services accepted/that are 
   assigned to the service provider only. 
   
11. Payment Service: This service is used to update the payment, payment status and car breakdown status of a request. This service 
    interacts with Aid Service using Feign Client and updates the respective AidRequest.
