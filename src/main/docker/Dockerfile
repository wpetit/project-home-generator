FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD http://ci.wpetit.com/nexus/repository/maven-snapshots/com/wpetit/project-home-generator/1.0.0-SNAPSHOT/project-home-generator-1.0.0-20160830.130508-2.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app.jar"]