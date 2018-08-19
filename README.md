### Vaadin App Example

This simple book store is for trying out Vaadin to experience server-side 
frontend development with Java.

Spring Boot, Vaadin, Gradle and Docker are used. Undertow is serving the app. 
H2 in-memory database is used to list, create, update or delete books. 
Vaadin Spring Extensions can be used for further improvement.

![alt text][screenshot]

##### How to run

1. You can use docker to build image and run it:
    1. `docker build -t vaadin-example-app .`
    2. `docker run -it -p 8080:8080 -p 8443:8443 --rm vaadin-example-app`

2. You can build jar with `gradle bootJar` and run command `java -jar -Dspring.profiles.active=local VaadBooks.jar` at where the jar is. (probably under build/libs/)

##### Some Notes

- Keystore for ssl is generated for development with command: 
`/WHEREEVER_JAVA_IS/keytool -genkey -alias local -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore localhost.p12 -validity 3650`

- UndertowServletWebServerFactory is modified to add http listener. Default ports are 8080 for http and 8443 for https, pass in -DhttpPort or -DhttpsPort to override defaults.

- Vaadin Tutorial: https://vaadin.com/docs/v8/framework/tutorial.html

- Vaadin with Spring Tutorial: http://vaadin.github.io/spring-tutorial/

[screenshot]: img.png "Screenshot"
