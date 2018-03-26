# vaadin-book-store
This simple book store is for trying out Vaadin to experience server-side frontend development with Java.

Used Spring Boot, Vaadin and Gradle. Undertow is serving the app. H2 in-memory database to list, create, update or delete books. Vaadin Spring Extensions can be used for further improvement.

Keystore for ssl is generated for development with command:
/WHEREEVER_JAVA_IS/keytool -genkey -alias local -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore localhost.p12 -validity 3650

UndertowServletWebServerFactory is modified to add http listener. Default ports are 8080 for http and 8443 for https, pass in -DhttpPort or -DhttpsPort to override defaults.

Vaadin Tutorial: https://vaadin.com/docs/v8/framework/tutorial.html

Vaadin with Spring Tutorial: http://vaadin.github.io/spring-tutorial/
