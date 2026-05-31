# Etapa de construcción (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean dependency:copy-dependencies package -DskipTests

# Etapa de ejecución (Run)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/dependency /app/dependency
COPY --from=build /app/target/users-management-1.4.jar app.jar

# Variables de entorno para conectar con MySQL
ENV DB_HOST=mysql-db
ENV DB_PORT=3306
ENV DB_NAME=censo
ENV DB_USERNAME=root
ENV DB_PASSWORD=R1234

# Comando de inicio de la aplicación apuntando exclusivamente al MainCenso
ENTRYPOINT ["java", "-cp", "app.jar:dependency/*", "com.rcarmona.censo.MainCenso"]
