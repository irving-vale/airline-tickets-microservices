# ETAPA 1: Compilacion
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app/msvc-eureka-server

COPY ./pom.xml /app
COPY ./msvc-eureka-server .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests


# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/msvc-eureka-server/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java","--enable-preview" ,"-jar", "app.jar"]