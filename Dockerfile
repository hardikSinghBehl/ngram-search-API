FROM maven:3.8.3-jdk-17 AS MAVEN_BUILD
COPY src /build/src
COPY pom.xml /build/
WORKDIR /build/
RUN mvn clean install


FROM openjdk:11-oraclelinux7
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/ngram-based-search-API-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ngram-based-search-API-0.0.1-SNAPSHOT.jar"]