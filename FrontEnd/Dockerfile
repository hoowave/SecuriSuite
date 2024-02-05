FROM kalilinux/kali-rolling
RUN apt-get update && \
    apt-get install -y default-jre && \
    apt-get clean
WORKDIR /app
COPY ./build/libs/FrontEnd-0.0.1-SNAPSHOT.jar /app/frontend-app.jar
ENTRYPOINT ["java", "-jar", "/app/frontend-app.jar"]