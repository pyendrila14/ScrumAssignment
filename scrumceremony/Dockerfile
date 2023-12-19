FROM bellsoft/liberica-openjdk-alpine-musl:17
VOLUME /tmp
ARG DEPENDENCY=target
RUN echo ${DEPENDENCY}
COPY ${DEPENDENCY}/scrumceremony-0.0.1-SNAPSHOT.jar /app/lib/scrumceremony.jar
ENTRYPOINT ["java","-jar","/app/lib/scrumceremony.jar"]