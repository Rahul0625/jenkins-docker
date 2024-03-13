#FROM openjdk:17
#EXPOSE 9090
#ADD target/hibernateOne.war hibernateOne.war
#ENTRYPOINT ["java","-jar","/hibernateOne.war"]
# Use the official Tomcat image as the base image
FROM tomcat:latest
# Create a directory to copy your WAR file into
RUN mkdir -p "D:/Softwares & Others/apache-tomcat-7.0.109/webapps/ROOT"
# Copy the WAR file into the webapps directory of Tomcat
COPY "Jenkins + Docker\src\main\webapp\hibernateOne.war" "D:/Softwares & Others/apache-tomcat-7.0.109/webapps/ROOT/"
# Expose the default port for Tomcat
EXPOSE 9090
# Start Tomcat when the container starts
CMD ["catalina.sh", "run"]

