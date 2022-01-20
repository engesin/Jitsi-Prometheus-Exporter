FROM  maven:3.3-jdk-8 as builder

COPY src /usr/src/exporter/src/
COPY pom.xml /usr/src/exporter/

WORKDIR /usr/src/exporter

RUN mvn install

FROM java:8
COPY --from=builder /usr/src/exporter/target/jpe-0.0.5-SNAPSHOT.jar /usr/src/exporter/
COPY conf /usr/src/exporter/conf

WORKDIR /usr/src/exporter
ENTRYPOINT ["java","-d64","-DCONFIG_HOME=./conf", "-jar", "./jpe-0.0.5-SNAPSHOT.jar"]

VOLUME [ "/usr/src/exporter/conf" ]
