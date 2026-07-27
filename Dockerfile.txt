# Etapa 1 - Build
FROM maven:3.9.11-eclipse-temurin-25 AS builder

WORKDIR /app

# Copia o pom primeiro para aproveitar cache
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o restante do projeto
COPY src ./src

# Gera o JAR
RUN mvn clean package -DskipTests

# Etapa 2 - Runtime
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copia o JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Porta utilizada pelo Render
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]