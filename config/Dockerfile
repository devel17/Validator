FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ADD ./target/config-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-0.0.1-SNAPSHOT.jar"]

#EXPOSE 8888