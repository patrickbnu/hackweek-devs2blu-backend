FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
COPY settings.xml /root/.m2

COPY lombok.config ./

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

#RUN ./mvnw clean install

EXPOSE 8080:8080

CMD ["./mvnw", "spring-boot:run"]

