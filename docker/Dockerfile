# Build
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copy dependency definitions first to leverage Docker layer caching
COPY pom.xml .
# Download dependencies
RUN mvn dependency:go-offline -B || true

# Copy source code and build
COPY . .
RUN mvn package

# Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/out/TaskDaemon.Core.jar /app/TaskDaemon.Core.jar
# Default memory limit - change as required
ENV JAVA_OPTS="-Xmx1G"

# Run that baby
CMD ["sh", "-c", "java $JAVA_OPTS -jar TaskDaemon.Core.jar"]
