FROM eclipse-temurin:17-jdk

VOLUME /tmp

EXPOSE 8080

COPY target/TP1-DevOps-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]