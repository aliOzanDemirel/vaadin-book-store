FROM gradle:4.9-alpine

EXPOSE 8080 8443

WORKDIR /app

COPY . /app

USER root
RUN chown -R gradle /app
USER gradle

RUN gradle bootJar --stacktrace

CMD ["java", "-Dspring.profiles.active=local", "-jar", "build/libs/VaadBooks.jar"]
