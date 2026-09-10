# ETAPA 1: Compilacion
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app/msvc-users

COPY ./pom.xml /app
COPY ./msvc-users .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests


# ETAPA 2: Ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/msvc-users/target/*.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java","--enable-preview" ,"-jar", "app.jar"]