FROM maven:3.8-amazoncorretto-11

RUN mkdir /src
COPY . /src
WORKDIR /src

EXPOSE 5000
RUN mkdir /app
COPY /target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
