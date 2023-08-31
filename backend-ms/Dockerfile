FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ADD ./target/backend-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/backend-0.0.1-SNAPSHOT.jar"]