FROM openjdk:17
EXPOSE 8081
ADD target/CurrencyConverter.jar CurrencyConverter.jar
ENTRYPOINT ["java", "-jar", "/CurrencyConverter.jar"]