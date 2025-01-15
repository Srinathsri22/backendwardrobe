# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven

# Add Maven to the system PATH
ENV PATH="/usr/share/maven/bin:${PATH}"

# Set the working directory
WORKDIR /app

# Copy the pom.xml to the container
COPY demo/demo/pom.xml demo/demo/

# Install dependencies (this will create the target directory with the JAR)
RUN mvn clean install

# Copy the rest of the application code
COPY demo/demo/ /app/

# Expose the port your app will run on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "demo/demo/target/demo-0.0.1-SNAPSHOT.jar"]
