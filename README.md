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
java -jar springBoot/target/SpringBootDemo-1.0.jar

# Run Application with debugger
java -agentlib:jdwp=transport=dt_socket,address=7777,server=y,suspend=n -jar springBoot/target/SpringBootDemo-1.0.jar