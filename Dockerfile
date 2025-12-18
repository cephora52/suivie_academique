FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .

RUN ./mvnw clean package -DskipTests

# Etape 2 : Executer l'application (JRE 17)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exposer le port par defaut de spring boot

EXPOSE 8089

#Demarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]