# Use the official OpenJDK 11 base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot executable JAR file to the container
COPY target/your-spring-boot-app.jar app.jar

# Expose the port on which your Spring Boot app listens
EXPOSE 8082

# Set the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]