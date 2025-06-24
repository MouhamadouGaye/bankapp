# Stage 1: Build the application using a Maven container
FROM maven:3.9.6-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller image with only the JRE and the JAR
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
# Copy the built JAR from the 'build' stage
COPY --from=build /app/target/bankapp-0.0.1-SNAPSHOT.jar app.jar
# Expose the port the app runs on
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]