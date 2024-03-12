FROM openjdk:17
EXPOSE 9090
ADD target/library-project.jar library-project.jar
ENTRYPOINT ["java","-jar","/library-project.jar"]