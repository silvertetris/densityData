FROM openjdk:21-jdk as builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY src src
RUN microdnf install findutils
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:21-jdk
COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
