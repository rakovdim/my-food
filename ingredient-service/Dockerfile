FROM openjdk:8
RUN mkdir /app
ARG uuid=2000
RUN groupadd --gid $uuid myfoods
RUN useradd -l --uid $uuid --gid $uuid myfood
COPY target/ingredient-service-1.0.0.jar /app/app.jar
RUN chown -R myfood:myfoods /app
RUN chmod -R 770 /app
USER myfood
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]