# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml file from your repo (adjust path based on actual structure)
COPY demo/demo/pom.xml /app/demo/demo/

# Install dependencies (this will create the target directory with the JAR)
RUN mvn clean install -f /app/demo/demo/pom.xml

# Copy the rest of the application code (adjust path as necessary)
COPY demo/demo /app/demo/demo/

# Expose the port your app will run on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "/app/demo/demo/target/demo-0.0.1-SNAPSHOT.jar"]
