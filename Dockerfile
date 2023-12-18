FROM amazoncorretto:17-alpine-jdk
LABEL authors="Ricardo"

COPY target/${project.artifactId}-${project.version}.jar ${project.artifactId}-${project.version}.jar
ENTRYPOINT ["java","-jar","${project.artifactId}-${project.version}.jar"]