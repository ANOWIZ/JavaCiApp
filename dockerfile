FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src/app
RUN gradle build

FROM amazoncorretto:17-alpine3.16

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/app/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
