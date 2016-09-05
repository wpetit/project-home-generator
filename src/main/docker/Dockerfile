FROM java:8
VOLUME /tmp
EXPOSE 8080
ENV VERSION 1.0.0
ENV BUILD ${VERSION}
ENV REPOSITORY maven-releases
ADD http://ci.wpetit.com/nexus/repository/${REPOSITORY}/com/wpetit/project-home-generator/${VERSION}/project-home-generator-${BUILD}.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app.jar"]