# AutoAid

This project will focus on identifying the most common causes of breakdowns, developing a plan for responding to breakdowns quickly, and creating a system that can connect drivers with nearby assistance.

# Technologies used in the project
>Spring Boot
>Java
>H2 Database
>Netflix Eureka
>Feign Client
>Bootstrap
>Thymeleaf
>Maps
>Git

# Run Instruction:
Final test was done in a macbook. To successfully run the project, you need to run the services in the following sequence:
1. ConfigService
2. DiscoveryService
3. User Service
4. Breakdown Service
5. Aid Service
6. Payment Service
# Generate a demo breakdown at first from the nav bar

In this project, there are overall 6 services including 2 configService and discoveryService.

# Description of Services:
1. ConfigService:
   Main functionality of this service is to provide the service configuration of all services from remote server.
2. Discovery service:
   It allows services to register themselves with the discovery service and enables other services to discover and communicate with     
   each other dynamically in the microservice architecture.
3. User Service:
   In this service, it is used for registration and login of the users. Both car driver and service provider can register and login   
   using this service. After successfull login users are redirected to there respective services/dashboard.
4. Breakdown Service:
   Here in this service, car breakdowns are generated. It is possible send request to all the service providers initially, also there 
   will be a list of nearby service providers with contacts. In another section user can see their sent service request and updates.
5. Aid Service:
6. Payment Service:
