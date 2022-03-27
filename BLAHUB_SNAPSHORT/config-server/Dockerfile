FROM openjdk:11

RUN mkdir -p /myApp

ADD build/libs/config-server-1.0.0.RELEASE.jar /myApp/app.jar

ENV port=8888

CMD java -jar /myApp/app.jar
