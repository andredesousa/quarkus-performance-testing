FROM azul/zulu-openjdk-alpine:11.0.17-jre

WORKDIR /app
COPY build/quarkus-app .

EXPOSE 8080

CMD ["java", "-jar", "quarkus-run.jar"]
