FROM openjdk:17

ADD target/Student-Management-System-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT [ "java" ,"-jar" , "/app.war" ]

EXPOSE 8080