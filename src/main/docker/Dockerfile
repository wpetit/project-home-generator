FROM java:8
VOLUME /tmp
EXPOSE 8080
COPY *.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app.jar"]