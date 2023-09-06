FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ADD ./target/ui-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ui-0.0.1-SNAPSHOT.jar"]