# STAGE 1: Build the Application
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline

RUN mv target/*.jar application.jar


# STAGE 2: Create Custom JRE
FROM eclipse-temurin:21-jdk-alpine AS jre-builder
RUN "$JAVA_HOME"/bin/jlink \
    --add-modules java.base,java.sql,java.net.http,java.desktop,java.management,java.compiler,java.instrument,jdk.crypto.ec,jdk.unsupported \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /optimized-jdk \


#STAGE 3: Final Runtime Stage
FROM alpine:latest
WORKDIR /app

ENV JAVA_HOME=/opt/jdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"


COPY --from=jre-builder /optimized-jdk $JAVA_HOME

COPY --from=builder /app/application.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
