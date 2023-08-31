FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ADD ./target/discovery-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery-0.0.1-SNAPSHOT.jar"]

EXPOSE 8761