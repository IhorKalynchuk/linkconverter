FROM openjdk:11
ADD target/linkconverter.jar linkconverter.jar
ENTRYPOINT ["java", "-jar", "linkconverter.jar"]
EXPOSE 8080
