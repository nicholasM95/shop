FROM openjdk:11
RUN useradd --create-home -s /bin/bash -u 999 appuser
USER 999
WORKDIR /home/appuser
COPY target/shop.jar app.jar
CMD ["java", "-Duser.timezone=Europe/Brussels", "-jar", "app.jar"]
