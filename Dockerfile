FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package

CMD ["java", "-jar", "target/TechlynxInnovationsPvtLtd-0.0.1-SNAPSHOT.jar"]