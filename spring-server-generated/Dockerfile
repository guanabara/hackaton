FROM hirokimatsumoto/alpine-openjdk-11
VOLUME /tmp

ARG JAR_FILE=target/public-api-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080
