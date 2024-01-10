FROM gradle:7.6.0-jdk17
WORKDIR /opt/app
COPY ./build/libs/museum-ticket-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["sh","-c", "java ${JAVA_OPTS} -jar museum-ticket-0.0.1-SNAPSHOT.jar "]