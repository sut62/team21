##Stage 0
FROM openjdk:8

COPY . /backend
WORKDIR /backend

RUN ./gradlew -no-daemon bootJar

## Stage 1
FROM openjdk:8
COPY --from=0 /backend/build/libs/backend-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
