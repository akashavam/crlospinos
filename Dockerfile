FROM openjdk:11-jre-slim

WORKDIR /home/opc

COPY ./target/app.war app.war

EXPOSE 8080

CMD ["java", "-war", "app.war"]