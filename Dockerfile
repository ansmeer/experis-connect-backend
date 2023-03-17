FROM maven:3.8.7-amazoncorretto-17 AS maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17 AS runtime
WORKDIR /app

ENV PORT 8080
ENV SPRING_PROFILE production

ARG JAR_FILE=/app/target/*.jar
COPY --from=maven ${JAR_FILE} /app/app.jar

RUN chown -R 1000:1000 /app
USER 1000:1000

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
