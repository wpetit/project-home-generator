FROM java:8
VOLUME /tmp
EXPOSE 8080
COPY *.jar /app.jar
ENV spring.datasource.url jdbc:postgresql://db:5432/projecthome
ENV spring.datasource.username projecthome
ENV spring.datasource.password projecthome
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app.jar","--spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"]