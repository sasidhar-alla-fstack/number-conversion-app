# Use a multi-stage build for efficiency
FROM gradle:8.5-jdk17 AS build

WORKDIR /numberconversion-webservice

# Copy Gradle project files
COPY build.gradle ./
COPY gradle/ gradle/

# Download dependencies (cached for faster builds)
RUN gradle dependencies --no-daemon

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN gradle bootJar --no-daemon

# Use a lightweight JDK for the final image
FROM openjdk:17-jdk-slim
WORKDIR /numberconversion-webservice

# Copy the built JAR from the build stage
COPY --from=build numberconversion-webservice/build/libs/*.jar app.jar

# Expose port and run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
