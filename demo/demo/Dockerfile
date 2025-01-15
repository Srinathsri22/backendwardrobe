# Use a Maven base image
FROM maven:3.8.1-openjdk-11 as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .

# Install project dependencies
RUN mvn clean install

# Copy the rest of the source code
COPY src /app/src

# Package the application
RUN mvn clean package

# Start the application using the generated JAR file
CMD ["java", "-jar", "/app/target/demo-0.0.1-SNAPSHOT.jar"]
