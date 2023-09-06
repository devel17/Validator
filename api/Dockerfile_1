FROM maven:3.8.4-openjdk-17 as backend
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean install -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=backend /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]