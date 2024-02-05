FROM kalilinux/kali-rolling
RUN apt-get update && \
    apt-get install -y default-jre apache2 httrack wget nmap crunch john zip && \
    apt-get clean
WORKDIR /app

COPY ./build/libs/BackEnd-0.0.1-SNAPSHOT.jar /app/backend-app.jar
COPY ./DockerRun.sh /DockerRun.sh
RUN chmod +x /DockerRun.sh

ENTRYPOINT ["/DockerRun.sh"]