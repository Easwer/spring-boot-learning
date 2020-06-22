# Spring Boot Application
Spring Boot Application with React Material GUI

# Requirements
Java Version  : 8    
JDK Version   : 1.8.0_201    
Maven Version : 3.3.1    

# Linux Build Process
cd springBoot    
mvn clean    
mvn install (Dev)    
mvn build    

# Run Application
java -jar springBoot/server/target/service-1.0.jar    

# Run Application with debugger
java -agentlib:jdwp=transport=dt_socket,address=7777,server=y,suspend=n -jar springBoot/server/target/service-1.0.jar   

# Access Server
URL: https://localhost:8083

# Static Code Analysis Tools
Checkstyle    
PMD    
Firebug

# Run Static Code Analysis Tools
cd springBoot    
mvn site

# API Implementaion Tool
OpenAPI 3.0.3

# API Documentation
Swagger OpenAPI v3 GUI    
URL: https://localhost:8083/api-docs.html

# Database Tools
H2 Database    
H2 URL: https://localhost:8083/h2    
Flyway database migration
