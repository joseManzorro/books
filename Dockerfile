FROM openjdk:15
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xdebug" , "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "app.jar"]
