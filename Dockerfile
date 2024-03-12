FROM openjdk:17
EXPOSE 9090
ADD target/hibernateOne.war hibernateOne.war
ENTRYPOINT ["java","-jar","/hibernateOne.war"]