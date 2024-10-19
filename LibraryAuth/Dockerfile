FROM openjdk:17-alpine
COPY target/*.jar opt/auth.jar
ENTRYPOINT ["java", "-jar", "opt/auth.jar"]